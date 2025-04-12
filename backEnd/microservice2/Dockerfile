FROM openjdk:17
#port ulistené par le projet
EXPOSE 8082
ADD target/microservice1-0.0.1-SNAPSHOT.jar microservice2.jar
ENTRYPOINT ["java", "-jar", "microservice2.jar"]