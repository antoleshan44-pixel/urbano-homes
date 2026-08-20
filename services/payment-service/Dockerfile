FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/payment-service-*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=docker
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]
