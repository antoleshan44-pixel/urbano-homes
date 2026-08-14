package com.urbano.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth", r -> r
                        .path("/api/auth/**")
                        .uri("http://auth-service:8081"))
                .route("property", r -> r
                        .path("/api/properties/**")
                        .uri("http://property-service:8082"))
                .route("listing", r -> r
                        .path("/api/public/listings/**")
                        .uri("http://listing-service:8083"))
                .route("tenant", r -> r
                        .path("/api/tenants/**")
                        .uri("http://tenant-service:8084"))
                .route("payment", r -> r
                        .path("/api/payments/**")
                        .uri("http://payment-service:8085"))
                .route("maintenance", r -> r
                        .path("/api/maintenance/**")
                        .uri("http://maintenance-service:8086"))
                .route("crm", r -> r
                        .path("/api/crm/**")
                        .uri("http://crm-service:8087"))
                .build();
    }
}