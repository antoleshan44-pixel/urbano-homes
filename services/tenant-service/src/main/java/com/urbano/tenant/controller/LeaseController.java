package com.urbano.tenant.controller;

import com.urbano.common.dto.PagedResponse;
import com.urbano.common.enums.LeaseStatus;
import com.urbano.tenant.dto.LeaseDto;
import com.urbano.tenant.dto.LeaseRequest;
import com.urbano.tenant.service.LeaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/leases")
@RequiredArgsConstructor
public class LeaseController {

    private final LeaseService leaseService;

    @PostMapping
    public ResponseEntity<LeaseDto> createLease(@Valid @RequestBody LeaseRequest request) {
        return ResponseEntity.ok(leaseService.createLease(request));
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<PagedResponse<LeaseDto>> getLeasesByTenant(
            @PathVariable UUID tenantId, Pageable pageable) {
        return ResponseEntity.ok(leaseService.getLeasesByTenant(tenantId, pageable.getPageNumber(), pageable.getPageSize()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaseDto> getLease(@PathVariable UUID id) {
        return ResponseEntity.ok(leaseService.getLease(id));
    }

    @PostMapping("/{id}/terminate")
    public ResponseEntity<LeaseDto> terminateLease(@PathVariable UUID id) {
        return ResponseEntity.ok(leaseService.terminateLease(id));
    }

    @GetMapping("/unit/{unitId}/active")
    public ResponseEntity<LeaseDto> getActiveLeaseByUnit(@PathVariable UUID unitId) {
        return ResponseEntity.ok(leaseService.getActiveLeaseByUnit(unitId));
    }

    @GetMapping("/tenant/{tenantId}/active")
    public ResponseEntity<LeaseDto> getActiveLeaseByTenant(@PathVariable UUID tenantId) {
        return ResponseEntity.ok(leaseService.getActiveLeaseByTenant(tenantId));
    }
}
