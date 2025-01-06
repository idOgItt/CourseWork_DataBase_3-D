-- data.sql: Скрипт для заполнения базы данных начальными данными

-- Заполнение таблицы Roles
INSERT INTO Roles (RoleName) VALUES ('ADMIN'), ('CLIENT'), ('GUEST');

-- Заполнение таблицы Permissions
INSERT INTO Permissions (PermissionID, PermissionName, Description) VALUES
                                                                        (1, 'MANAGE_USERS', 'Управление пользователями'),
                                                                        (2, 'VIEW_ORDERS', 'Просмотр заказов'),
                                                                        (3, 'MANAGE_DISCOUNTS', 'Управление скидками'),
                                                                        (4, 'PLACE_ORDERS', 'Размещение заказов'),
                                                                        (5, 'VIEW_MODELS', 'Просмотр моделей'),
                                                                        (6, 'LEAVE_REVIEWS', 'Оставление отзывов'),
                                                                        (7, 'MANAGE_MODELS', 'Управление моделями'),
                                                                        (8, 'MANAGE_REVIEWS', 'Управление отзывами'),
                                                                        (9, 'MANAGE_ORDERS', 'Управление состояними заказов');


-- Заполнение таблицы RolePermissions
INSERT INTO RolePermissions (RoleID, PermissionID) VALUES
                                                       (1, 1), -- ADMIN: MANAGE_USERS
                                                       (1, 2), -- ADMIN: VIEW_ORDERS
                                                       (1, 3), -- ADMIN: MANAGE_DISCOUNTS
                                                       (1, 4), -- ADMIN: PLACE_ORDERS
                                                       (1, 5), -- ADMIN: VIEW_MODELS
                                                       (1, 6), -- ADMIN: LEAVE_REVIEWS
                                                       (1, 7), -- ADMIN: MANAGE_MODELS
                                                       (1, 8), -- ADMIN: MANAGE_REVIEWS
                                                       (1, 9), -- ADMIN: MANAGE_ORDERS
                                                       (2, 2), -- CLIENT: VIEW_ORDERS
                                                       (2, 4), -- CLIENT: PLACE_ORDERS
                                                       (2, 5), -- CLIENT: VIEW_MODELS
                                                       (2, 6), -- CLIENT: LEAVE_REVIEWS
                                                       (3, 5); -- GUEST: VIEW_MODELS

INSERT INTO ActionTypes (ActionTypeID, ActionName, Description) VALUES
                                                                    (1, 'USERS_INSERT', 'User inserted a record'),
                                                                    (2, 'USERS_UPDATE', 'User updated a record'),
                                                                    (3, 'ORDERS_INSERT', 'Order inserted'),
                                                                    (4, 'ORDERS_UPDATE', 'Order updated'),
                                                                    (5, 'MODELS_INSERT', 'Model inserted'),
                                                                    (6, 'MODELS_UPDATE', 'Model update'),
                                                                    (7, 'REVIEWS_INSERT', 'Review inserted'),
                                                                    (8, 'REVIEWS_UPDATE', 'Review updated'),
                                                                    (9, 'DISCOUNTS_INSERT', 'Discount insert'),
                                                                    (10, 'DISCOUNTS_UPDATE', 'Discount update'),
                                                                    (999, 'EXCEPTION_LOG', 'Log for exceptions and errors');

INSERT INTO SuspiciousActionTypes (ActionTypeID, ActionName, Description) VALUES
                                                                              (998, 'SUSPICIOUS_LOG', 'Log for suspicious actions'),
                                                                              (999, 'EXCEPTION_LOG', 'Log for exceptions and errors');
-- Заполнение таблицы Users
INSERT INTO Users (Username, Email, PasswordHash, RoleID) VALUES
                                                              ('admin', 'admin@example.com', 'hashed_password_admin', 1),
                                                              ('client1', 'client1@example.com', 'hashed_password_client1', 2),
                                                              ('client2', 'client2@example.com', 'hashed_password_client2', 2),
                                                              ('client3', 'client3@example.com', 'hashed_password_client3', 2),
                                                              ('guest1', 'guest1@example.com', 'hashed_password_guest1', 3),
                                                              ('guest2', 'guest2@example.com', 'hashed_password_guest2', 3),
                                                              ('guest3', 'guest3@example.com', 'hashed_password_guest3', 3),
                                                              ('guest4', 'guest4@example.com', 'hashed_password_guest4', 3),
                                                              ('guest5', 'guest5@example.com', 'hashed_password_guest5', 3),
                                                              ('guest6', 'guest6@example.com', 'hashed_password_guest6', 3);

