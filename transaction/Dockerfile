FROM maven:3.8.3-openjdk-17 AS build

# Set the working directory to the user-service directory
WORKDIR /usr/src/app/transaction

# Copy the Maven project files
COPY . .

# Build each project using Maven
RUN mvn clean package

# Runtime stage
FROM openjdk:17-slim AS runtime

WORKDIR /usr/src/app/transaction

# Copy JAR files from the build stage to the runtime image
COPY --from=build /usr/src/app/transaction/target/*.jar transaction.jar

# Expose the ports each application runs on (adjust ports if necessary)
EXPOSE 8091

# Specify the command to run each application
CMD ["java", "-jar", "transaction.jar"]


