FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/gateway-*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=docker
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
