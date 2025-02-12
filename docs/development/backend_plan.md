1. План реализации API
   Этап 1: Пользователи (Users)

   CreateUser: Реализация регистрации пользователя.
   AuthenticateUser: Реализация аутентификации и выдачи токена.
   GetUserInfo: Получение информации о пользователе.
   UpdateUser: Обновление данных пользователя.

Задачи:

    Создание контроллера для работы с пользователями.
    Реализация сервиса для регистрации и аутентификации.
    Создание репозитория для работы с пользователями (используя Spring Data JPA).
    Реализация JWT для аутентификации.

Этап 2: Категории и модели (Categories & Models)

    GetCategories: Получение списка категорий.
    GetModelsByCategory: Получение моделей для определенной категории.
    CreateModel: Добавление новой модели.
    UpdateModel: Обновление информации о модели.
    DeleteModel: Удаление модели.

Задачи:

    Создание контроллера для моделей и категорий.
    Создание сервиса для работы с моделями и категориями.
    Репозитории для категорий и моделей.
    Логика добавления, обновления и удаления моделей.

Этап 3: Заказы (Orders)

    CreateOrder: Создание нового заказа.
    GetOrder: Получение информации о заказе.
    UpdateOrderStatus: Обновление статуса заказа.

Задачи:

    Создание контроллера для работы с заказами.
    Сервис для создания заказов.
    Реализация логики оплаты (если необходимо).
    Обновление статусов заказов.

Этап 4: Отзывы (Reviews)

    AddReview: Добавление отзыва для модели.
    GetReviewsForModel: Получение отзывов для модели.

Задачи:

    Создание контроллера для отзывов.
    Реализация логики добавления и получения отзывов.
    Сервис для обработки отзывов.

Этап 5: Скидки (Discounts)

    ValidateDiscountCode: Проверка валидности скидочного кода.
    GetActiveDiscounts: Получение всех активных скидок.

Задачи:

    Создание контроллера для скидок.
    Логика валидации скидочных кодов.
    Сервис для обработки активных скидок.

Этап 6: Логирование (Logs)

    GetLogs: Получение логов действий пользователей и администраторов.

Задачи:

    Контроллер для логов.
    Реализация сервиса для логирования действий.
    Хранение и доступ к логам (можно использовать таблицу logs).

Этап 7: Управление (Admin Panel)

    GetStatistics: Получение статистики по пользователям, заказам, доходам и популярным моделям.

Задачи:

    Контроллер для получения статистики.
    Сервис для сбора статистических данных.
    Получение и представление данных (например, через v_user_statistics, v_orders_summary).

3. Пошаговый процесс
   Шаг 0 (DONE): Role, Privilige. 
   Шаг 1: Реализуем UserController, UserService и UserRepository для регистрации пользователей и аутентификации с JWT.
   Шаг 2: Реализуем ProductController, ProductService и CategoryRepository для работы с моделями и категориями.
   Шаг 3: Создаем OrderController, OrderService и OrderRepository для обработки заказов.
   Шаг 4: Создаем ReviewController, ReviewService и ReviewRepository для обработки отзывов.
   Шаг 5: Реализуем DiscountController, DiscountService и DiscountRepository для обработки скидок.
   Шаг 6: Создаем LogController, LogService и LogRepository для обработки логов.
   Шаг 7: Реализуем AdminController и AdminService для получения статистики.

4. Классы
    Category.java
    Discount.java
    Role.java и Permission.java (если еще не созданы)
    Image.java и UserImage.java
    Model.java
    Order.java и OrderItem.java
    Payment.java
    Review.java
    User.java

Причина:

    Category и Discount не зависят от других классов.
    Role и Permission должны быть созданы до User, так как User зависит от Role.
    Image и UserImage можно создать до User, так как они связываются с моделью и пользователем, но User не зависит от них.
    Model, Order, OrderItem, Payment, и Review зависят от User и других сущностей, поэтому они должны быть созданы после User.