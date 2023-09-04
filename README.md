# RESTful CRUD API with JWT Authentication and Authorization

This Java project, built using IntelliJ IDEA, implements a RESTful CRUD API with user roles (user and admin). It leverages Spring Boot, Spring, Spring Security, and Spring Data JPA to provide robust functionality for user registration and CRUD operations on the user entity. Additionally, it incorporates JWT (JSON Web Token) for secure authentication and authorization.

## Features

- **User Registration:** Users can register with the system, providing their username and password.
- **CRUD Operations:** Users with appropriate roles can perform CRUD (Create, Read, Update, Delete) operations on the user entity.
- **Role-based Access Control:** The system distinguishes between user and admin roles, allowing for role-specific access to certain endpoints.

## Technology Stack

- **Spring Boot:** For creating the RESTful API and simplifying the setup of Spring applications.
- **Spring Security:** Implements secure authentication and authorization using JWT tokens.
- **Spring Data JPA:** Simplifies database access and management.
- **JWT Authentication:** Secure user authentication and authorization using JSON Web Tokens.
- **Lombok:** Reduces boilerplate code in Java classes, making the codebase cleaner and more readable.
- **MySQL Database:** Stores and manages user data.

## Getting Started

1. **Clone the Repository:**
   ```shell
   git clone https://github.com/kenankd/java-spring-rest-api.git
   cd your-repo
  Build and Run:
  Open the project in IntelliJ IDEA or your preferred Java IDE.  
  Configure your MySQL database settings in application.properties or application.yml.  
  Build and run the project.
2. **Usage:**
  Use Postman or a similar tool to interact with the API.
  Register a user using the /auth/register endpoint.
  Authenticate (login) using the /auth/authenticate endpoint. This will return a JWT token.
  Include the JWT token in the Authorization header of your requests to authorize and authenticate further actions.
  Access CRUD operations for the user entity based on your role (user or admin).
## Endpoints
- User Registration:
  POST /auth/register: Register a new user.
- Authentication:
  POST /auth/authenticate: Authenticate (login) and obtain a JWT token.
- User CRUD Operations (Role-based):  
  GET /api/getUsers: Get all users (admin only).  
  GET /api/getUser/{username}: Get a user by username.  
  PATCH /api/updateUsername/{username}: Update a user based on given username.  
  DELETE /api/deleteUser/{username}: Delete a user (admin only).  
## Authorization
 - **User Role (ROLE_USER)**: Allows basic user actions, such as updating their own profile.
 - **Admin Role (ROLE_ADMIN)**: Grants full access to user CRUD operations.
