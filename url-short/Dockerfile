#Docker multistage
#FROM gradle:4.7.0-jdk11-alpine AS build
#COPY --chown=gradle:gradle . /home/gradle/src
#WORKDIR /home/gradle/src
#RUN gradle build --no-daemon 

# For Java 8
#FROM openjdk:8-jdk-alpine
# For Java 11, try this
FROM adoptopenjdk/openjdk11:alpine-jre
# Refer to Maven build -> finalName
ARG JAR_FILE=build/libs/url-short-0.0.1-SNAPSHOT.jar
# cd /opt/app
WORKDIR /opt/app
# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} url-short-v1.jar
# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","url-short-v1.jar"]