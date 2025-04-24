FROM openjdk:17
#port ulistené par le projet
EXPOSE 8084
ADD target/microservice1-0.0.1-SNAPSHOT.jar microservice4.jar
ENTRYPOINT ["java", "-jar", "microservice4.jar"]