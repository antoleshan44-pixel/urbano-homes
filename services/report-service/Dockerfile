FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY target/report-service-*.jar app.jar

ENV SPRING_PROFILES_ACTIVE=docker

EXPOSE 8089

ENTRYPOINT ["java", "-jar", "app.jar"]
