# ==========================
# 1. Build Stage
# ==========================
FROM maven:3.9.8-eclipse-temurin-17 AS build

WORKDIR /app

# Copy only pom.xml first for dependency caching
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build the application (skip tests for faster build)
RUN mvn clean package -DskipTests

# ==========================
# 2. Runtime Stage
# ==========================
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy built JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose Render’s default port
EXPOSE 8080

# Run Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
