package com.urbano.audit.controller;

import com.urbano.common.dto.PagedResponse;
import com.urbano.audit.dto.AuditLogDto;
import com.urbano.audit.dto.AuditLogRequest;
import com.urbano.audit.service.AuditService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/audit")
@RequiredArgsConstructor
public class AuditController {

    private final AuditService auditService;

    @PostMapping("/log")
    public ResponseEntity<Void> logAction(@Valid @RequestBody AuditLogRequest request) {
        auditService.logAction(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/logs/{id}")
    public ResponseEntity<AuditLogDto> getAuditLog(@PathVariable UUID id) {
        return ResponseEntity.ok(auditService.getAuditLog(id));
    }

    @GetMapping("/logs/user/{userId}")
    public ResponseEntity<PagedResponse<AuditLogDto>> getUserAuditLogs(
            @PathVariable UUID userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(auditService.getUserAuditLogs(userId, page, size));
    }
}
