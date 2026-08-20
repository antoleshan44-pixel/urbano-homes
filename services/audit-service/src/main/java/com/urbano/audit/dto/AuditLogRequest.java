package com.urbano.audit.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogRequest {
    @NotBlank
    private String eventType;

    @NotNull
    private UUID userId;

    private String username;

    @NotBlank
    private String action;

    private String resourceType;

    private UUID resourceId;

    private Map<String, Object> metadata;

    private String ipAddress;

    private String userAgent;

    private boolean success;

    private Long executionTime;

    private String correlationId;

    private String serviceName;
}
