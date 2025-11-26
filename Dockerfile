# Step 1: Build the app using Maven
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . /app
# Ensure mvnw is executable and build using the wrapper
RUN chmod +x ./mvnw && ./mvnw -B -DskipTests package

# Step 2: Create a lightweight runtime image
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]