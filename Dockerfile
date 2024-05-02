# Stage 1: Build stage
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /usr/src/app/config-server
COPY . .
RUN mvn clean package

WORKDIR /usr/src/app/discovery
COPY . .
RUN mvn clean package

WORKDIR /usr/src/app/gateway
COPY . .
RUN mvn clean package

WORKDIR /usr/src/app/transaction
COPY . .
RUN mvn clean package



# Stage 2: Runtime stage
FROM openjdk:17-slim AS runtime

WORKDIR /usr/src/app

# Copy JAR files from the build stage to the runtime image
COPY --from=build /usr/src/app/config_server/target/*.jar ./config_server.jar
COPY --from=build /usr/src/app/discovery/target/*.jar ./discovery_server.jar
COPY --from=build /usr/src/app/gateway/target/*.jar ./api_gateway.jar
COPY --from=build /usr/src/app/transaction/target/*.jar ./user_microservice.jar

# Expose the ports each application runs on
EXPOSE 8888 8761 8222 8091

# Specify the command to run each application in the correct order
CMD java -jar config_server.jar & \
    sleep 30 && \
    java -jar discovery_server.jar && \
    java -jar api_gateway.jar && \
    java -jar user_microservice.jar
