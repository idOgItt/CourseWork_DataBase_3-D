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