CREATE OR REPLACE TRIGGER after_order_item_insert
    AFTER INSERT ON OrderItems
    FOR EACH ROW
EXECUTE FUNCTION handle_update_model_quantity_exception();

CREATE OR REPLACE TRIGGER log_user_updates
    AFTER INSERT OR UPDATE ON Users
    FOR EACH ROW
EXECUTE FUNCTION log_user_action();

CREATE OR REPLACE TRIGGER log_discount_updates
    AFTER INSERT OR UPDATE ON Discounts
    FOR EACH ROW
EXECUTE FUNCTION log_user_action();

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

CREATE TRIGGER recalc_order_after_item_change
    AFTER INSERT OR UPDATE ON OrderItems
    FOR EACH ROW
EXECUTE FUNCTION recalc_order_after_item_change();


