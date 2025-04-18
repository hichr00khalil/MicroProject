# Use OpenJDK 17 image
FROM openjdk:17

# Expose the port that the application will be listening on
EXPOSE 8082

# Add the jar file to the container
ADD target/microservice1-0.0.1-SNAPSHOT.jar app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]