FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/tenant-service-*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=docker
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "app.jar"]
