# Stage 1: Build config server
FROM maven:3.8.3-openjdk-17 AS build_config
WORKDIR /usr/src/app/config-server

# Copy only the POM file to cache the selected maven dependencies of the POM file
COPY ./config-server/pom.xml .
RUN mvn dependency:go-offline

# Copying the entire application code
COPY ./config-server .

# Build the config server
RUN mvn clean package

# Stage 2: Build discovery
FROM maven:3.8.3-openjdk-17 AS build_discovery
WORKDIR /usr/src/app/discovery

# Copy only the POM file to cache the selected maven dependencies of the POM file
COPY ./discovery/pom.xml .
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

# Copy only the POM file to cache the selected maven dependencies of the POM file
COPY ./gateway/pom.xml .
RUN mvn dependency:go-offline

# Copying the entire application code
COPY ./gateway .

# Copy the built artifacts
COPY --from=build_config /usr/src/app/config-server/target/config-server-0.0.1-SNAPSHOT.jar ./config-server.jar
COPY --from=build_discovery /usr/src/app/discovery/target/discovery-0.0.1-SNAPSHOT.jar ./discovery.jar

# Build the gateway service
RUN mvn clean package -DskipTests

# Stage 4: Build transaction
FROM maven:3.8.3-openjdk-17 AS build_transaction
WORKDIR /usr/src/app/transaction

# Copy only the POM file to cache the selected maven dependencies of the POM file
COPY ./transaction/pom.xml .
RUN mvn dependency:go-offline

# Copying the entire application code
COPY ./transaction .

# Copy the built artifacts
COPY --from=build_config /usr/src/app/config-server/target/config-server-0.0.1-SNAPSHOT.jar ./config-server.jar
COPY --from=build_discovery /usr/src/app/discovery/target/discovery-0.0.1-SNAPSHOT.jar ./discovery.jar
COPY --from=build_gateway /usr/src/app/gateway/target/gateway-0.0.1-SNAPSHOT.jar ./gateway.jar

# Build the transaction service
RUN mvn clean package -DskipTests

# Stage 5: Build budget
FROM maven:3.8.3-openjdk-17 AS build_budget
WORKDIR /usr/src/app/budget

# Copy only the POM file to cache the selected maven dependencies of the POM file
COPY ./budget/pom.xml .
RUN mvn dependency:go-offline

# Copying the entire application code
COPY ./budget .

# Copy the built artifacts
COPY --from=build_config /usr/src/app/config-server/target/config-server-0.0.1-SNAPSHOT.jar ./config-server.jar
COPY --from=build_discovery /usr/src/app/discovery/target/discovery-0.0.1-SNAPSHOT.jar ./discovery.jar
COPY --from=build_gateway /usr/src/app/gateway/target/gateway-0.0.1-SNAPSHOT.jar ./gateway.jar
COPY --from=build_transaction /usr/src/app/transaction/target/transaction-0.0.1-SNAPSHOT.jar ./transaction.jar

# Build the budget service
RUN mvn clean package -DskipTests

# Stage 5: Build user
FROM maven:3.8.3-openjdk-17 AS build_user
WORKDIR /usr/src/app/User-Service

# Copy only the POM file to cache the selected maven dependencies of the POM file
COPY ./User-Service/pom.xml .
RUN mvn dependency:go-offline

# Copying the entire application code
COPY ./User-Service .

# Copy the built artifacts
COPY --from=build_config /usr/src/app/config-server/target/config-server-0.0.1-SNAPSHOT.jar ./config-server.jar
COPY --from=build_discovery /usr/src/app/discovery/target/discovery-0.0.1-SNAPSHOT.jar ./discovery.jar
COPY --from=build_gateway /usr/src/app/gateway/target/gateway-0.0.1-SNAPSHOT.jar ./gateway.jar
COPY --from=build_transaction /usr/src/app/transaction/target/transaction-0.0.1-SNAPSHOT.jar ./transaction.jar
COPY --from=build_budget /usr/src/app/budget/target/budget-0.0.1-SNAPSHOT.jar ./budget.jar

# Build the user service
RUN mvn clean package -DskipTests

# Stage 5: Runtime stage
FROM openjdk:17-slim AS runtime

WORKDIR /usr/src/app

# Copy JAR files from the build stage to the runtime image
COPY --from=build_config /usr/src/app/config-server/target/config-server-0.0.1-SNAPSHOT.jar ./config-server.jar
COPY --from=build_discovery /usr/src/app/discovery/target/discovery-0.0.1-SNAPSHOT.jar ./discovery.jar
COPY --from=build_gateway /usr/src/app/gateway/target/gateway-0.0.1-SNAPSHOT.jar ./gateway.jar
COPY --from=build_transaction /usr/src/app/transaction/target/transaction-0.0.1-SNAPSHOT.jar ./transaction.jar
COPY --from=build_budget /usr/src/app/budget/target/budget-0.0.1-SNAPSHOT.jar ./budget.jar
COPY --from=build_user /usr/src/app/User-Service/target/User-Service-0.0.1-SNAPSHOT.jar ./user.jar

# Expose the ports each application runs on
EXPOSE 8888 8761 8222 8091 8070 8999

# Specify the command to run each application in the correct order
CMD java -jar config-server.jar & \
    sleep 30 && \
    java -jar discovery.jar & \
    sleep 30 && \
    java -jar gateway.jar & \
    sleep 30 && \
    java -jar transaction.jar & \
    sleep 30 && \
    java -jar budget.jar & \
    sleep 30 && \
    java -jar user.jar

