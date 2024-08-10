# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the application code
COPY src /app/src

# Package the application
RUN mvn package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:17-jdk

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/Assignment-for-Backend-of-Restaurant-0.0.1-SNAPSHOT.jar /app/restaurant-mongo.jar

# Set environment variables for MongoDB connection
ENV SPRING_DATA_MONGODB_URI=mongodb://localhost:27017/restaurant

# Expose the port your application runs on
EXPOSE 8080

# Expose the MongoDB port
EXPOSE 27017

# Run the application
ENTRYPOINT ["java", "-jar", "/app/restaurant-mongo.jar"]