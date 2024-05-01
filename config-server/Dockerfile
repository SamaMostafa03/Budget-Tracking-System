FROM maven:3.8.3-openjdk-17 AS build

#copy all files from the current directory into the /usr/src/app directory within the Docker container
COPY . /usr/src/app

WORKDIR /usr/src/app

# Build each project using Maven
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17-slim AS runtime

WORKDIR /app

# Copy JAR files from the build stage to the runtime image
COPY --from=build /usr/src/app/target/config-server-0.0.1-SNAPSHOT.jar config_server.jar

# Expose the ports each application runs on (adjust ports if necessary)
EXPOSE 8888

# Specify the command to run each application
CMD ["java", "-jar", "config_server.jar"]


