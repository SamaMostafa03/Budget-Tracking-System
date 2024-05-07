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
COPY --from=build /usr/src/app/target/User-Service-0.0.1-SNAPSHOT.jar user.jar

# Expose port
EXPOSE 8999

# Command to run application
CMD ["java", "-jar", "user.jar"]
