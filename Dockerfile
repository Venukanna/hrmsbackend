# Build stage
FROM openjdk:21-jdk-slim AS build
RUN apt-get update && apt-get install -y maven
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

# Run stage
FROM openjdk:21-jdk-slim

# Update CA certificates for MongoDB SSL connection
RUN apt-get update && \
    apt-get install -y ca-certificates && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Match the port with your application.properties
EXPOSE 1234

ENTRYPOINT ["java", "-jar", "app.jar"]