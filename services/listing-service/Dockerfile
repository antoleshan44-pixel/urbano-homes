FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/listing-service-*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=docker
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]
