FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/crm-service-*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=docker
EXPOSE 8087
ENTRYPOINT ["java", "-jar", "app.jar"]
