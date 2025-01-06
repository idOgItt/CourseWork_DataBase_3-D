project/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/threed_model_market/project/
│   │   │   │       ├── controller/
│   │   │   │       ├── service/
│   │   │   │       ├── repository/
│   │   │   │       ├── model/
│   │   │   │       ├── dto/
│   │   │   │       ├── exception/
│   │   │   │       ├── util/
│   │   │   │       └── config/
│   │   │   ├── resources/
│   │   │       ├── application.yml
│   │   └── test/
│   │       └──java/
│   │           └── com/threed_model_market/project/
│   ├── gradle/wrapper
│   ├── gradlew
│   ├── gradlew.bat
│   ├── build.gradle
│   ├── Dockerfile
│
├── database/
│   ├── schema/
│   │   ├── init.sql
│   │   ├── triggers.sql
│   │   ├── functions.sql
│   │   └── views.sql
│   ├── migrations/
│   │   ├── V1__init.sql
│   │   ├── V2__add_reviews.sql
│   │   └── V3__update_orders.sql
│   ├── backups/
│   │   └── backup_YYYYMMDD.sql
│   ├── README.md
│   └── Dockerfile
│
├── frontend/
│   ├── public/
│   ├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/threed_model_market/frontend/
│   │   │       └── Main.java
│   │   └── resources/
│   │   │   ├── application.fxml
│   │   │   ├── styles.css
│   │   │   └── images/
│   │   ├── components/
│   │   │   ├── common/
│   │   │   └── specific/
│   │   ├── pages/
│   │   ├── services/
│   │   ├── store/
│   │   ├── assets/
│   │   └── config/
│   ├── tests/
│   │   ├── unit/
│   │   └── e2e/
│   ├── .gradle/
│   ├── gradle/wrapper
│   ├── gradlew
│   ├── gradlew.bat
│   ├── build.gradle
│   ├── settings.gradle
│   └── Dockerfile
│
├── docker-compose.yml
├── docs/
│   ├── api/
│   │   └── api_reference.md
│   ├── database/
│   │   ├── schema_diagram.png
│   │   └── schema_description.md
│   ├── deployment/
│   │   ├── dev_guide.md
│   │   └── prod_guide.md
│   ├── testing/
│   │   └── test_plan.md
│   ├── architecture.md
│   ├── user_guide.md
│   └── developer_guide.md
│
├── docker/
│   ├──backend/
│   ├──database/
│   ├──frontend/
│   ├──compose.dev.yml
│   ├──compose.prod.yml
│   ├──docker-compose.yml
├── logs/
│   ├── backend.log
│   ├── frontend.log
│   └── database.log
├── .env
├── .gitignore
├── build_and_run.sh
└── README.md
