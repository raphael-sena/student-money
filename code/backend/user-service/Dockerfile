FROM openjdk:21
WORKDIR /app
COPY target/user-service.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]