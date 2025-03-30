FROM openjdk:17-jdk-alpine
LABEL authors="Patryk"
WORKDIR /app

ARG JAR_FILE=build/libs/SWIFTcontroller.jar
COPY ${JAR_FILE} app.jar


EXPOSE 8080


ENTRYPOINT ["java", "-jar", "app.jar"]