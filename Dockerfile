# Stage 1: Build config server
FROM maven:3.8.3-openjdk-17 AS build_config
WORKDIR /usr/src/app/config-server
COPY ./config-server .
RUN mvn dependency:go-offline

# Copying the entire application code
COPY ./config-server .

# Build the config server
RUN mvn clean package

# Stage 2: Build discovery
FROM maven:3.8.3-openjdk-17 AS build_discovery
WORKDIR /usr/src/app/discovery
COPY ./discovery .
RUN mvn dependency:go-offline
# Copying the entire application code
COPY ./discovery .

# Copy the built artifacts from the config server
COPY --from=build_config /usr/src/app/config-server/target/config-server-0.0.1-SNAPSHOT.jar ./config-server.jar

# Build the discovery service
RUN mvn clean package -DskipTests

# Stage 3: Build gateway
FROM maven:3.8.3-openjdk-17 AS build_gateway
WORKDIR /usr/src/app/gateway
COPY ./gateway .
RUN mvn dependency:go-offline
# Copying the entire application code
COPY ./gateway .

# Copy the built artifacts
COPY --from=build_config /usr/src/app/config-server/target/config-server-0.0.1-SNAPSHOT.jar ./config-server.jar
COPY --from=build_discovery /usr/src/app/discovery/target/discovery-0.0.1-SNAPSHOT.jar ./discovery.jar

# Build the discovery service
RUN mvn clean package -DskipTests

# Stage 4: Build transaction
FROM maven:3.8.3-openjdk-17 AS build_transaction
WORKDIR /usr/src/app/transaction
COPY ./transaction .
RUN mvn dependency:go-offline
# Copying the entire application code
COPY ./transaction .

# Copy the built artifacts
COPY --from=build_config /usr/src/app/config-server/target/config-server-0.0.1-SNAPSHOT.jar ./config-server.jar
COPY --from=build_discovery /usr/src/app/discovery/target/discovery-0.0.1-SNAPSHOT.jar ./discovery.jar
COPY --from=build_gateway /usr/src/app/gateway/target/gateway-0.0.1-SNAPSHOT.jar ./gateway.jar

# Build the discovery service
RUN mvn clean package -DskipTests

# Stage 5: Runtime stage
FROM openjdk:17-slim AS runtime

WORKDIR /usr/src/app

# Copy JAR files from the build stage to the runtime image
COPY --from=build_config /usr/src/app/config-server/target/config-server-0.0.1-SNAPSHOT.jar ./config-server.jar
COPY --from=build_discovery /usr/src/app/discovery/target/discovery-0.0.1-SNAPSHOT.jar ./discovery.jar
COPY --from=build_gateway /usr/src/app/gateway/target/gateway-0.0.1-SNAPSHOT.jar ./gateway.jar
COPY --from=build_transaction /usr/src/app/transaction/target/transaction-0.0.1-SNAPSHOT.jar ./transaction.jar

# Expose the ports each application runs on
EXPOSE 8888 8761 8222 8091

# Set environment variables for database configuration (example)
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/transactions
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=samaS1234
# Specify the command to run each application in the correct order
CMD java -jar config-server.jar & \
    sleep 30 && \
    java -jar discovery.jar & \
    sleep 30 && \
    java -jar gateway.jar & \
    sleep 30 && \
    java -jar transaction.jar
