# Stack of Technologies

## 1. Docker
- **Purpose**: Containerization of application components for consistent deployment and scalability.
- **Usage**: Separate Dockerfiles for backend, frontend, and database services. Managed with `docker-compose` for multi-container orchestration.

## 2. JavaScript
- **Purpose**: Development of the client-side user interface.
- **Usage**: Frontend development using **React** and **Vite**. It provides the graphical user interface (GUI) for the application, suitable for intuitive navigation and visualization of catalog and order management.

## 3. Yarn
- **Purpose**: Dependency management for frontend.
- **Usage**: Used to manage JavaScript dependencies for the frontend, ensuring consistent installation of packages across environments. The `yarn.lock` file ensures deterministic installs.

## 4. React
- **Purpose**: Frontend JavaScript library for building user interfaces.
- **Usage**: Used to create interactive UI components and manage application state.

## 5. Vite
- **Purpose**: Next-generation frontend build tool.
- **Usage**: Utilized to bundle, transpile, and serve the frontend code, optimizing the build and development processes for a faster experience.

## 6. Spring Framework
- **Purpose**: Backend development with Spring Boot.
- **Usage**:
  - REST and gRPC API implementation.
  - Integration with PostgreSQL for database operations.
  - Utilized for dependency injection and application configuration.

## 7. PostgreSQL
- **Purpose**: Primary database for data storage and retrieval.
- **Usage**:
  - Tables and relations adhering to 3NF or higher.
  - Functions, triggers, and views for business logic.
  - Secure and scalable data handling.

## 8. REST API
- **Purpose**: Communication between backend and client-side applications.
- **Usage**:
  - High-performance communication using HTTP/HTTPS protocols for exchanging data between frontend and backend.
  - Efficient handling of structured data: Uses JSON (or optionally XML) as a lightweight data format for requests and responses.
  - Standardized CRUD operations: Implements Create, Read, Update, Delete operations through HTTP methods (POST, GET, PUT, DELETE).
  - Stateless communication: Each API request from the frontend is independent, and the backend doesn't need to store any session information, which makes scaling and load balancing easier.

## 9. Hibernate (ORM)
- **Purpose**: Object-Relational Mapping for database interactions.
- **Usage**:
  - Simplifies interaction with the database.
  - Reduces boilerplate code for CRUD operations.

## 10. Gradle
- **Purpose**: Build automation for backend services.
- **Usage**:
  - Used for building and managing dependencies for the backend service.
  - `gradle-wrapper` is used for consistent builds across environments.

## 11. Bcrypt
- **Purpose**: Secure password hashing.
- **Usage**:
  - Encryption for user passwords before storage.
  - Enhances security against brute force attacks.

## 12. Postman
- **Purpose**: API testing and development.
- **Usage**:
  - Used for testing and documenting the backend API endpoints.
  - Helps simulate client requests to the backend for functional and integration testing.

## 13. Nginx
-**Purpose**: Web server and reverse proxy.
- **Usage**:
- Serving Static Content: Nginx is used to serve the static files for the frontend, including HTML, CSS, JavaScript, and images. These files are built by Vite and packaged by React. 
- Reverse Proxy: Nginx acts as a reverse proxy to forward API requests from the frontend to the backend. It routes requests for /api/* endpoints to the backend service, ensuring smooth communication between the frontend and backend. 
- Load Balancing and Security: Nginx can also be used for load balancing across multiple instances of the backend, enhancing scalability. Additionally, it can provide SSL/TLS termination for secure communication over HTTPS.