FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/maintenance-service-*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=docker
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "app.jar"]
