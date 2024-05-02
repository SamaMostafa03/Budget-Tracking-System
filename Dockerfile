# Stage 1: Build stage
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app

# Copy the entire project directory into the Docker build context
COPY . .

# Build and install dependencies for each service separately
RUN mvn clean install -f config_server/pom.xml && \
    mvn clean install -f discovery/pom.xml && \
    mvn clean install -f gateway/pom.xml && \
    mvn clean install -f transaction/pom.xml

# Stage 2: Runtime stage
FROM openjdk:17-slim AS runtime

WORKDIR /app

# Copy JAR files from the build stage to the runtime image
COPY --from=build /app/config_server/target/*.jar ./config_server.jar
COPY --from=build /app/discovery/target/*.jar ./discovery_server.jar
COPY --from=build /app/gateway/target/*.jar ./api_gateway.jar
COPY --from=build /app/transaction/target/*.jar ./user_microservice.jar

# Expose the ports each application runs on
EXPOSE 8888 8761 8222 8091

# Specify the command to run each application in the correct order
CMD java -jar config_server.jar & \
    sleep 30 && \
    java -jar discovery_server.jar && \
    java -jar api_gateway.jar && \
    java -jar user_microservice.jar
