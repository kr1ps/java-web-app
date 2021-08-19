FROM maven:3.5.4-jdk-8-alpine as maven
COPY ./pom.xml ./pom.xml
COPY ./src ./src
RUN mvn dependency:go-offline -B
RUN mvn package

# For Java 8, try this
FROM openjdk:8-jdk-alpine

# For Java 11, try this

#FROM adoptopenjdk/openjdk11:alpine-jre

# Refer to Maven build -> finalName
#ARG JAR_FILE=target/spring-boot-web.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY --from=maven target/*.jar /opt/app/app.jar


# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar","--server.port=8080"]

## sudo docker run -p 8080:8080 -t docker-spring-boot:1.0
## sudo docker run -p 80:8080 -t docker-spring-boot:1.0
## sudo docker run -p 443:8443 -t docker-spring-boot:1.0