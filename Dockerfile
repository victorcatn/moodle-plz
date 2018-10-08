FROM openjdk:8u111-jdk-alpine
VOLUME /tmp
ADD /target/spring-boot-app.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar", "--server.port=8087", "--spring.data.mongodb.host=pae-mdb", "--spring.data.mongodb.database=moodle-plz"]