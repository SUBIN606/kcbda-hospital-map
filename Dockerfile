FROM openjdk:11-slim-buster
COPY build/libs/app.jar .
CMD ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]
