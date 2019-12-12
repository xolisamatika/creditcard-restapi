FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY build/libs/app.jar app.jar
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongodb/test", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]