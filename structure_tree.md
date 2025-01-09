./
├── .env
├── .gitattributes
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
│   │   │       │               ├── config
│   │   │       │               │   ├── CorsConfig.class
│   │   │       │               │   ├── PasswordConfig.class
│   │   │       │               │   └── RestApiConfig.class
│   │   │       │               ├── controller
│   │   │       │               │   ├── CategoryController.class
│   │   │       │               │   ├── DiscountController.class
│   │   │       │               │   ├── DiscountTypeController.class
│   │   │       │               │   ├── ImageController.class
│   │   │       │               │   ├── ModelController.class
│   │   │       │               │   ├── ModelStatusController.class
│   │   │       │               │   ├── OrderController.class
│   │   │       │               │   ├── OrderItemController.class
│   │   │       │               │   ├── OrderStatusController.class
│   │   │       │               │   ├── PaymentController.class
│   │   │       │               │   ├── PaymentMethodController.class
│   │   │       │               │   ├── PaymentStatusController.class
│   │   │       │               │   ├── PermissionController.class
│   │   │       │               │   ├── ReviewController.class
│   │   │       │               │   ├── RoleController.class
│   │   │       │               │   ├── UserController.class
│   │   │       │               │   ├── UserImageController.class
│   │   │       │               │   └── Views
│   │   │       │               │       ├── VDiscountsAnalysisController.class
│   │   │       │               │       ├── VLogsSummaryController.class
│   │   │       │               │       ├── VModelsPopularityController.class
│   │   │       │               │       ├── VOrderDetailController.class
│   │   │       │               │       ├── VOrdersSummaryController.class
│   │   │       │               │       ├── VReviewsSummaryController.class
│   │   │       │               │       └── VUserStatisticController.class
│   │   │       │               ├── dto
│   │   │       │               │   ├── CategoryDto.class
│   │   │       │               │   ├── DiscountDto.class
│   │   │       │               │   ├── DiscountTypeDto.class
│   │   │       │               │   ├── ImageDto.class
│   │   │       │               │   ├── ModelDto.class
│   │   │       │               │   ├── ModelStatusDto.class
│   │   │       │               │   ├── OrderDto.class
│   │   │       │               │   ├── OrderItemDto.class
│   │   │       │               │   ├── OrderStatusDto.class
│   │   │       │               │   ├── PaymentDto.class
│   │   │       │               │   ├── PaymentMethodDto.class
│   │   │       │               │   ├── PaymentStatusDto.class
│   │   │       │               │   ├── PermissionDto.class
│   │   │       │               │   ├── ReviewDto.class
│   │   │       │               │   ├── RoleDto.class
│   │   │       │               │   ├── UserDto.class
│   │   │       │               │   ├── UserImageDto.class
│   │   │       │               │   └── Views
│   │   │       │               │       ├── VDiscountsAnalysisDto.class
│   │   │       │               │       ├── VLogsSummaryDto.class
│   │   │       │               │       ├── VModelsPopularityDto.class
│   │   │       │               │       ├── VOrderDetailDto.class
│   │   │       │               │       ├── VOrdersSummaryDto.class
│   │   │       │               │       ├── VReviewsSummaryDto.class
│   │   │       │               │       └── VUserStatisticDto.class
│   │   │       │               ├── exception_handler
│   │   │       │               │   ├── CategoryExceptionHandler.class
│   │   │       │               │   ├── DiscountExceptionHandler.class
│   │   │       │               │   ├── GlobalExceptionHandler.class
│   │   │       │               │   ├── ImageExceptionHandler.class
│   │   │       │               │   ├── ModelExceptionHandler.class
│   │   │       │               │   ├── OrderExceptionHandler.class
│   │   │       │               │   ├── PaymentExceptionHandler.class
│   │   │       │               │   ├── PermissionExceptionHandler.class
│   │   │       │               │   ├── ReviewExceptionHandler.class
│   │   │       │               │   ├── RoleExceptionHandler.class
│   │   │       │               │   ├── UserExceptionHandler.class
│   │   │       │               │   ├── UserImageExceptionHandler.class
│   │   │       │               │   └── exceptions
│   │   │       │               │       ├── Category
│   │   │       │               │       │   └── CategoryNotFoundException.class
│   │   │       │               │       ├── Discount
│   │   │       │               │       │   └── DiscountNotFoundException.class
│   │   │       │               │       ├── DiscountType
│   │   │       │               │       │   └── DiscountTypeNotFoundException.class
│   │   │       │               │       ├── Image
│   │   │       │               │       │   └── ImageNotFoundException.class
│   │   │       │               │       ├── Log
│   │   │       │               │       │   └── LogNotFoundException.class
│   │   │       │               │       ├── Model
│   │   │       │               │       │   └── ModelNotFoundException.class
│   │   │       │               │       ├── ModelStatus
│   │   │       │               │       │   └── ModelStatusNotFoundException.class
│   │   │       │               │       ├── Order
│   │   │       │               │       │   └── OrderNotFoundException.class
│   │   │       │               │       ├── OrderStatus
│   │   │       │               │       │   └── OrderStatusNotFoundException.class
│   │   │       │               │       ├── Patch
│   │   │       │               │       │   └── PatchOperationNotSupportedException.class
│   │   │       │               │       ├── Payment
│   │   │       │               │       │   └── PaymentNotFoundException.class
│   │   │       │               │       ├── PaymentMethod
│   │   │       │               │       │   └── PaymentMethodNotFoundException.class
│   │   │       │               │       ├── PaymentStatus
│   │   │       │               │       │   └── PaymentStatusNotFoundException.class
│   │   │       │               │       ├── Permission
│   │   │       │               │       │   └── PermissionNotFoundException.class
│   │   │       │               │       ├── Review
│   │   │       │               │       │   └── ReviewNotFoundException.class
│   │   │       │               │       ├── Role
│   │   │       │               │       │   └── RoleNotFoundException.class
│   │   │       │               │       ├── Security
│   │   │       │               │       │   └── UnauthorizedAccessException.class
│   │   │       │               │       ├── User
│   │   │       │               │       │   ├── UserAlreadyExistsException.class
│   │   │       │               │       │   ├── UserInvalidMailFormatException.class
│   │   │       │               │       │   ├── UserInvalidPasswordException.class
│   │   │       │               │       │   ├── UserNotFoundException.class
│   │   │       │               │       │   └── UserNotFoundMailException.class
│   │   │       │               │       └── UserImage
│   │   │       │               │           └── UserImageNotFoundException.class
│   │   │       │               ├── model
│   │   │       │               │   ├── Category.class
│   │   │       │               │   ├── Discount.class
│   │   │       │               │   ├── DiscountType.class
│   │   │       │               │   ├── Image.class
│   │   │       │               │   ├── Model.class
│   │   │       │               │   ├── ModelStatus.class
│   │   │       │               │   ├── Order.class
│   │   │       │               │   ├── OrderItem.class
│   │   │       │               │   ├── OrderStatus.class
│   │   │       │               │   ├── Payment.class
│   │   │       │               │   ├── PaymentMethod.class
│   │   │       │               │   ├── PaymentStatus.class
│   │   │       │               │   ├── Permission.class
│   │   │       │               │   ├── Review.class
│   │   │       │               │   ├── Role.class
│   │   │       │               │   ├── User$UserBuilder.class
│   │   │       │               │   ├── User.class
│   │   │       │               │   ├── UserImage.class
│   │   │       │               │   └── Views
│   │   │       │               │       ├── VDiscountsAnalysis.class
│   │   │       │               │       ├── VLogsSummary.class
│   │   │       │               │       ├── VModelsPopularity.class
│   │   │       │               │       ├── VOrderDetail.class
│   │   │       │               │       ├── VOrdersSummary.class
│   │   │       │               │       ├── VReviewsSummary.class
│   │   │       │               │       └── VUserStatistic.class
│   │   │       │               ├── repository
│   │   │       │               │   ├── CategoryRepository.class
│   │   │       │               │   ├── DiscountRepository.class
│   │   │       │               │   ├── DiscountTypeRepository.class
│   │   │       │               │   ├── ImageRepository.class
│   │   │       │               │   ├── ModelRepository.class
│   │   │       │               │   ├── ModelStatusRepository.class
│   │   │       │               │   ├── OrderItemRepository.class
│   │   │       │               │   ├── OrderRepository.class
│   │   │       │               │   ├── OrderStatusRepository.class
│   │   │       │               │   ├── PaymentMethodRepository.class
│   │   │       │               │   ├── PaymentRepository.class
│   │   │       │               │   ├── PaymentStatusRepository.class
│   │   │       │               │   ├── PermissionRepository.class
│   │   │       │               │   ├── ReviewRepository.class
│   │   │       │               │   ├── RoleRepository.class
│   │   │       │               │   ├── UserImageRepository.class
│   │   │       │               │   ├── UserRepository.class
│   │   │       │               │   └── Views
│   │   │       │               │       ├── VDiscountsAnalysisRepository.class
│   │   │       │               │       ├── VLogsSummaryRepository.class
│   │   │       │               │       ├── VModelsPopularityRepository.class
│   │   │       │               │       ├── VOrderDetailRepository.class
│   │   │       │               │       ├── VOrdersSummaryRepository.class
│   │   │       │               │       ├── VReviewsSummaryRepository.class
│   │   │       │               │       └── VUserStatisticRepository.class
│   │   │       │               ├── security
│   │   │       │               │   ├── Constants.class
│   │   │       │               │   ├── CustomUserDetails.class
│   │   │       │               │   ├── CustomUserDetailsService.class
│   │   │       │               │   ├── JwtAuthenticationFilter.class
│   │   │       │               │   ├── JwtTokenProvider.class
│   │   │       │               │   └── SecurityConfig.class
│   │   │       │               ├── service
│   │   │       │               │   ├── CategoryService.class
│   │   │       │               │   ├── DiscountService.class
│   │   │       │               │   ├── DiscountTypeService.class
│   │   │       │               │   ├── ImageService.class
│   │   │       │               │   ├── ModelService.class
│   │   │       │               │   ├── ModelStatusService.class
│   │   │       │               │   ├── OrderItemService.class
│   │   │       │               │   ├── OrderService.class
│   │   │       │               │   ├── OrderStatusService.class
│   │   │       │               │   ├── PaymentMethodService.class
│   │   │       │               │   ├── PaymentService.class
│   │   │       │               │   ├── PaymentStatusService.class
│   │   │       │               │   ├── PermissionService.class
│   │   │       │               │   ├── ReviewService.class
│   │   │       │               │   ├── RoleService.class
│   │   │       │               │   ├── UserImageService.class
│   │   │       │               │   ├── UserService.class
│   │   │       │               │   ├── Views
│   │   │       │               │   │   ├── VDiscountsAnalysisService.class
│   │   │       │               │   │   ├── VLogsSummaryService.class
│   │   │       │               │   │   ├── VModelsPopularityService.class
│   │   │       │               │   │   ├── VOrderDetailService.class
│   │   │       │               │   │   ├── VOrdersSummaryService.class
│   │   │       │               │   │   ├── VReviewsSummaryService.class
│   │   │       │               │   │   └── VUserStatisticService.class
│   │   │       │               │   └── impl
│   │   │       │               │       ├── CategoryServiceImpl.class
│   │   │       │               │       ├── DiscountServiceImpl.class
│   │   │       │               │       ├── DiscountTypeServiceImpl.class
│   │   │       │               │       ├── ImageServiceImpl.class
│   │   │       │               │       ├── ModelServiceImpl.class
│   │   │       │               │       ├── ModelStatusServiceImpl.class
│   │   │       │               │       ├── OrderItemServiceImpl.class
│   │   │       │               │       ├── OrderServiceImpl.class
│   │   │       │               │       ├── OrderStatusServiceImpl.class
│   │   │       │               │       ├── PaymentMethodServiceImpl.class
│   │   │       │               │       ├── PaymentServiceImpl.class
│   │   │       │               │       ├── PaymentStatusServiceImpl.class
│   │   │       │               │       ├── PermissionServiceImpl.class
│   │   │       │               │       ├── ReviewServiceImpl.class
│   │   │       │               │       ├── RoleServiceImpl.class
│   │   │       │               │       ├── UserImageServiceImpl.class
│   │   │       │               │       ├── UserServiceImpl.class
│   │   │       │               │       └── Views
│   │   │       │               │           ├── VDiscountsAnalysisServiceImpl.class
│   │   │       │               │           ├── VLogsSummaryServiceImpl.class
│   │   │       │               │           ├── VModelsPopularityServiceImpl.class
│   │   │       │               │           ├── VOrderDetailServiceImpl.class
│   │   │       │               │           ├── VOrdersSummaryServiceImpl.class
│   │   │       │               │           ├── VReviewsSummaryServiceImpl.class
│   │   │       │               │           └── VUserStatisticServiceImpl.class
│   │   │       │               └── util
│   │   │       │                   ├── AccessValidator.class
│   │   │       │                   ├── AuthenticationService.class
│   │   │       │                   ├── AuthenticationServiceImpl.class
│   │   │       │                   └── ValidationUtils.class
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
│   │   ├── resources
│   │   │   └── main
│   │   │       ├── META-INF
│   │   │       │   └── additional-spring-configuration-metadata.json
│   │   │       ├── application.yml
│   │   │       └── db
│   │   │           └── migration
│   │   │               └── V001_add_additional_statuses_and_payment_methods.sql
│   │   └── tmp
│   │       ├── bootJar
│   │       │   └── MANIFEST.MF
│   │       ├── compileJava
│   │       │   ├── compileTransaction
│   │       │   │   ├── backup-dir
│   │       │   │   └── stash-dir
│   │       │   │       ├── Payment.class.uniqueId10
│   │       │   │       ├── PaymentController.class.uniqueId11
│   │       │   │       ├── PaymentMethod.class.uniqueId8
│   │       │   │       ├── PaymentMethodController.class.uniqueId4
│   │       │   │       ├── PaymentMethodRepository.class.uniqueId7
│   │       │   │       ├── PaymentMethodService.class.uniqueId2
│   │       │   │       ├── PaymentMethodServiceImpl.class.uniqueId13
│   │       │   │       ├── PaymentRepository.class.uniqueId12
│   │       │   │       ├── PaymentService.class.uniqueId1
│   │       │   │       ├── PaymentServiceImpl.class.uniqueId14
│   │       │   │       ├── PaymentStatus.class.uniqueId9
│   │       │   │       ├── PaymentStatusController.class.uniqueId6
│   │       │   │       ├── PaymentStatusRepository.class.uniqueId0
│   │       │   │       ├── PaymentStatusService.class.uniqueId5
│   │       │   │       └── PaymentStatusServiceImpl.class.uniqueId3
│   │       │   └── previous-compilation-data.bin
│   │       ├── compileTestJava
│   │       │   └── previous-compilation-data.bin
│   │       └── jar
│   │           └── MANIFEST.MF
│   ├── build.gradle
│   ├── docker-entrypoint.sh
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
│   │   │   │               ├── config
│   │   │   │               │   ├── CorsConfig.java
│   │   │   │               │   ├── PasswordConfig.java
│   │   │   │               │   └── RestApiConfig.java
│   │   │   │               ├── controller
│   │   │   │               │   ├── CategoryController.java
│   │   │   │               │   ├── DiscountController.java
│   │   │   │               │   ├── DiscountTypeController.java
│   │   │   │               │   ├── ImageController.java
│   │   │   │               │   ├── ModelController.java
│   │   │   │               │   ├── ModelStatusController.java
│   │   │   │               │   ├── OrderController.java
│   │   │   │               │   ├── OrderItemController.java
│   │   │   │               │   ├── OrderStatusController.java
│   │   │   │               │   ├── PaymentController.java
│   │   │   │               │   ├── PaymentMethodController.java
│   │   │   │               │   ├── PaymentStatusController.java
│   │   │   │               │   ├── PermissionController.java
│   │   │   │               │   ├── ReviewController.java
│   │   │   │               │   ├── RoleController.java
│   │   │   │               │   ├── UserController.java
│   │   │   │               │   ├── UserImageController.java
│   │   │   │               │   └── Views
│   │   │   │               │       ├── VDiscountsAnalysisController.java
│   │   │   │               │       ├── VLogsSummaryController.java
│   │   │   │               │       ├── VModelsPopularityController.java
│   │   │   │               │       ├── VOrderDetailController.java
│   │   │   │               │       ├── VOrdersSummaryController.java
│   │   │   │               │       ├── VReviewsSummaryController.java
│   │   │   │               │       └── VUserStatisticController.java
│   │   │   │               ├── dto
│   │   │   │               │   ├── CategoryDto.java
│   │   │   │               │   ├── DiscountDto.java
│   │   │   │               │   ├── DiscountTypeDto.java
│   │   │   │               │   ├── ImageDto.java
│   │   │   │               │   ├── ModelDto.java
│   │   │   │               │   ├── ModelStatusDto.java
│   │   │   │               │   ├── OrderDto.java
│   │   │   │               │   ├── OrderItemDto.java
│   │   │   │               │   ├── OrderStatusDto.java
│   │   │   │               │   ├── PaymentDto.java
│   │   │   │               │   ├── PaymentMethodDto.java
│   │   │   │               │   ├── PaymentStatusDto.java
│   │   │   │               │   ├── PermissionDto.java
│   │   │   │               │   ├── ReviewDto.java
│   │   │   │               │   ├── RoleDto.java
│   │   │   │               │   ├── UserDto.java
│   │   │   │               │   ├── UserImageDto.java
│   │   │   │               │   └── Views
│   │   │   │               │       ├── VDiscountsAnalysisDto.java
│   │   │   │               │       ├── VLogsSummaryDto.java
│   │   │   │               │       ├── VModelsPopularityDto.java
│   │   │   │               │       ├── VOrderDetailDto.java
│   │   │   │               │       ├── VOrdersSummaryDto.java
│   │   │   │               │       ├── VReviewsSummaryDto.java
│   │   │   │               │       └── VUserStatisticDto.java
│   │   │   │               ├── exception_handler
│   │   │   │               │   ├── CategoryExceptionHandler.java
│   │   │   │               │   ├── DiscountExceptionHandler.java
│   │   │   │               │   ├── GlobalExceptionHandler.java
│   │   │   │               │   ├── ImageExceptionHandler.java
│   │   │   │               │   ├── ModelExceptionHandler.java
│   │   │   │               │   ├── OrderExceptionHandler.java
│   │   │   │               │   ├── PaymentExceptionHandler.java
│   │   │   │               │   ├── PermissionExceptionHandler.java
│   │   │   │               │   ├── ReviewExceptionHandler.java
│   │   │   │               │   ├── RoleExceptionHandler.java
│   │   │   │               │   ├── UserExceptionHandler.java
│   │   │   │               │   ├── UserImageExceptionHandler.java
│   │   │   │               │   └── exceptions
│   │   │   │               │       ├── Category
│   │   │   │               │       │   └── CategoryNotFoundException.java
│   │   │   │               │       ├── Discount
│   │   │   │               │       │   └── DiscountNotFoundException.java
│   │   │   │               │       ├── DiscountType
│   │   │   │               │       │   └── DiscountTypeNotFoundException.java
│   │   │   │               │       ├── Image
│   │   │   │               │       │   └── ImageNotFoundException.java
│   │   │   │               │       ├── Log
│   │   │   │               │       │   └── LogNotFoundException.java
│   │   │   │               │       ├── Model
│   │   │   │               │       │   └── ModelNotFoundException.java
│   │   │   │               │       ├── ModelStatus
│   │   │   │               │       │   └── ModelStatusNotFoundException.java
│   │   │   │               │       ├── Order
│   │   │   │               │       │   └── OrderNotFoundException.java
│   │   │   │               │       ├── OrderStatus
│   │   │   │               │       │   └── OrderStatusNotFoundException.java
│   │   │   │               │       ├── Patch
│   │   │   │               │       │   └── PatchOperationNotSupportedException.java
│   │   │   │               │       ├── Payment
│   │   │   │               │       │   └── PaymentNotFoundException.java
│   │   │   │               │       ├── PaymentMethod
│   │   │   │               │       │   └── PaymentMethodNotFoundException.java
│   │   │   │               │       ├── PaymentStatus
│   │   │   │               │       │   └── PaymentStatusNotFoundException.java
│   │   │   │               │       ├── Permission
│   │   │   │               │       │   └── PermissionNotFoundException.java
│   │   │   │               │       ├── Review
│   │   │   │               │       │   └── ReviewNotFoundException.java
│   │   │   │               │       ├── Role
│   │   │   │               │       │   └── RoleNotFoundException.java
│   │   │   │               │       ├── Security
│   │   │   │               │       │   └── UnauthorizedAccessException.java
│   │   │   │               │       ├── User
│   │   │   │               │       │   ├── UserAlreadyExistsException.java
│   │   │   │               │       │   ├── UserInvalidMailFormatException.java
│   │   │   │               │       │   ├── UserInvalidPasswordException.java
│   │   │   │               │       │   ├── UserNotFoundException.java
│   │   │   │               │       │   └── UserNotFoundMailException.java
│   │   │   │               │       └── UserImage
│   │   │   │               │           └── UserImageNotFoundException.java
│   │   │   │               ├── model
│   │   │   │               │   ├── Category.java
│   │   │   │               │   ├── Discount.java
│   │   │   │               │   ├── DiscountType.java
│   │   │   │               │   ├── Image.java
│   │   │   │               │   ├── Model.java
│   │   │   │               │   ├── ModelStatus.java
│   │   │   │               │   ├── Order.java
│   │   │   │               │   ├── OrderItem.java
│   │   │   │               │   ├── OrderStatus.java
│   │   │   │               │   ├── Payment.java
│   │   │   │               │   ├── PaymentMethod.java
│   │   │   │               │   ├── PaymentStatus.java
│   │   │   │               │   ├── Permission.java
│   │   │   │               │   ├── Review.java
│   │   │   │               │   ├── Role.java
│   │   │   │               │   ├── User.java
│   │   │   │               │   ├── UserImage.java
│   │   │   │               │   └── Views
│   │   │   │               │       ├── VDiscountsAnalysis.java
│   │   │   │               │       ├── VLogsSummary.java
│   │   │   │               │       ├── VModelsPopularity.java
│   │   │   │               │       ├── VOrderDetail.java
│   │   │   │               │       ├── VOrdersSummary.java
│   │   │   │               │       ├── VReviewsSummary.java
│   │   │   │               │       └── VUserStatistic.java
│   │   │   │               ├── repository
│   │   │   │               │   ├── CategoryRepository.java
│   │   │   │               │   ├── DiscountRepository.java
│   │   │   │               │   ├── DiscountTypeRepository.java
│   │   │   │               │   ├── ImageRepository.java
│   │   │   │               │   ├── ModelRepository.java
│   │   │   │               │   ├── ModelStatusRepository.java
│   │   │   │               │   ├── OrderItemRepository.java
│   │   │   │               │   ├── OrderRepository.java
│   │   │   │               │   ├── OrderStatusRepository.java
│   │   │   │               │   ├── PaymentMethodRepository.java
│   │   │   │               │   ├── PaymentRepository.java
│   │   │   │               │   ├── PaymentStatusRepository.java
│   │   │   │               │   ├── PermissionRepository.java
│   │   │   │               │   ├── ReviewRepository.java
│   │   │   │               │   ├── RoleRepository.java
│   │   │   │               │   ├── UserImageRepository.java
│   │   │   │               │   ├── UserRepository.java
│   │   │   │               │   └── Views
│   │   │   │               │       ├── VDiscountsAnalysisRepository.java
│   │   │   │               │       ├── VLogsSummaryRepository.java
│   │   │   │               │       ├── VModelsPopularityRepository.java
│   │   │   │               │       ├── VOrderDetailRepository.java
│   │   │   │               │       ├── VOrdersSummaryRepository.java
│   │   │   │               │       ├── VReviewsSummaryRepository.java
│   │   │   │               │       └── VUserStatisticRepository.java
│   │   │   │               ├── security
│   │   │   │               │   ├── Constants.java
│   │   │   │               │   ├── CustomUserDetails.java
│   │   │   │               │   ├── CustomUserDetailsService.java
│   │   │   │               │   ├── JwtAuthenticationFilter.java
│   │   │   │               │   ├── JwtTokenProvider.java
│   │   │   │               │   └── SecurityConfig.java
│   │   │   │               ├── service
│   │   │   │               │   ├── CategoryService.java
│   │   │   │               │   ├── DiscountService.java
│   │   │   │               │   ├── DiscountTypeService.java
│   │   │   │               │   ├── ImageService.java
│   │   │   │               │   ├── ModelService.java
│   │   │   │               │   ├── ModelStatusService.java
│   │   │   │               │   ├── OrderItemService.java
│   │   │   │               │   ├── OrderService.java
│   │   │   │               │   ├── OrderStatusService.java
│   │   │   │               │   ├── PaymentMethodService.java
│   │   │   │               │   ├── PaymentService.java
│   │   │   │               │   ├── PaymentStatusService.java
│   │   │   │               │   ├── PermissionService.java
│   │   │   │               │   ├── ReviewService.java
│   │   │   │               │   ├── RoleService.java
│   │   │   │               │   ├── UserImageService.java
│   │   │   │               │   ├── UserService.java
│   │   │   │               │   ├── Views
│   │   │   │               │   │   ├── VDiscountsAnalysisService.java
│   │   │   │               │   │   ├── VLogsSummaryService.java
│   │   │   │               │   │   ├── VModelsPopularityService.java
│   │   │   │               │   │   ├── VOrderDetailService.java
│   │   │   │               │   │   ├── VOrdersSummaryService.java
│   │   │   │               │   │   ├── VReviewsSummaryService.java
│   │   │   │               │   │   └── VUserStatisticService.java
│   │   │   │               │   └── impl
│   │   │   │               │       ├── CategoryServiceImpl.java
│   │   │   │               │       ├── DiscountServiceImpl.java
│   │   │   │               │       ├── DiscountTypeServiceImpl.java
│   │   │   │               │       ├── ImageServiceImpl.java
│   │   │   │               │       ├── ModelServiceImpl.java
│   │   │   │               │       ├── ModelStatusServiceImpl.java
│   │   │   │               │       ├── OrderItemServiceImpl.java
│   │   │   │               │       ├── OrderServiceImpl.java
│   │   │   │               │       ├── OrderStatusServiceImpl.java
│   │   │   │               │       ├── PaymentMethodServiceImpl.java
│   │   │   │               │       ├── PaymentServiceImpl.java
│   │   │   │               │       ├── PaymentStatusServiceImpl.java
│   │   │   │               │       ├── PermissionServiceImpl.java
│   │   │   │               │       ├── ReviewServiceImpl.java
│   │   │   │               │       ├── RoleServiceImpl.java
│   │   │   │               │       ├── UserImageServiceImpl.java
│   │   │   │               │       ├── UserServiceImpl.java
│   │   │   │               │       └── Views
│   │   │   │               │           ├── VDiscountsAnalysisServiceImpl.java
│   │   │   │               │           ├── VLogsSummaryServiceImpl.java
│   │   │   │               │           ├── VModelsPopularityServiceImpl.java
│   │   │   │               │           ├── VOrderDetailServiceImpl.java
│   │   │   │               │           ├── VOrdersSummaryServiceImpl.java
│   │   │   │               │           ├── VReviewsSummaryServiceImpl.java
│   │   │   │               │           └── VUserStatisticServiceImpl.java
│   │   │   │               └── util
│   │   │   │                   ├── AccessValidator.java
│   │   │   │                   ├── AuthenticationService.java
│   │   │   │                   ├── AuthenticationServiceImpl.java
│   │   │   │                   └── ValidationUtils.java
│   │   │   ├── main.iml
│   │   │   └── resources
│   │   │       ├── META-INF
│   │   │       │   └── additional-spring-configuration-metadata.json
│   │   │       ├── application.yml
│   │   │       └── db
│   │   │           └── migration
│   │   │               └── V001_add_additional_statuses_and_payment_methods.sql
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
├── backup
│   ├── Dockerfile
│   ├── backup-cron
│   ├── backup.sh
│   └── docker-entrypoint.sh
├── build_and_run.sh
├── database
│   ├── Dockerfile
│   ├── README.md
│   ├── backups
│   │   └── backup_2025-01-07_19-49-30.sql
│   ├── data
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
├── env
├── frontend
│   ├── .env
│   ├── .gitignore
│   ├── Dockerfile
│   ├── README.md
│   ├── docker-entrypoint.sh
│   ├── index.html
│   ├── nginx.conf.template
│   ├── node_modules
│   ├── package.json
│   ├── public
│   │   ├── favicon.ico
│   │   └── images
│   ├── src
│   │   ├── App.jsx
│   │   ├── api
│   │   │   └── httpClient.jsx
│   │   ├── assets
│   │   ├── components
│   │   │   ├── Navbar.jsx
│   │   │   └── ProtectedRoute.jsx
│   │   ├── constants
│   │   ├── context
│   │   │   └── CartContext.jsx
│   │   ├── hooks
│   │   │   └── useAuth.js
│   │   ├── index.jsx
│   │   ├── locales
│   │   │   ├── en.json
│   │   │   └── ru.json
│   │   ├── pages
│   │   │   ├── AdminPanel.jsx
│   │   │   ├── CartPage.jsx
│   │   │   ├── CatalogPage.jsx
│   │   │   ├── CheckoutPage.jsx
│   │   │   ├── HomePage.jsx
│   │   │   ├── LoginPage.jsx
│   │   │   ├── ManageCategories.jsx
│   │   │   ├── ManageModels.jsx
│   │   │   ├── ManageUsers.jsx
│   │   │   ├── ModelDetailsPage.jsx
│   │   │   └── RegisterPage.jsx
│   │   ├── routes
│   │   │   └── AppRoutes.jsx
│   │   ├── services
│   │   ├── store
│   │   └── styles
│   │       ├── HomePage.css
│   │       ├── main.css
│   │       └── theme.css
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