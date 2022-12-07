FROM openjdk:8
ADD target/bookManagement-0.0.1-SNAPSHOT.jar bookmanager.jar
ENTRYPOINT ["java","-jar","bookmanager.jar"]