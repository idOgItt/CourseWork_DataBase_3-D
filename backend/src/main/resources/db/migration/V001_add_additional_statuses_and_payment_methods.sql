INSERT INTO OrderStatuses (Name, Description) VALUES
                                                  ('COMPLETED', 'Order has been successfully completed'),
                                                  ('FAILED', 'Order processing failed'),
                                                  ('CANCELED', 'Order was canceled by the user or system');
INSERT INTO ModelStatuses (Name, Description) VALUES
    ('ARCHIVED', 'Model is no longer available or active');

INSERT INTO PaymentStatuses (Name, Description) VALUES
    ('FAILED', 'Payment was unsuccessful');

INSERT INTO PaymentMethods (Name, Description) VALUES
                                                   ('CREDIT_CARD', 'Payment made using a credit card'),
                                                   ('PAYPAL', 'Payment made via PayPal'),
                                                   ('BANK_TRANSFER', 'Payment made via bank transfer'),
                                                   ('CASH', 'Payment made using cash');
