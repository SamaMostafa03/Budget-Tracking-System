# Use Maven image as build stage
FROM maven:3.8.3-openjdk-17 AS build

# Set the working directory
WORKDIR /usr/src/app

# Copy only the POM file to cache the selected maven dependencies of the POM file
COPY pom.xml .

RUN mvn dependency:go-offline

# Copy the rest of the files to compile the project
COPY . .

# Build the project
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17-slim AS runtime

# Copy the JAR file from the build stage to the runtime image
COPY --from=build /usr/src/app/target/gateway-0.0.1-SNAPSHOT.jar gateway.jar

# Copy the wait-for-services.sh script into the Docker image
COPY wait-for-services.sh /usr/src/app/wait-for-services.sh

# Mark the script as executable and install netcat
RUN chmod +x wait-for-services.sh \
    && apt-get update \
    && apt-get install -y netcat

# Expose port
EXPOSE 8222

# Command to run application
CMD ["./wait-for-services.sh",
     "config-server", "8888",
     "discovery", "8761",
     "java", "-jar", "gateway.jar"]
