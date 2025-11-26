# Build stage: use Maven with Java 21 so release 21 is supported
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy only what we need for dependency download & build (better cache)
COPY pom.xml .
COPY src ./src

# Build the project: skip tests for faster builds
RUN mvn -B -DskipTests clean package

# Runtime stage: keep it lightweight, use Temurin 21
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the jar produced in build stage to the runtime image
COPY --from=build /app/target/*.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]