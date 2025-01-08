CREATE OR REPLACE FUNCTION calculate_discount(order_id INT, discount_code VARCHAR) RETURNS MONEY AS $$
DECLARE
    total NUMERIC := 0;
    discount NUMERIC := 0;
    discounted_total NUMERIC;
    discount_type VARCHAR(20) := 'NONE';
BEGIN
    IF order_id IS NULL THEN
        RETURN 0::MONEY;
    END IF;
    SELECT COALESCE(SUM(m.Price::NUMERIC * oi.Quantity), 0)
    INTO total
    FROM OrderItems oi
             JOIN Models m ON oi.ModelID = m.ModelID
    WHERE oi.OrderID = order_id;

    IF discount_code IS NOT NULL THEN
        SELECT COALESCE(DiscountAmount::NUMERIC, 0), COALESCE(DiscountType, 'FIXED')
        INTO discount, discount_type
        FROM Discounts
        WHERE Discounts.Code = discount_code
          AND StartDate <= CURRENT_TIMESTAMP
          AND EndDate > CURRENT_TIMESTAMP;
    END IF;

    IF discount_type = 'FIXED' THEN
        discounted_total := total - discount;
    ELSIF discount_type = 'PERCENT' THEN
        discounted_total := total - (total * discount / 100.0);
    ELSE
        discounted_total := total;
    END IF;

    IF discounted_total < 0 THEN
        discounted_total := 0;
    END IF;

    RAISE NOTICE 'Total: %, Discount: %, DiscountType: %, Result: %', total, discount, discount_type, discounted_total;

    RETURN discounted_total::MONEY;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION log_suspicious_action(
    user_id INT,
    action_type_name VARCHAR
) RETURNS VOID AS $$
DECLARE
    action_type_id INT;
BEGIN
    SELECT ActionTypeID
    INTO action_type_id
    FROM SuspiciousActionTypes
    WHERE ActionName = action_type_name;

    IF action_type_id IS NULL THEN
        RAISE EXCEPTION 'ActionTypeID not found for action type name: %', action_type_name;
    END IF;

    INSERT INTO SuspiciousLogs (UserID, ActionTypeID, Description)
    VALUES (user_id, action_type_id, description);
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION calculate_model_rating(model_id INT) RETURNS NUMERIC AS $$
DECLARE
    avg_rating NUMERIC;
BEGIN
    SELECT AVG(Rating) INTO avg_rating
    FROM Reviews
    WHERE ModelID = model_id;

    IF avg_rating IS NULL THEN
        avg_rating := 0;
    END IF;

    UPDATE Models
    SET Rating = COALESCE(avg_rating, 0.0)
    WHERE ModelID = model_id;

    RETURN avg_rating;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION check_model_availability(model_id INT, quantity INT) RETURNS BOOLEAN AS $$
DECLARE
    available_quantity INT;
BEGIN
    SELECT QuantityAvailable INTO available_quantity
    FROM Models
    WHERE ModelID = model_id;

    IF available_quantity >= quantity THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION log_user_action() RETURNS TRIGGER AS $$
DECLARE
    action_type_id INT;
    user_id INT := NULL;
BEGIN
    RAISE NOTICE 'Trigger activated on table: % with operation: %', TG_TABLE_NAME, TG_OP;

    IF TG_TABLE_NAME = 'Logs' THEN
        RAISE NOTICE 'Skipping trigger execution for Logs.';
        RETURN NEW;
    END IF;

    SELECT ActionTypeID
    INTO action_type_id
    FROM ActionTypes
    WHERE ActionName = UPPER(TG_TABLE_NAME) || '_' || TG_OP;

    IF action_type_id IS NULL THEN
        RAISE EXCEPTION 'ActionTypeID not found for table % and operation %', TG_TABLE_NAME, TG_OP;
    END IF;

    IF TG_OP = 'INSERT' OR TG_OP = 'UPDATE' THEN
        BEGIN
            user_id := NEW.UserID;
        EXCEPTION WHEN undefined_column THEN
            RAISE NOTICE 'Column UserID does not exist in table %', TG_TABLE_NAME;
            user_id := NULL;
        END;
    END IF;

    INSERT INTO Logs (UserID, ActionTypeID, Description, Timestamp)
    VALUES (user_id, action_type_id, 'Action performed on table ' || TG_TABLE_NAME, CURRENT_TIMESTAMP);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION log_exception(
    user_id INT,
    action_type_name VARCHAR,
    exception_message TEXT
) RETURNS VOID AS $$
DECLARE
    action_type_id INT;
