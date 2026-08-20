FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY target/audit-service-*.jar app.jar

ENV SPRING_PROFILES_ACTIVE=docker

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "app.jar"]