-- Заполнение таблицы Categories
INSERT INTO Categories (Name) VALUES
                                  ('Electronics'),
                                  ('Home Appliances'),
                                  ('Books'),
                                  ('Clothing'),
                                  ('Toys'),
                                  ('Sports Equipment'),
                                  ('Health & Beauty'),
                                  ('Furniture'),
                                  ('Garden Supplies'),
                                  ('Automotive');

-- Заполнение таблицы Models с учетом UserID
INSERT INTO Models (Name, Description, Price, CategoryID, QuantityAvailable, Status, UserID) VALUES
                                                                                                 ('Smartphone', 'Latest model smartphone.', 699.99, 1, 50, 'ACTIVE', 1), -- Admin
                                                                                                 ('Vacuum Cleaner', 'High-efficiency vacuum cleaner.', 299.99, 2, 30, 'ACTIVE', 2), -- Client 1
                                                                                                 ('Programming Book', 'Learn PostgreSQL in 24 Hours.', 39.99, 3, 100, 'ACTIVE', 3), -- Client 2
                                                                                                 ('T-Shirt', 'Cotton T-shirt.', 19.99, 4, 200, 'ACTIVE', 4), -- Client 3
                                                                                                 ('Toy Car', 'Remote-controlled toy car.', 49.99, 5, 150, 'ACTIVE', 1), -- Admin
                                                                                                 ('Basketball', 'Professional basketball.', 29.99, 6, 120, 'ACTIVE', 2), -- Client 1
                                                                                                 ('Face Cream', 'Hydrating face cream.', 15.99, 7, 80, 'ACTIVE', 3), -- Client 2
                                                                                                 ('Dining Table', 'Wooden dining table.', 499.99, 8, 20, 'ACTIVE', 4), -- Client 3
                                                                                                 ('Garden Shovel', 'Heavy-duty garden shovel.', 25.99, 9, 60, 'ACTIVE', 1), -- Admin
                                                                                                 ('Car Seat Cover', 'Durable car seat cover.', 39.99, 10, 40, 'ACTIVE', 2); -- Client 1

-- Заполнение таблицы Discounts
INSERT INTO Discounts (Code, DiscountAmount, DiscountType, StartDate, EndDate, UsageLimit) VALUES
                                                                                               ('SUMMER2025', 50.00, 'FIXED', '2025-01-01', '2025-12-31', 100),
                                                                                               ('NEWYEAR2025', 20.00, 'PERCENT', '2025-01-01', '2025-01-31', 50),
                                                                                               ('SPRINGSALE', 10.00, 'FIXED', '2025-03-01', '2025-03-31', 200),
                                                                                               ('WINTER2025', 15.00, 'PERCENT', '2025-11-01', '2025-12-31', 150),
                                                                                               ('FALL2025', 30.00, 'FIXED', '2025-09-01', '2025-10-31', 100),
                                                                                               ('BLACKFRIDAY', 50.00, 'PERCENT', '2025-11-28', '2025-11-29', 300),
                                                                                               ('CYBERMONDAY', 40.00, 'PERCENT', '2025-12-02', '2025-12-03', 250),
                                                                                               ('EASTER2025', 15.00, 'FIXED', '2025-04-01', '2025-04-30', 100),
                                                                                               ('BACKTOSCHOOL', 20.00, 'PERCENT', '2025-08-01', '2025-08-31', 100),
                                                                                               ('VALENTINE2025', 25.00, 'FIXED', '2025-02-01', '2025-02-14', 50);


INSERT INTO Orders (UserID, Status, DiscountCode) VALUES
                                                      (2, 'PENDING', 'SUMMER2025'),
                                                      (3, 'PAID', 'NEWYEAR2025');

INSERT INTO OrderItems (OrderID, ModelID, Quantity) VALUES
                                                        (1, 1, 1), (1, 2, 1), (2, 3, 2);


-- Заполнение таблицы Reviews
INSERT INTO Reviews (UserID, ModelID, Text, Rating) VALUES
                                                        (2, 1, 'Great smartphone! Highly recommend.', 5),
                                                        (2, 3, 'Very informative book.', 4),
                                                        (3, 4, 'Nice T-shirt, fits well.', 5),
                                                        (4, 5, 'My kid loves it!', 4),
                                                        (5, 6, 'Great basketball for the price.', 5),
                                                        (6, 7, 'Good quality cream.', 4),
                                                        (7, 8, 'Sturdy dining table.', 5),
                                                        (8, 9, 'Very useful in the garden.', 4),
                                                        (9, 10, 'Fits perfectly in my car.', 5),
                                                        (10, 2, 'The vacuum cleaner is efficient.', 4);