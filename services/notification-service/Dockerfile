FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY target/notification-service-*.jar app.jar

ENV SPRING_PROFILES_ACTIVE=docker

EXPOSE 8088

ENTRYPOINT ["java", "-jar", "app.jar"]
