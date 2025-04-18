FROM openjdk:17
#port ulistené par le projet
EXPOSE 8085
ADD target/microservice5-0.0.1-SNAPSHOT.jar  microservice5.jar
ENTRYPOINT ["java", "-jar", "microservice5.jar"]