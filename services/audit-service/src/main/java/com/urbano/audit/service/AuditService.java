package com.urbano.audit.service;

import com.urbano.common.dto.PagedResponse;
import com.urbano.common.exception.ResourceNotFoundException;
import com.urbano.audit.dto.AuditLogDto;
import com.urbano.audit.dto.AuditLogRequest;
import com.urbano.audit.entity.AuditLog;
import com.urbano.audit.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    @Async
    @Transactional
    public void logAction(AuditLogRequest request) {
        try {
            AuditLog auditLog = AuditLog.builder()
                    .eventType(request.getEventType())
                    .userId(request.getUserId())
                    .username(request.getUsername())
                    .action(request.getAction())
                    .resourceType(request.getResourceType())
                    .resourceId(request.getResourceId())
                    .metadata(request.getMetadata())
                    .ipAddress(request.getIpAddress())
                    .userAgent(request.getUserAgent())
                    .success(request.isSuccess())
                    .executionTime(request.getExecutionTime())
                    .correlationId(request.getCorrelationId())
                    .serviceName(request.getServiceName())
                    .timestamp(LocalDateTime.now())
                    .build();

            auditLogRepository.save(auditLog);
            log.debug("Audit log saved: {} - {}", request.getEventType(), request.getAction());
        } catch (Exception e) {
            log.error("Failed to save audit log: {}", e.getMessage());
        }
    }

    public AuditLogDto getAuditLog(UUID id) {
        AuditLog auditLog = auditLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Audit log not found"));
        return mapToDto(auditLog);
    }

    public PagedResponse<AuditLogDto> getUserAuditLogs(UUID userId, int page, int size) {
        Page<AuditLog> auditPage = auditLogRepository
                .findByUserIdOrderByTimestampDesc(userId, PageRequest.of(page, size));
        List<AuditLogDto> content = auditPage.getContent().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return PagedResponse.<AuditLogDto>builder()
                .content(content)
                .page(page)
                .size(size)
                .totalElements(auditPage.getTotalElements())
                .totalPages(auditPage.getTotalPages())
                .first(auditPage.isFirst())
                .last(auditPage.isLast())
                .build();
    }

    private AuditLogDto mapToDto(AuditLog auditLog) {
        return AuditLogDto.builder()
                .id(auditLog.getId())
                .eventType(auditLog.getEventType())
                .userId(auditLog.getUserId())
                .username(auditLog.getUsername())
                .action(auditLog.getAction())
                .resourceType(auditLog.getResourceType())
                .resourceId(auditLog.getResourceId())
                .metadata(auditLog.getMetadata())
                .ipAddress(auditLog.getIpAddress())
                .userAgent(auditLog.getUserAgent())
                .timestamp(auditLog.getTimestamp())
                .success(auditLog.isSuccess())
                .executionTime(auditLog.getExecutionTime())
                .correlationId(auditLog.getCorrelationId())
                .serviceName(auditLog.getServiceName())
                .build();
    }
}
