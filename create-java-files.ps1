# =============================================================================
# URBANO HOMES — CREATE ALL DIRECTORIES AND JAVA FILES
# =============================================================================

$rootDir = "C:\Users\antol\programming projects\Urbano Homes"
Set-Location $rootDir

Write-Host "🏠 Creating all directories and Java files for Urbano Homes..." -ForegroundColor Green
Write-Host ""

# =============================================================================
# 1. CREATE ALL DIRECTORIES FIRST
# =============================================================================

Write-Host "📁 Creating directory structure..." -ForegroundColor Yellow

# Common module directories
$dirs = @(
    "common\src\main\java\com\urbano\common\enums",
    "common\src\main\java\com\urbano\common\exception",
    "common\src\main\java\com\urbano\common\dto",
    "common\src\main\java\com\urbano\common\filter",
    "common\src\main\java\com\urbano\common\config",
    "common\src\main\java\com\urbano\common\security",
    "common\src\main\java\com\urbano\common\context",
    "common\src\main\resources"
)

foreach ($dir in $dirs) {
    New-Item -ItemType Directory -Force -Path $dir | Out-Null
}

# Auth Service directories
$dirs = @(
    "services\auth-service\src\main\java\com\urbano\auth",
    "services\auth-service\src\main\java\com\urbano\auth\entity",
    "services\auth-service\src\main\java\com\urbano\auth\controller",
    "services\auth-service\src\main\java\com\urbano\auth\config",
    "services\auth-service\src\main\java\com\urbano\auth\service",
    "services\auth-service\src\main\java\com\urbano\auth\repository",
    "services\auth-service\src\main\java\com\urbano\auth\dto",
    "services\auth-service\src\main\resources\db\migration"
)

foreach ($dir in $dirs) {
    New-Item -ItemType Directory -Force -Path $dir | Out-Null
}

# Gateway directories
$dirs = @(
    "gateway\src\main\java\com\urbano\gateway",
    "gateway\src\main\java\com\urbano\gateway\config",
    "gateway\src\main\java\com\urbano\gateway\filter",
    "gateway\src\main\resources"
)

foreach ($dir in $dirs) {
    New-Item -ItemType Directory -Force -Path $dir | Out-Null
}

# Property Service directories
$dirs = @(
    "services\property-service\src\main\java\com\urbano\property",
    "services\property-service\src\main\java\com\urbano\property\entity",
    "services\property-service\src\main\java\com\urbano\property\controller",
    "services\property-service\src\main\java\com\urbano\property\service",
    "services\property-service\src\main\java\com\urbano\property\repository",
    "services\property-service\src\main\java\com\urbano\property\dto",
    "services\property-service\src\main\resources\db\migration"
)

foreach ($dir in $dirs) {
    New-Item -ItemType Directory -Force -Path $dir | Out-Null
}

# Listing Service directories
$dirs = @(
    "services\listing-service\src\main\java\com\urbano\listing",
    "services\listing-service\src\main\java\com\urbano\listing\controller",
    "services\listing-service\src\main\java\com\urbano\listing\service",
    "services\listing-service\src\main\java\com\urbano\listing\dto",
    "services\listing-service\src\main\resources"
)

foreach ($dir in $dirs) {
    New-Item -ItemType Directory -Force -Path $dir | Out-Null
}

# Tenant Service directories
$dirs = @(
    "services\tenant-service\src\main\java\com\urbano\tenant",
    "services\tenant-service\src\main\java\com\urbano\tenant\entity",
    "services\tenant-service\src\main\java\com\urbano\tenant\controller",
    "services\tenant-service\src\main\java\com\urbano\tenant\service",
    "services\tenant-service\src\main\java\com\urbano\tenant\repository",
    "services\tenant-service\src\main\java\com\urbano\tenant\dto",
    "services\tenant-service\src\main\resources\db\migration"
)

foreach ($dir in $dirs) {
    New-Item -ItemType Directory -Force -Path $dir | Out-Null
}

