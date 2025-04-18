# Build stage
FROM openjdk:21-jdk-slim AS build
RUN apt-get update && apt-get install -y maven
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

# Run stage
FROM openjdk:21-jdk-slim

# Update CA certificates and enforce TLS 1.2
RUN apt-get update && \
    apt-get install -y ca-certificates openssl && \
    update-ca-certificates && \
    rm -rf /var/lib/apt/lists/*

# Force Java to use system CA certificates and TLS 1.2
ENV JAVA_TOOL_OPTIONS="-Djdk.tls.client.protocols=TLSv1.2 -Djavax.net.ssl.trustStore=/etc/ssl/certs/java/cacerts"

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 1234
ENTRYPOINT ["java", "-jar", "app.jar"]