BEGIN
    SELECT ActionTypeID
    INTO action_type_id
    FROM ActionTypes
    WHERE ActionName = action_type_name;

    IF action_type_id IS NULL THEN
        RAISE EXCEPTION 'ActionTypeID not found for action type name: %', action_type_name;
    END IF;

    INSERT INTO Logs (UserID, ActionTypeID, Description, Timestamp)
    VALUES (user_id, action_type_id, exception_message, CURRENT_TIMESTAMP);
END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION handle_update_model_quantity_exception() RETURNS TRIGGER AS $$
BEGIN
    BEGIN
        UPDATE Models
        SET QuantityAvailable = QuantityAvailable - NEW.Quantity
        WHERE ModelID = NEW.ModelID;

        IF (SELECT QuantityAvailable FROM Models WHERE ModelID = NEW.ModelID) < 0 THEN
            RAISE EXCEPTION 'Insufficient stock for ModelID %', NEW.ModelID;
        END IF;
    EXCEPTION WHEN OTHERS THEN
        PERFORM log_exception(NULL, 999, 'Unhandled exception_handler in handle_update_model_quantity_exception: ' || SQLERRM);
        RAISE;
    END;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION update_model_rating_trigger() RETURNS TRIGGER AS $$
BEGIN
    BEGIN
        PERFORM calculate_model_rating(NEW.ModelID);
    EXCEPTION WHEN OTHERS THEN
        PERFORM log_exception(NULL, 999, 'Unhandled exception_handler in update_model_rating_trigger: ' || SQLERRM);
        RAISE;
    END;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION validate_model_availability() RETURNS TRIGGER AS $$
BEGIN
    IF NOT check_model_availability(NEW.ModelID, NEW.Quantity) THEN
        PERFORM log_suspicious_action(
                NEW.UserID,
                'SUSPICIOUS_MODEL_AVAILABILITY',
                'Insufficient stock for ModelID: ' || NEW.ModelID
                );
        RAISE EXCEPTION 'Insufficient stock for ModelID %', NEW.ModelID;
    END IF;
    RETURN NEW;

EXCEPTION WHEN OTHERS THEN
    PERFORM log_exception(
            NEW.UserID,
            'EXCEPTION_LOG',
            'Unhandled exception_handler in validate_model_availability: ' || SQLERRM
            );
    RAISE;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION log_suspicious_discount() RETURNS TRIGGER AS $$
BEGIN
    IF NEW.DiscountCode IS NOT NULL AND NOT EXISTS (
        SELECT 1 FROM Discounts WHERE Code = NEW.DiscountCode
    ) THEN
        PERFORM log_suspicious_action(NEW.UserID, 999, 'Suspicious discount code usage: ' || NEW.DiscountCode);
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION calculate_discount_trigger() RETURNS TRIGGER AS $$
BEGIN
    IF NEW.DiscountCode IS NOT NULL THEN
        IF NOT EXISTS (
            SELECT 1
            FROM Discounts
            WHERE Code = NEW.DiscountCode
              AND StartDate <= CURRENT_TIMESTAMP
              AND EndDate >= CURRENT_TIMESTAMP
              AND (UsageLimit IS NULL OR UsageLimit > 0)
        ) THEN
            PERFORM log_suspicious_action(
                    NEW.UserID,
                    'SUSPICIOUS_LOG',
                    'Invalid or expired discount code: ' || NEW.DiscountCode
                    );

            RAISE EXCEPTION 'Invalid or expired discount code: %', NEW.DiscountCode;
        END IF;
    END IF;

    NEW.TotalAmount := calculate_discount(NEW.OrderID, NEW.DiscountCode);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION recalc_order_after_item_change()
    RETURNS TRIGGER AS $$
BEGIN
    UPDATE Orders
    SET TotalAmount = calculate_discount(Orders.OrderID, Orders.DiscountCode)
    WHERE Orders.OrderID = NEW.OrderID;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