# Payment Service directories
$dirs = @(
    "services\payment-service\src\main\java\com\urbano\payment",
    "services\payment-service\src\main\java\com\urbano\payment\entity",
    "services\payment-service\src\main\java\com\urbano\payment\controller",
    "services\payment-service\src\main\java\com\urbano\payment\service",
    "services\payment-service\src\main\java\com\urbano\payment\repository",
    "services\payment-service\src\main\java\com\urbano\payment\dto",
    "services\payment-service\src\main\resources\db\migration"
)

foreach ($dir in $dirs) {
    New-Item -ItemType Directory -Force -Path $dir | Out-Null
}

# Maintenance Service directories
$dirs = @(
    "services\maintenance-service\src\main\java\com\urbano\maintenance",
    "services\maintenance-service\src\main\java\com\urbano\maintenance\entity",
    "services\maintenance-service\src\main\java\com\urbano\maintenance\controller",
    "services\maintenance-service\src\main\java\com\urbano\maintenance\service",
    "services\maintenance-service\src\main\java\com\urbano\maintenance\repository",
    "services\maintenance-service\src\main\java\com\urbano\maintenance\dto",
    "services\maintenance-service\src\main\resources\db\migration"
)

foreach ($dir in $dirs) {
    New-Item -ItemType Directory -Force -Path $dir | Out-Null
}

# CRM Service directories
$dirs = @(
    "services\crm-service\src\main\java\com\urbano\crm",
    "services\crm-service\src\main\java\com\urbano\crm\entity",
    "services\crm-service\src\main\java\com\urbano\crm\controller",
    "services\crm-service\src\main\java\com\urbano\crm\service",
    "services\crm-service\src\main\java\com\urbano\crm\repository",
    "services\crm-service\src\main\java\com\urbano\crm\dto",
    "services\crm-service\src\main\resources\db\migration"
)

foreach ($dir in $dirs) {
    New-Item -ItemType Directory -Force -Path $dir | Out-Null
}

# E2E Tests directories
New-Item -ItemType Directory -Force -Path "e2e-tests\src\test\java\com\urbano\e2e" | Out-Null
New-Item -ItemType Directory -Force -Path "e2e-tests\src\test\resources" | Out-Null

Write-Host "  ✅ Directories created" -ForegroundColor Green

# =============================================================================
# 2. CREATE ALL JAVA FILES
# =============================================================================

Write-Host "📁 Creating Java files..." -ForegroundColor Yellow

# =============================================================================
# 2a. COMMON MODULE — ENUMS
# =============================================================================

@'
package com.urbano.common.enums;

public enum UserRole {
    SUPER_ADMIN,
    PM_ADMIN,
    PM_STAFF,
    TENANT
}
'@ | Set-Content -Encoding UTF8 "common\src\main\java\com\urbano\common\enums\UserRole.java"

@'
package com.urbano.common.enums;

public enum UnitStatus {
    VACANT,
    OCCUPIED,
    UNDER_MAINTENANCE,
    DRAFT
}
'@ | Set-Content -Encoding UTF8 "common\src\main\java\com\urbano\common\enums\UnitStatus.java"

@'
package com.urbano.common.enums;

public enum PaymentStatus {
    RECONCILED,
    PARTIAL,
    OVERPAID,
    UNMATCHED
}
'@ | Set-Content -Encoding UTF8 "common\src\main\java\com\urbano\common\enums\PaymentStatus.java"

@'
package com.urbano.common.enums;

public enum MaintenanceStatus {
    SUBMITTED,
    IN_PROGRESS,
    RESOLVED
}
'@ | Set-Content -Encoding UTF8 "common\src\main\java\com\urbano\common\enums\MaintenanceStatus.java"

@'
package com.urbano.common.enums;

public enum LeaseStatus {
    ACTIVE,
    TERMINATED,
    EXPIRED
}
'@ | Set-Content -Encoding UTF8 "common\src\main\java\com\urbano\common\enums\LeaseStatus.java"

Write-Host "  ✅ Common enums created" -ForegroundColor Green

# =============================================================================
# 2b. COMMON MODULE — EXCEPTIONS
# =============================================================================

@'
package com.urbano.common.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
    public ResourceNotFoundException(String resource, Object id) {
        super(String.format("%s not found with id: %s", resource, id));
    }
}
'@ | Set-Content -Encoding UTF8 "common\src\main\java\com\urbano\common\exception\ResourceNotFoundException.java"

