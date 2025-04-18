FROM openjdk:17
#port ulistené par le projet
EXPOSE 8065
ADD target/gataway-0.0.1-SNAPSHOT.jar gataway.jar
ENTRYPOINT ["java", "-jar", "gataway.jar"]