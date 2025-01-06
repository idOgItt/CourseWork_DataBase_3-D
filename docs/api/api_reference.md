1. Пользователи (Users)
   Методы:

   CreateUser
   Вход: UserRequest { username, email, password, role }
   Выход: UserResponse { id, username, email, role }
   Описание: Регистрация нового пользователя.
   AuthenticateUser
   Вход: AuthRequest { email, password }
   Выход: AuthResponse { token, userId }
   Описание: Аутентификация и выдача токена.
   GetUserInfo
   Вход: UserIdRequest { id }
   Выход: UserResponse { id, username, email, role }
   Описание: Получение информации о пользователе.
   UpdateUser
   Вход: UpdateUserRequest { id, username, email, role }
   Выход: UserResponse { id, username, email, role }
   Описание: Обновление данных пользователя.

2. Категории и модели (Categories & Models)
   Методы:

   GetCategories
   Вход: Empty
   Выход: CategoriesResponse { categories: [Category { id, name }] }
   Описание: Получение списка категорий.
   GetModelsByCategory
   Вход: CategoryIdRequest { id }
   Выход: ModelsResponse { models: [Model { id, name, price, description, quantity, categoryId }] }
   Описание: Получение моделей для определенной категории.
   CreateModel
   Вход: ModelRequest { name, price, description, quantity, categoryId }
   Выход: ModelResponse { id, name, price, description, quantity, categoryId }
   Описание: Добавление новой модели.
   UpdateModel
   Вход: UpdateModelRequest { id, name, price, description, quantity, categoryId }
   Выход: ModelResponse { id, name, price, description, quantity, categoryId }
   Описание: Обновление модели.
   DeleteModel
   Вход: ModelIdRequest { id }
   Выход: OperationStatus { success, message }
   Описание: Удаление модели.

3. Заказы (Orders)
   Методы:

   CreateOrder
   Вход: OrderRequest { userId, items: [OrderItem { modelId, quantity }], discountCode }
   Выход: OrderResponse { id, totalAmount, status, createdDate, discountCode }
   Описание: Создание нового заказа.
   GetOrder
   Вход: OrderIdRequest { id }
   Выход: OrderResponse { id, totalAmount, status, createdDate, discountCode, items: [OrderItemResponse { modelId, quantity, price }] }
   Описание: Получение информации о заказе.
   UpdateOrderStatus
   Вход: UpdateOrderStatusRequest { id, status }
   Выход: OrderResponse { id, totalAmount, status, createdDate }
   Описание: Обновление статуса заказа.

4. Отзывы (Reviews)
   Методы:

   AddReview
   Вход: ReviewRequest { userId, modelId, rating, text }
   Выход: ReviewResponse { id, userId, modelId, rating, text, createdDate }
   Описание: Добавление отзыва для модели.
   GetReviewsForModel
   Вход: ModelIdRequest { id }
   Выход: ReviewsResponse { reviews: [Review { id, userId, modelId, rating, text, createdDate }] }
   Описание: Получение отзывов для модели.

5. Скидки (Discounts)
   Методы:

   ValidateDiscountCode
   Вход: DiscountCodeRequest { code }
   Выход: DiscountValidationResponse { valid, discountType, discountValue }
   Описание: Проверка валидности кода скидки.
   GetActiveDiscounts
   Вход: Empty
   Выход: DiscountsResponse { discounts: [Discount { code, discountType, discountValue, startDate, endDate }] }
   Описание: Получение всех активных скидок.

6. Логирование (Logs)
   Методы:

   GetLogs
   Вход: LogsRequest { filterOptions }
   Выход: LogsResponse { logs: [Log { id, userId, actionType, description, timestamp }] }
   Описание: Получение логов системы для администраторов.

7. Управление (Admin Panel)
   Методы:

   GetStatistics
   Вход: Empty
   Выход: StatisticsResponse { totalUsers, totalOrders, totalRevenue, topModels }
   Описание: Получение статистики для панели администратора.