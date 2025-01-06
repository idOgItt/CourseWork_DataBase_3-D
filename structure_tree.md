./
├── .env
├── .gitignore
├── 3D-Model_Market.iml
├── README.md
├── backend
│   ├── Dockerfile
│   ├── build.gradle
│   ├── gradle
│   │   └── wrapper
│   │       ├── gradle-wrapper.jar
│   │       └── gradle-wrapper.properties
│   ├── gradlew
│   ├── gradlew.bat
│   ├── settings.gradle
│   ├── src
│   │   ├── main
│   │   │   └── java
│   │   │       └── com
│   │   │           └── threed_model_market
│   │   │               └── project
│   │   │                   ├── Application.java
│   │   │                   ├── config
│   │   │                   │   ├── RestApiConfig.java
│   │   │                   │   └── SecurityConfig.java
│   │   │                   ├── contoller
│   │   │                   │   ├── OrderController.java
│   │   │                   │   ├── ProductController.java
│   │   │                   │   └── UserController.java
│   │   │                   ├── dto
│   │   │                   │   ├── CategoryDto.java
│   │   │                   │   ├── OrderDto.java
│   │   │                   │   ├── PaymentDto.java
│   │   │                   │   ├── ProductDto.java
│   │   │                   │   └── UserDto.java
│   │   │                   ├── exception
│   │   │                   │   ├── GlobalExceptionHandler.java
│   │   │                   │   ├── OrderNotFoundException.java
│   │   │                   │   └── ProductNotFoundException.java
│   │   │                   ├── model
│   │   │                   │   ├── Category.java
│   │   │                   │   ├── Order.java
│   │   │                   │   ├── OrderItem.java
│   │   │                   │   ├── Payment.java
│   │   │                   │   ├── Product.java
│   │   │                   │   └── User.java
│   │   │                   ├── repository
│   │   │                   │   ├── CategoryRepository.java
│   │   │                   │   ├── OrderItemRepository.java
│   │   │                   │   ├── OrderRepository.java
│   │   │                   │   ├── PaymentRepository.java
│   │   │                   │   ├── ProductRepository.java
│   │   │                   │   └── UserRepository.java
│   │   │                   ├── service
│   │   │                   │   ├── CategoryService.java
│   │   │                   │   ├── OrderItemService.java
│   │   │                   │   ├── OrderService.java
│   │   │                   │   ├── PaymentService.java
│   │   │                   │   ├── ProductService.java
│   │   │                   │   ├── UserService.java
│   │   │                   │   └── impl
│   │   │                   │       ├── CategoryServiceImpl.java
│   │   │                   │       ├── OrderServiceImpl.java
│   │   │                   │       └── ProductServiceImpl.java
│   │   │                   └── util
│   │   │                       ├── DateUtils.java
│   │   │                       └── ValidationUtils.java
│   │   ├── resources
│   │   │   └── application.yml
│   │   └── test
│   │       └── java
│   │           └── com
│   │               └── threed_model_market
│   │                   └── project
│   │                       ├── ApplicationTest.java
│   │                       ├── OrderControllerTest.java
│   │                       ├── ProductControllerTest.java
│   │                       ├── ProductRepositoryTest.java
│   │                       └── ProductServiceTest.java
│   └── tests
├── build_and_run.sh
├── database
│   ├── Dockerfile
│   ├── README.md
│   ├── backups
│   └── schema
│       ├── 01_init.sql
│       ├── 02_functions.sql
│       ├── 03_triggers.sql
│       ├── 04_data.sql
│       ├── 05_views.sql
│       └── test
│           ├── quiries.sql
│           └── test_data.sql
├── docker-compose.yml
├── docs
│   ├── api
│   │   └── api_reference.md
│   ├── architecture.md
│   ├── database
│   │   ├── scheme_descriprion.md
│   │   └── scheme_diagram.png
│   ├── deployment
│   │   ├── dev_guide.md
│   │   └── prod_guide.md
│   ├── developer_guide.md
│   ├── development
│   │   ├── development_plan.md
│   │   └── structure.md
│   ├── requirements
│   │   ├── DB_MAI.pdf
│   │   └── requirements.md
│   ├── testing
│   │   └── test_plan.md
│   └── user_guide.md
├── frontend
│   ├── Dockerfile
│   ├── index.html
│   ├── node_modules
│   ├── package.json
│   ├── public
│   ├── src
│   │   ├── App.js
│   │   ├── assets
│   │   ├── components
│   │   ├── index.js
│   │   └── styles
│   ├── tests
│   ├── vite.config.js
│   └── yarn.lock
├── logs
├── structure_tree.md
├── tree.py
└── wait-for-it.sh