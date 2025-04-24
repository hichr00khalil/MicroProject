FROM openjdk:17
#port ulistené par le projet
EXPOSE 8888
ADD target/config-server-0.0.1-SNAPSHOT.jar configserver.jar
ENTRYPOINT ["java", "-jar", "configserver.jar"]