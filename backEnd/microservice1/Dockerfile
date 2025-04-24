FROM openjdk:17
#port ulistené par le projet
EXPOSE 8081
ADD target/microservice1-0.0.1-SNAPSHOT.jar microservice1.jar
ENTRYPOINT ["java", "-jar", "microservice1.jar"]