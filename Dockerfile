# Stage 1: Build config server
FROM maven:3.8.3-openjdk-17 AS build_config
WORKDIR /usr/src/app/config-server
COPY ./config-server/pom.xml .
RUN mvn dependency:go-offline

# Copying the entire application code
COPY ./config-server .

# Build the config server
RUN mvn clean package

# Stage 2: Build discovery
FROM maven:3.8.3-openjdk-17 AS build_discovery
WORKDIR /usr/src/app/discovery
COPY ./discovery/pom.xml .
RUN mvn dependency:go-offline
# Copying the entire application code
COPY ./discovery .

# Copy the built artifacts from the config server
COPY --from=build_config /usr/src/app/config-server/target/config_server.jar ./config_server.jar

# Build the discovery service
RUN mvn clean package


# Stage 5: Runtime stage
FROM openjdk:17-slim AS runtime

WORKDIR /usr/src/app

# Copy JAR files from the build stage to the runtime image
COPY --from=build_config /usr/src/app/config-server/target/config_server.jar ./config_server.jar
COPY --from=build_discovery /usr/src/app/discovery/target/discovery.jar ./discovery.jar

# Expose the ports each application runs on
EXPOSE 8888 8761

# Specify the command to run each application in the correct order
CMD java -jar config_server.jar & \
    sleep 30 && \
    java -jar discovery.jar
