FROM maven:3.8.3-openjdk-17 AS build

WORKDIR /usr/src/app

#Copy all files from the current directory
COPY . .

#Install  maven dependencies and store them in the local repository
RUN mvn dependency:go-offline

# Build each project using Maven
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17-slim AS runtime

WORKDIR /usr/src/app

# Copy JAR file from the build stage to the runtime image
COPY --from=build /usr/src/app/target/discovery-0.0.1-SNAPSHOT.jar discovery.jar

EXPOSE 8761

# Command to run application
CMD ["java", "-jar", "discovery.jar"]