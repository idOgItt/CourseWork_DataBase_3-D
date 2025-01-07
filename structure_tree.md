./
├── .env
├── .gitignore
├── 3D-Model_Market.iml
├── README.md
├── backend
│   ├── Dockerfile
│   ├── build
│   │   ├── classes
│   │   │   └── java
│   │   │       ├── main
│   │   │       │   └── com
│   │   │       │       └── threed_model_market
│   │   │       │           └── project
│   │   │       │               ├── Application.class
│   │   │       │               └── config
│   │   │       │                   ├── CorsConfig.class
│   │   │       │                   └── RestApiConfig.class
│   │   │       └── test
│   │   ├── generated
│   │   │   └── sources
│   │   │       ├── annotationProcessor
│   │   │       │   └── java
│   │   │       │       ├── main
│   │   │       │       └── test
│   │   │       └── headers
│   │   │           └── java
│   │   │               ├── main
│   │   │               └── test
│   │   ├── libs
│   │   │   ├── 3D-Model_Market-1.0.0-plain.jar
│   │   │   └── 3D-Model_Market-1.0.0.jar
│   │   ├── reports
│   │   │   └── problems
│   │   │       └── problems-report.html
│   │   └── tmp
│   │       ├── bootJar
│   │       │   └── MANIFEST.MF
│   │       ├── compileJava
│   │       │   └── previous-compilation-data.bin
│   │       ├── compileTestJava
│   │       │   └── previous-compilation-data.bin
│   │       └── jar
│   │           └── MANIFEST.MF
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
│   │   │   ├── java
│   │   │   │   └── com
│   │   │   │       └── threed_model_market
│   │   │   │           └── project
│   │   │   │               ├── Application.java
│   │   │   │               ├── Category.java
│   │   │   │               ├── Discount.java
│   │   │   │               ├── Image.java
│   │   │   │               ├── Model.java
│   │   │   │               ├── Order.java
│   │   │   │               ├── Orderitem.java
│   │   │   │               ├── Payment.java
│   │   │   │               ├── Review.java
│   │   │   │               ├── UserImage.java
│   │   │   │               ├── config
│   │   │   │               │   ├── CorsConfig.java
│   │   │   │               │   ├── PasswordConfig.java
│   │   │   │               │   └── RestApiConfig.java
│   │   │   │               ├── contoller
│   │   │   │               │   ├── OrderController.java
│   │   │   │               │   ├── PermissionController.java
│   │   │   │               │   ├── ProductController.java
│   │   │   │               │   ├── RoleController.java
│   │   │   │               │   └── UserController.java
│   │   │   │               ├── dto
│   │   │   │               │   ├── CategoryDto.java
│   │   │   │               │   ├── OrderDto.java
│   │   │   │               │   ├── PaymentDto.java
│   │   │   │               │   ├── PermissionDto.java
│   │   │   │               │   ├── ProductDto.java
│   │   │   │               │   ├── RoleDto.java
│   │   │   │               │   └── UserDto.java
│   │   │   │               ├── exception_handler
│   │   │   │               │   ├── GlobalExceptionHandler.java
│   │   │   │               │   ├── OrderNotFoundException.java
│   │   │   │               │   ├── PermissionExceptionHandler.java
│   │   │   │               │   ├── ProductNotFoundException.java
│   │   │   │               │   ├── RoleExceptionHandler.java
│   │   │   │               │   ├── UserExceptionHandler.java
│   │   │   │               │   └── exceptions
│   │   │   │               │       ├── Patch
│   │   │   │               │       │   └── PatchOperationNotSupportedException.java
│   │   │   │               │       ├── Permission
│   │   │   │               │       │   └── PermissionNotFoundException.java
│   │   │   │               │       ├── Role
│   │   │   │               │       │   └── RoleNotFoundException.java
│   │   │   │               │       ├── Security
│   │   │   │               │       │   └── UnauthorizedAccessException.java
│   │   │   │               │       └── User
│   │   │   │               │           ├── UserAlreadyExistsException.java
│   │   │   │               │           ├── UserInvalidMailFormatException.java
│   │   │   │               │           ├── UserInvalidPasswordException.java
│   │   │   │               │           ├── UserNotFoundException.java
│   │   │   │               │           └── UserNotFoundMailException.java
│   │   │   │               ├── model
│   │   │   │               │   ├── Category.java
│   │   │   │               │   ├── Order.java
│   │   │   │               │   ├── OrderItem.java
│   │   │   │               │   ├── Payment.java
│   │   │   │               │   ├── Permission.java
│   │   │   │               │   ├── Product.java
│   │   │   │               │   ├── Role.java
│   │   │   │               │   └── User.java
│   │   │   │               ├── repository
│   │   │   │               │   ├── CategoryRepository.java
│   │   │   │               │   ├── OrderItemRepository.java
│   │   │   │               │   ├── OrderRepository.java
│   │   │   │               │   ├── PaymentRepository.java
│   │   │   │               │   ├── PermissionRepository.java
│   │   │   │               │   ├── ProductRepository.java
│   │   │   │               │   ├── RoleRepository.java
│   │   │   │               │   └── UserRepository.java
│   │   │   │               ├── security
│   │   │   │               │   ├── Constants.java
│   │   │   │               │   ├── CustomUserDetails.java
│   │   │   │               │   ├── CustomUserDetailsService.java
│   │   │   │               │   ├── JwtAuthenticationFilter.java
│   │   │   │               │   ├── JwtTokenProvider.java
│   │   │   │               │   └── SecurityConfig.java
│   │   │   │               ├── service
│   │   │   │               │   ├── AuthenticationService.java
│   │   │   │               │   ├── CategoryService.java
│   │   │   │               │   ├── OrderItemService.java
│   │   │   │               │   ├── OrderService.java
│   │   │   │               │   ├── PaymentService.java
│   │   │   │               │   ├── PermissionService.java
│   │   │   │               │   ├── ProductService.java
│   │   │   │               │   ├── RoleService.java
│   │   │   │               │   ├── UserService.java
│   │   │   │               │   └── impl
│   │   │   │               │       ├── AuthenticationServiceImpl.java
│   │   │   │               │       ├── CategoryServiceImpl.java
│   │   │   │               │       ├── OrderServiceImpl.java
│   │   │   │               │       ├── PermissionServiceImpl.java
│   │   │   │               │       ├── ProductServiceImpl.java
│   │   │   │               │       ├── RoleServiceImpl.java
│   │   │   │               │       └── UserServiceImpl.java
│   │   │   │               └── util
│   │   │   │                   └── ValidationUtils.java
│   │   │   └── main.iml
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
│   ├── migrations
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
│   │   ├── backend_plan.md
│   │   ├── development_plan.md
│   │   └── structure.md
│   ├── requirements
│   │   ├── DB_MAI.pdf
│   │   └── requirements.md
│   ├── testing
│   │   └── test_plan.md
│   └── user_guide.md
├── frontend
│   ├── .gitignore
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
├── lib
│   ├── javax.annotation.jar
│   ├── javax.ejb.jar
│   ├── javax.jms.jar
│   ├── javax.persistence.jar
│   ├── javax.resource.jar
│   ├── javax.servlet.jsp.jar
│   ├── javax.servlet.jsp.jstl.jar
│   └── javax.transaction.jar
├── logs
├── out
│   └── production
│       └── 3D-Model_Market
│           ├── application.yml
│           └── main.iml
├── structure_tree.md
├── tree.py
└── wait-for-it.sh