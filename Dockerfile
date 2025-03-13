# First stage: Build the application
FROM gradle:8-jdk17 AS builder
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

# Second stage: Use a lightweight image to run the application
FROM openjdk:17-jdk-slim
LABEL authors="cbaha"
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the port the Spring Boot app runs on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