@'
package com.urbano.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("error", "Not Found");
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.put("error", "Internal Server Error");
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
'@ | Set-Content -Encoding UTF8 "common\src\main\java\com\urbano\common\exception\GlobalExceptionHandler.java"

Write-Host "  ✅ Common exceptions created" -ForegroundColor Green

# =============================================================================
# 2c. AUTH SERVICE
# =============================================================================

@'
package com.urbano.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
'@ | Set-Content -Encoding UTF8 "services\auth-service\src\main\java\com\urbano\auth\AuthApplication.java"

@'
package com.urbano.auth.entity;

import com.urbano.common.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phone;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Column(name = "pm_account_id")
    private UUID pmAccountId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
'@ | Set-Content -Encoding UTF8 "services\auth-service\src\main\java\com\urbano\auth\entity\User.java"

@'
package com.urbano.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/register")
    public String register() {
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public String login() {
        return "User logged in successfully!";
    }
}
'@ | Set-Content -Encoding UTF8 "services\auth-service\src\main\java\com\urbano\auth\controller\AuthController.java"

@'
package com.urbano.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .build();
    }
}
'@ | Set-Content -Encoding UTF8 "services\auth-service\src\main\java\com\urbano\auth\config\SecurityConfig.java"

@'
server:
  port: 8081

spring:
  application:
    name: auth-service
  datasource:
    url: jdbc:postgresql://localhost:5432/urbano_homes
    username: urbano
    password: urbano_dev_password
  jpa:
    hibernate:
      ddl-auto: validate
  flyway:
    enabled: true
    locations: classpath:db/migration

logging:
  level:
    com.urbano: DEBUG
'@ | Set-Content -Encoding UTF8 "services\auth-service\src\main\resources\application.yml"

Write-Host "  ✅ Auth Service created" -ForegroundColor Green

# =============================================================================
# 2d. GATEWAY
# =============================================================================

@'
package com.urbano.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
'@ | Set-Content -Encoding UTF8 "gateway\src\main\java\com\urbano\gateway\GatewayApplication.java"

@'
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
'@ | Set-Content -Encoding UTF8 "gateway\src\main\java\com\urbano\gateway\config\GatewayConfig.java"

@'
server:
  port: 8080

