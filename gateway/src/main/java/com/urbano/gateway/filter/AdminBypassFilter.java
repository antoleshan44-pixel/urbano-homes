package com.urbano.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class AdminBypassFilter extends AbstractGatewayFilterFactory<AdminBypassFilter.Config> {

    public AdminBypassFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String role = request.getHeaders().getFirst("X-User-Role");

            if ("SUPER_ADMIN".equals(role)) {
                ServerHttpRequest mutatedRequest = request.mutate()
                        .header("adminAccess", "true")
                        .build();
                return chain.filter(exchange.mutate().request(mutatedRequest).build());
            }

            return chain.filter(exchange);
        };
    }

    public static class Config {
    }
}