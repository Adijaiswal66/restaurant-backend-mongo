
# Restaurant-Backend

**Project Description**

Restaurant-Backend is a Spring Boot application designed to manage restaurant details, including their location and ratings. This backend service allows restaurant owners to perform CRUD (Create, Read, Update, Delete) operations on their restaurant information. Users can search for nearby restaurants, view their details, and rate them. The service utilizes JWT-based authentication to protect certain endpoints, ensuring that only authorized users can perform specific operations

# Features

- **Restaurant Management** :
  - Owners can add, update, view, and delete restaurant details.
  - Owners must log in and obtain a JWT token to perform operations other than adding a restaurant.

- **User Interaction** :

  - Users can filter restaurants from nearest to farthest.
  - Users can rate restaurants out of 5.
  - The application provides average ratings for restaurants based on user feedback.

# Technology Stack

 - **Backend**: Java, Spring Boot
 - **ORM**: Hibernate
 - **Database**: PostgreSQL
 - **Version Control**: Git, GitHub
 - **API Testing**: Postman

 # Prerequisites

  - **IntelliJ IDEA**: Development environment.
  - **PostgreSQL**: Database to store restaurant details.

  # Installation and Running the Project

    1.  Clone the Repository:

```bash 
    git clone https://github.com/Adijaiswal66/Restaurant-Backend.git

    cd Restaurant-Backend
```

    2. Configure the PostgreSQL Database :
 - Create a database in PostgreSQL.
 
 - Update the application.properties file with your PostgreSQL credentials.
  

```bash 
    spring.datasource.url=jdbc:postgresql://localhost:5432/Restaurant
    spring.datasource.username=postgres
    spring.datasource.password=Aditya@1234
```


    3. Build and Run the Project
- Open the project in IntelliJ IDEA.
- Make sure all dependencies are downloaded (Maven will handle this).
- Run the application using the following command:

```bash
    mvn spring-boot:run
```
    4. Testing with Postman
- Import the Postman collection provided in the repository (if available).
- Use the endpoints to interact with the application, ensuring that you include a valid JWT token for protected routes.
