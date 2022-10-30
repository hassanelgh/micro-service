FROM openjdk:17
WORKDIR /usr/src/myapp
COPY ./target/*.jar app.jar
EXPOSE 8084
CMD [ "java", "-jar", "app.jar"]