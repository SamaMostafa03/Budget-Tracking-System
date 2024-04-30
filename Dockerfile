FROM maven:3.8.3-openjdk-17 AS build

WORKDIR /app

COPY .. .

# Build the projects using Maven
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17-slim AS runtime

WORKDIR /app

# Copy JAR files from the build stage to the runtime image
COPY --from=build springboot-3-micro-service-demo-main/springboot-3-micro-service-demo-main/config_server/target/config-server-0.0.1-SNAPSHOT.jar /app/config_server.jar
COPY --from=build springboot-3-micro-service-demo-main/springboot-3-micro-service-demo-main/api_gateway/target/discovery-0.0.1-SNAPSHOT.jar /app/discovery_server.jar
COPY --from=build springboot-3-micro-service-demo-main/springboot-3-micro-service-demo-main/api_gateway/target/gateway-0.0.1-SNAPSHOT.jar /app/api_gateway.jar
COPY --from=build springboot-3-micro-service-demo-main/springboot-3-micro-service-demo-main/api_gateway/target/transaction-0.0.1-SNAPSHOT.jar /app/user_microservice.jar

# Expose the ports each application runs on (adjust ports if necessary)
EXPOSE 8888 8761 8222 8091

# Specify the command to run each application
CMD java -jar config_server.jar && \
    java -jar discovery_server.jar && \
    java -jar api_gateway.jar && \
    java -jar user_microservice.jar

