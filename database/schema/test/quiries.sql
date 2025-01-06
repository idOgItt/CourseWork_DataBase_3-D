SELECT *
FROM logs;

SELECT *
FROM models;

SELECT *
FROM orders;

SELECT *
FROM discounts;

SELECT *
FROM orderitems;

UPDATE Orders
SET Status = 'SHIPPED'
WHERE OrderID IN (1, 2, 3);


DELETE FROM Orders
WHERE Status = 'CANCELLED';

UPDATE Models
SET QuantityAvailable = QuantityAvailable + 20
WHERE Name IN ('Laptop', 'Smartphone');


CREATE OR REPLACE FUNCTION calculate_order_total_before_discount(order_id INT) RETURNS NUMERIC AS $$
DECLARE
    total_before_discount NUMERIC := 0;
BEGIN
    -- Рассчитать сумму заказа до скидки
    SELECT SUM(m.Price * oi.Quantity)::NUMERIC
    INTO total_before_discount
    FROM OrderItems oi
             JOIN Models m ON oi.ModelID = m.ModelID
    WHERE oi.OrderID = order_id;

    -- Если сумма не рассчитана, вернуть 0
    IF total_before_discount IS NULL THEN
        total_before_discount := 0;
    END IF;

    RETURN total_before_discount;
END;
$$ LANGUAGE plpgsql;

SELECT calculate_order_total_before_discount(1) AS total_before_discount;

SELECT calculate_discount(1, 'NEWYEAR2025');

-- Посмотрите, что вернёт эта выборка:
SELECT COALESCE(DiscountAmount::NUMERIC, 0),
       COALESCE(DiscountType, 'FIXED')
FROM Discounts
WHERE Discounts.code = NEW.discount_code
  AND StartDate <= CURRENT_TIMESTAMP
  AND EndDate > CURRENT_TIMESTAMP;


 -- <-- подставьте тот же order_id, что вызываете в функции

SELECT *
FROM Discounts
WHERE Code = 'SUMMER2025'
  AND StartDate <= CURRENT_TIMESTAMP
  AND EndDate > CURRENT_TIMESTAMP;



