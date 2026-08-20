package com.urbano.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        FlywayAutoConfiguration.class,
        JpaRepositoriesAutoConfiguration.class
})
@ComponentScan(
        basePackages = {"com.urbano.gateway", "com.urbano.common"},
        excludeFilters = {
                // Exclude Servlet/WebMVC configurations
                @ComponentScan.Filter(
                        type = FilterType.REGEX,
                        pattern = "com\\.urbano\\.common\\.config\\.(CorsConfig|WebConfig|AuditConfig)"
                ),
                // Exclude all filter classes
                @ComponentScan.Filter(
                        type = FilterType.REGEX,
                        pattern = "com\\.urbano\\.common\\.filter\\..*"
                ),
                // Exclude interceptor classes
                @ComponentScan.Filter(
                        type = FilterType.REGEX,
                        pattern = "com\\.urbano\\.common\\.interceptor\\..*"
                ),
                // Exclude JPA-related components
                @ComponentScan.Filter(
                        type = FilterType.REGEX,
                        pattern = "com\\.urbano\\.common\\.context\\.TenantContext"
                )
        }
)
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}