CREATE OR REPLACE TRIGGER after_order_item_insert
    AFTER INSERT ON OrderItems
    FOR EACH ROW
EXECUTE FUNCTION handle_update_model_quantity_exception();

CREATE OR REPLACE TRIGGER log_user_updates
    AFTER INSERT OR UPDATE ON Users
    FOR EACH ROW
EXECUTE FUNCTION log_user_action();

--CREATE OR REPLACE TRIGGER log_discount_updates
--   AFTER INSERT OR UPDATE ON Discounts
--   FOR EACH ROW
--EXECUTE FUNCTION log_user_action();

CREATE OR REPLACE TRIGGER log_orders_actions
    AFTER INSERT OR UPDATE ON Orders
    FOR EACH ROW
EXECUTE FUNCTION log_user_action();

CREATE OR REPLACE TRIGGER log_models_actions
    AFTER INSERT OR UPDATE ON Models
    FOR EACH ROW
EXECUTE FUNCTION log_user_action();

CREATE OR REPLACE TRIGGER log_reviews_actions
    AFTER INSERT OR UPDATE ON Reviews
    FOR EACH ROW
EXECUTE FUNCTION log_user_action();

CREATE OR REPLACE TRIGGER calculate_discount_trigger
    BEFORE INSERT OR UPDATE ON Orders
    FOR EACH ROW
EXECUTE FUNCTION calculate_discount_trigger();

CREATE OR REPLACE  TRIGGER after_review_insert
    AFTER INSERT ON Reviews
    FOR EACH ROW
EXECUTE FUNCTION update_model_rating_trigger();

CREATE OR REPLACE  TRIGGER after_order_item_update
    AFTER INSERT OR UPDATE ON OrderItems
    FOR EACH ROW
EXECUTE FUNCTION handle_update_model_quantity_exception();

CREATE OR REPLACE  TRIGGER validate_model_availability_trigger
    BEFORE INSERT OR UPDATE ON OrderItems
    FOR EACH ROW
EXECUTE FUNCTION validate_model_availability();

CREATE OR REPLACE  TRIGGER recalc_order_after_item_change
    AFTER INSERT OR UPDATE ON OrderItems
    FOR EACH ROW
EXECUTE FUNCTION recalc_order_after_item_change();

CREATE OR REPLACE TRIGGER after_role_delete
    AFTER DELETE ON Roles
    FOR EACH ROW
EXECUTE FUNCTION set_guest_role();

CREATE OR REPLACE TRIGGER after_payment_update
    AFTER UPDATE ON Payments
    FOR EACH ROW
EXECUTE FUNCTION reduce_order_total_on_payment();

CREATE OR REPLACE TRIGGER after_user_delete
    AFTER DELETE ON Users
    FOR EACH ROW
EXECUTE FUNCTION log_user_deletion();

CREATE OR REPLACE TRIGGER after_order_status_update
    AFTER UPDATE OF StatusID ON Orders
    FOR EACH ROW
EXECUTE FUNCTION log_order_status_change();

CREATE OR REPLACE TRIGGER after_payment_success_update
    AFTER UPDATE ON Payments
    FOR EACH ROW
EXECUTE FUNCTION update_discount_usage_on_payment();

CREATE OR REPLACE TRIGGER after_discount_delete
    AFTER DELETE ON Discounts
    FOR EACH ROW
EXECUTE FUNCTION log_discount_deletion();

CREATE OR REPLACE TRIGGER after_review_insert_update
    AFTER INSERT OR UPDATE ON Reviews
    FOR EACH ROW
EXECUTE FUNCTION log_review_action_and_update_rating();

CREATE OR REPLACE TRIGGER after_image_insert
    AFTER INSERT ON Images
    FOR EACH ROW
EXECUTE FUNCTION log_image_upload();

CREATE OR REPLACE TRIGGER after_model_quantity_update
    AFTER UPDATE OF QuantityAvailable ON Models
    FOR EACH ROW
EXECUTE FUNCTION log_model_quantity_update();

--CREATE OR REPLACE TRIGGER after_category_insert_update
--    AFTER INSERT OR UPDATE ON Categories
--    FOR EACH ROW
--EXECUTE FUNCTION log_category_action();