FROM eclipse-temurin:21-jre
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/uam.jar"]
