FROM openjdk:11
COPY target/*.jar user-service.jar
ENTRYPOINT ["java","-jar","/user-service.jar"]
EXPOSE 5001