spring:
  application:
    name: gateway
  cloud:
    gateway:
      routes:
        - id: auth
          uri: http://auth-service:8081
          predicates:
            - Path=/api/auth/**
        - id: property
          uri: http://property-service:8082
          predicates:
            - Path=/api/properties/**,/api/units/**
        - id: listing
          uri: http://listing-service:8083
          predicates:
            - Path=/api/public/listings/**
        - id: tenant
          uri: http://tenant-service:8084
          predicates:
            - Path=/api/tenants/**,/api/leases/**
        - id: payment
          uri: http://payment-service:8085
          predicates:
            - Path=/api/payments/**
        - id: maintenance
          uri: http://maintenance-service:8086
          predicates:
            - Path=/api/maintenance/**
        - id: crm
          uri: http://crm-service:8087
          predicates:
            - Path=/api/crm/**

logging:
  level:
    com.urbano.gateway: DEBUG
'@ | Set-Content -Encoding UTF8 "gateway\src\main\resources\application.yml"

Write-Host "  ✅ Gateway created" -ForegroundColor Green

# =============================================================================
# 2e. PROPERTY SERVICE
# =============================================================================

@'
package com.urbano.property;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PropertyApplication {
    public static void main(String[] args) {
        SpringApplication.run(PropertyApplication.class, args);
    }
}
'@ | Set-Content -Encoding UTF8 "services\property-service\src\main\java\com\urbano\property\PropertyApplication.java"

@'
package com.urbano.property.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "properties")
@Data
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "pm_account_id", nullable = false)
    private UUID pmAccountId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
'@ | Set-Content -Encoding UTF8 "services\property-service\src\main\java\com\urbano\property\entity\Property.java"

@'
package com.urbano.property.entity;

import com.urbano.common.enums.UnitStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "units")
@Data
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "property_id", nullable = false)
    private UUID propertyId;

    @Column(nullable = false)
    private String label;

    private Integer bedrooms;

    @Column(name = "rent_amount", precision = 19, scale = 2)
    private BigDecimal rentAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UnitStatus status = UnitStatus.DRAFT;

    private String description;

    private boolean published = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
'@ | Set-Content -Encoding UTF8 "services\property-service\src\main\java\com\urbano\property\entity\Unit.java"

Write-Host "  ✅ Property Service created" -ForegroundColor Green

# =============================================================================
# 2f. LISTING SERVICE
# =============================================================================

@'
package com.urbano.listing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ListingApplication {
    public static void main(String[] args) {
        SpringApplication.run(ListingApplication.class, args);
    }
}
'@ | Set-Content -Encoding UTF8 "services\listing-service\src\main\java\com\urbano\listing\ListingApplication.java"

@'
package com.urbano.listing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/listings")
public class ListingController {

    @GetMapping
    public String getListings() {
        return "{\"message\": \"Public listings endpoint\"}";
    }
}
'@ | Set-Content -Encoding UTF8 "services\listing-service\src\main\java\com\urbano\listing\controller\ListingController.java"

Write-Host "  ✅ Listing Service created" -ForegroundColor Green

# =============================================================================
# 2g. TENANT SERVICE
# =============================================================================

@'
package com.urbano.tenant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TenantApplication {
    public static void main(String[] args) {
        SpringApplication.run(TenantApplication.class, args);
    }
}
'@ | Set-Content -Encoding UTF8 "services\tenant-service\src\main\java\com\urbano\tenant\TenantApplication.java"

@'
package com.urbano.tenant.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tenants")
@Data
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "pm_account_id", nullable = false)
    private UUID pmAccountId;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phone;

    @Column(name = "unit_id")
    private UUID unitId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
'@ | Set-Content -Encoding UTF8 "services\tenant-service\src\main\java\com\urbano\tenant\entity\Tenant.java"

@'
package com.urbano.tenant.entity;

import com.urbano.common.enums.LeaseStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "leases")
@Data
public class Lease {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    @Column(name = "unit_id", nullable = false)
    private UUID unitId;

    @Column(name = "pm_account_id", nullable = false)
    private UUID pmAccountId;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "rent_amount", precision = 19, scale = 2, nullable = false)
    private BigDecimal rentAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LeaseStatus status = LeaseStatus.ACTIVE;

    private String notes;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
'@ | Set-Content -Encoding UTF8 "services\tenant-service\src\main\java\com\urbano\tenant\entity\Lease.java"

Write-Host "  ✅ Tenant Service created" -ForegroundColor Green

# =============================================================================
# 2h. PAYMENT SERVICE
# =============================================================================

@'
package com.urbano.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class, args);
    }
}
'@ | Set-Content -Encoding UTF8 "services\payment-service\src\main\java\com\urbano\payment\PaymentApplication.java"

@'
package com.urbano.payment.entity;

import com.urbano.common.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payments")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "unit_id")
    private UUID unitId;

    @Column(name = "tenant_id")
    private UUID tenantId;

    @Column(name = "pm_account_id", nullable = false)
    private UUID pmAccountId;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name = "amount_expected", precision = 19, scale = 2)
    private BigDecimal amountExpected;

    @Column(name = "mpesa_receipt_number", unique = true, nullable = false)
    private String mpesaReceiptNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status = PaymentStatus.UNMATCHED;

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
'@ | Set-Content -Encoding UTF8 "services\payment-service\src\main\java\com\urbano\payment\entity\Payment.java"

Write-Host "  ✅ Payment Service created" -ForegroundColor Green

# =============================================================================
# 2i. MAINTENANCE SERVICE
# =============================================================================

@'
package com.urbano.maintenance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MaintenanceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MaintenanceApplication.class, args);
    }
}
'@ | Set-Content -Encoding UTF8 "services\maintenance-service\src\main\java\com\urbano\maintenance\MaintenanceApplication.java"

@'
package com.urbano.maintenance.entity;

import com.urbano.common.enums.MaintenanceStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "maintenance_requests")
@Data
public class MaintenanceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "unit_id", nullable = false)
    private UUID unitId;

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    @Column(name = "pm_account_id", nullable = false)
    private UUID pmAccountId;

    @Column(nullable = false)
    private String description;

    @Column(name = "photo_url")
    private String photoUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MaintenanceStatus status = MaintenanceStatus.SUBMITTED;

    @Column(name = "resolved_at")
    private LocalDateTime resolvedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
'@ | Set-Content -Encoding UTF8 "services\maintenance-service\src\main\java\com\urbano\maintenance\entity\MaintenanceRequest.java"

Write-Host "  ✅ Maintenance Service created" -ForegroundColor Green

# =============================================================================
# 2j. CRM SERVICE
# =============================================================================

@'
package com.urbano.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrmApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrmApplication.class, args);
    }
}
'@ | Set-Content -Encoding UTF8 "services\crm-service\src\main\java\com\urbano\crm\CrmApplication.java"

@'
package com.urbano.crm.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "crm_contacts")
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "pm_account_id", nullable = false)
    private UUID pmAccountId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phone;

    @Column(nullable = false)
    private String type;

    private String company;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
'@ | Set-Content -Encoding UTF8 "services\crm-service\src\main\java\com\urbano\crm\entity\Contact.java"

Write-Host "  ✅ CRM Service created" -ForegroundColor Green

# =============================================================================
# 2k. DATABASE MIGRATIONS
# =============================================================================

# Auth Service Migration
@'
CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    pm_account_id UUID,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS pm_accounts (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    company_name VARCHAR(255) NOT NULL,
    service_option VARCHAR(50) NOT NULL DEFAULT 'SOFTWARE_ONLY',
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);
'@ | Set-Content -Encoding UTF8 "services\auth-service\src\main\resources\db\migration\V1__create_user_and_pm_account_tables.sql"

# Property Service Migration
@'
CREATE TABLE IF NOT EXISTS properties (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    pm_account_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS units (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    property_id UUID NOT NULL,
    label VARCHAR(255) NOT NULL,
    bedrooms INTEGER DEFAULT 0,
    rent_amount DECIMAL(19,2),
    status VARCHAR(50) NOT NULL DEFAULT 'DRAFT',
    description TEXT,
    published BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);
'@ | Set-Content -Encoding UTF8 "services\property-service\src\main\resources\db\migration\V1__create_property_and_unit_tables.sql"

# Tenant Service Migration
@'
CREATE TABLE IF NOT EXISTS tenants (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID,
    pm_account_id UUID NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL UNIQUE,
    unit_id UUID,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS leases (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tenant_id UUID NOT NULL,
    unit_id UUID NOT NULL,
    pm_account_id UUID NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    rent_amount DECIMAL(19,2) NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);
'@ | Set-Content -Encoding UTF8 "services\tenant-service\src\main\resources\db\migration\V1__create_tenant_and_lease_tables.sql"

# Payment Service Migration
@'
CREATE TABLE IF NOT EXISTS payments (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    unit_id UUID,
    tenant_id UUID,
    pm_account_id UUID NOT NULL,
    amount DECIMAL(19,2) NOT NULL,
    amount_expected DECIMAL(19,2),
    mpesa_receipt_number VARCHAR(100) NOT NULL UNIQUE,
    status VARCHAR(50) NOT NULL DEFAULT 'UNMATCHED',
    transaction_date TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);
'@ | Set-Content -Encoding UTF8 "services\payment-service\src\main\resources\db\migration\V1__create_payment_table.sql"

# Maintenance Service Migration
@'
CREATE TABLE IF NOT EXISTS maintenance_requests (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    unit_id UUID NOT NULL,
    tenant_id UUID NOT NULL,
    pm_account_id UUID NOT NULL,
    description TEXT NOT NULL,
    photo_url TEXT,
    status VARCHAR(50) NOT NULL DEFAULT 'SUBMITTED',
    resolved_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);
'@ | Set-Content -Encoding UTF8 "services\maintenance-service\src\main\resources\db\migration\V1__create_maintenance_request_table.sql"

# CRM Service Migration
@'
CREATE TABLE IF NOT EXISTS crm_contacts (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    pm_account_id UUID NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL UNIQUE,
    type VARCHAR(50) NOT NULL,
    company VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);
'@ | Set-Content -Encoding UTF8 "services\crm-service\src\main\resources\db\migration\V1__create_crm_contact_table.sql"

Write-Host "  ✅ Database migrations created" -ForegroundColor Green

# =============================================================================
# 2l. E2E TESTS
# =============================================================================

@'
package com.urbano.e2e;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class E2ETestBase {

    protected static final String BASE_URL = System.getenv().getOrDefault(
            "E2E_BASE_URL", "http://localhost:8080"
    );

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
'@ | Set-Content -Encoding UTF8 "e2e-tests\src\test\java\com\urbano\e2e\E2ETestBase.java"

@'
package com.urbano.e2e;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AuthFlowTest extends E2ETestBase {

    @Test
    public void testHealthCheck() {
        given()
                .get("/actuator/health")
                .then()
                .statusCode(200);
    }
}
'@ | Set-Content -Encoding UTF8 "e2e-tests\src\test\java\com\urbano\e2e\AuthFlowTest.java"

Write-Host "  ✅ E2E Tests created" -ForegroundColor Green

# =============================================================================
# 3. FINAL SUMMARY
# =============================================================================

Write-Host ""
Write-Host "============================================================" -ForegroundColor Green
Write-Host "🏠 URBANO HOMES — ALL JAVA FILES CREATED!" -ForegroundColor Green
Write-Host "============================================================" -ForegroundColor Green
Write-Host ""

# Count files
$javaFiles = Get-ChildItem -Recurse -Filter "*.java" | Where-Object { $_.FullName -notlike "*\.git\*" } | Measure-Object | Select-Object -ExpandProperty Count
$sqlFiles = Get-ChildItem -Recurse -Filter "*.sql" | Where-Object { $_.FullName -notlike "*\.git\*" } | Measure-Object | Select-Object -ExpandProperty Count
$ymlFiles = Get-ChildItem -Recurse -Filter "*.yml" | Where-Object { $_.FullName -notlike "*\.git\*" } | Measure-Object | Select-Object -ExpandProperty Count

Write-Host "📊 Files Created:" -ForegroundColor Cyan
Write-Host "  ✅ Java Files: $javaFiles" -ForegroundColor White
Write-Host "  ✅ SQL Migrations: $sqlFiles" -ForegroundColor White
Write-Host "  ✅ YAML Configs: $ymlFiles" -ForegroundColor White

Write-Host ""
Write-Host "🚀 NEXT STEPS:" -ForegroundColor Yellow
Write-Host "  1. Build the project:" -ForegroundColor White
Write-Host "     mvn clean install -DskipTests" -ForegroundColor Gray
Write-Host ""
Write-Host "  2. Run the Auth Service:" -ForegroundColor White
Write-Host "     cd services\auth-service" -ForegroundColor Gray
Write-Host "     mvn spring-boot:run" -ForegroundColor Gray
Write-Host ""
Write-Host "  3. Test the API:" -ForegroundColor White
Write-Host "     curl http://localhost:8081/auth/register" -ForegroundColor Gray
Write-Host ""

Write-Host "📚 Service Ports:" -ForegroundColor Cyan
Write-Host "  Gateway:     8080  (API Gateway)" -ForegroundColor White
Write-Host "  Auth:        8081  (Authentication)" -ForegroundColor White
Write-Host "  Property:    8082  (Property Management)" -ForegroundColor White
Write-Host "  Listing:     8083  (Public Listings)" -ForegroundColor White
Write-Host "  Tenant:      8084  (Tenant & Lease)" -ForegroundColor White
Write-Host "  Payment:     8085  (Payments)" -ForegroundColor White
Write-Host "  Maintenance: 8086  (Maintenance)" -ForegroundColor White
Write-Host "  CRM:         8087  (CRM)" -ForegroundColor White

Write-Host ""
Write-Host "============================================================" -ForegroundColor Green
Write-Host "✅ DONE! Happy coding, Urbano Homes team! 🚀" -ForegroundColor Green
Write-Host "============================================================" -ForegroundColor Green