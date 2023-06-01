FROM openjdk:11-oracle
COPY target/*.jar test.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "test.jar"]
