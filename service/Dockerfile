FROM openjdk:15-jdk-alpine
ADD target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
