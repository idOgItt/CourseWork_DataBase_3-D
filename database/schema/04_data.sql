INSERT INTO Roles (RoleName) VALUES ('ADMIN'), ('CLIENT'), ('GUEST');

INSERT INTO Permissions (PermissionName, Description) VALUES
                                                          ('MANAGE_PERMISSIONS', 'Управление разрешениями'),
                                                          ('VIEW_PERMISSIONS', 'Просмотр разрешений'),
                                                          ('VIEW_ROLES', 'Просмотр ролей'),
                                                          ('MANAGE_ROLES', 'Управление ролями'),
                                                          ('VIEW_USERS', 'Просмотр пользователей'),
                                                          ('MANAGE_USERS', 'Управление пользователями'),
                                                          ('UPLOAD_USER_IMAGES', 'Загрузка пользовательских изображений'),
                                                          ('VIEW_USER_IMAGES', 'Просмотр пользовательских изображений'),
                                                          ('MANAGE_USER_IMAGES', 'Управление пользовательскими изображениями'),
                                                          ('DELETE_USER_IMAGES', 'Удаление пользовательских изображений'),
                                                          ('MANAGE_PAYMENT_STATUSES', 'Управление статусами платежей'),
                                                          ('VIEW_PAYMENT_STATUSES', 'Просмотр статусов платежей'),
                                                          ('MANAGE_ORDER_STATUSES', 'Управление статусами заказов'),
                                                          ('VIEW_ORDER_STATUSES', 'Просмотр статусов заказов'),
                                                          ('VIEW_MODEL_STATUSES', 'Просмотр статусов моделей'),
                                                          ('MANAGE_MODEL_STATUSES', 'Управление статусами моделей'),
                                                          ('MANAGE_DISCOUNT_TYPES', 'Управление типами скидок'),
                                                          ('VIEW_DISCOUNT_TYPES', 'Просмотр типов скидок'),
                                                          ('VIEW_DISCOUNT_ANALYSIS', 'Просмотр анализа скидок'),
                                                          ('VIEW_MODELS_POPULARITY', 'Просмотр популярности моделей'),
                                                          ('VIEW_ORDER_DETAILS', 'Просмотр деталей заказов'),
                                                          ('VIEW_ORDERS_SUMMARY', 'Просмотр сводки заказов'),
                                                          ('VIEW_USER_STATISTICS', 'Просмотр статистики пользователей'),
                                                          ('UPLOAD_IMAGES', 'Загрузка изображений'),
                                                          ('VIEW_IMAGES', 'Просмотр изображений'),
                                                          ('MANAGE_IMAGES', 'Управление изображениями'),
                                                          ('DELETE_IMAGES', 'Удаление изображений'),
                                                          ('MANAGE_CATEGORIES', 'Управление категориями'),
                                                          ('VIEW_CATEGORIES', 'Просмотр категорий'),
                                                          ('VIEW_DISCOUNTS', 'Просмотр скидок'),
                                                          ('MANAGE_DISCOUNTS', 'Управление скидками'),
                                                          ('ADD_REVIEWS', 'Добавление отзывов'),
                                                          ('VIEW_REVIEWS', 'Просмотр отзывов'),
                                                          ('DELETE_REVIEWS', 'Удаление отзывов'),
                                                          ('MANAGE_MODELS', 'Управление моделями'),
                                                          ('VIEW_MODELS', 'Просмотр моделей'),
                                                          ('MANAGE_ORDER_ITEMS', 'Управление элементами заказа'),
                                                          ('VIEW_ORDER_ITEMS', 'Просмотр элементов заказа'),
                                                          ('CREATE_PAYMENTS', 'Создание платежей'),
                                                          ('VIEW_PAYMENTS', 'Просмотр платежей'),
                                                          ('DELETE_PAYMENTS', 'Удаление платежей'),
                                                          ('CREATE_ORDERS', 'Создание заказов'),
                                                          ('VIEW_ORDERS', 'Просмотр заказов'),
                                                          ('DELETE_ORDERS', 'Удаление заказов'),
                                                          ('UPDATE_ORDERS', 'Обновление заказов'),
                                                          ('MANAGE_PAYMENT_METHODS', 'Управление методами оплаты'),
                                                          ('VIEW_PAYMENT_METHODS', 'Просмотр методов оплаты');

INSERT INTO RolePermissions (RoleID, PermissionID)
SELECT 1 AS RoleID, PermissionID FROM Permissions
UNION ALL
SELECT 2 AS RoleID, PermissionID FROM Permissions
UNION ALL
SELECT 3 AS RoleID, PermissionID FROM Permissions;


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
                                                                    (11, 'USER_DELETE', 'User deleted a record'),
                                                                    (12, 'DISCOUNTS_DELETE', 'Discount deleted'),
                                                                    (13, 'IMAGES_INSERT', 'Image inserted'),
                                                                    (14, 'MODELS_QUANTITY_UPDATE', 'Model quantity updated'),
                                                                    (15, 'CATEGORIES_INSERT', 'Category inserted'),
                                                                    (16, 'CATEGORIES_UPDATE', 'Category updated'),
                                                                    (999, 'EXCEPTION_LOG', 'Log for exceptions and errors');

INSERT INTO SuspiciousActionTypes (ActionTypeID, ActionName, Description) VALUES
                                                                              (998, 'SUSPICIOUS_LOG', 'Log for suspicious actions'),
                                                                              (999, 'EXCEPTION_LOG', 'Log for exceptions and errors');
INSERT INTO DiscountTypes (DiscountTypeID, Name, Description) VALUES
                                                                  (1, 'FIXED', 'Fixed amount discount'),
                                                                  (2, 'PERCENT', 'Percentage-based discount');
INSERT INTO ModelStatuses (Name, Description) VALUES
    ('PENDING', 'Model is pending approval or processing');

INSERT INTO OrderStatuses (Name, Description) VALUES
    ('PENDING', 'Order is pending approval or processing');

INSERT INTO PaymentStatuses (Name, Description) VALUES
                                                    ('PENDING', 'Payment is pending processing'),
                                                    ('PAID', 'Payment has been completed successfully');

