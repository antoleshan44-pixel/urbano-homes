package com.urbano.payment.controller;

import com.urbano.common.context.TenantContext;
import com.urbano.payment.dto.ManagementFeeRequest;
import com.urbano.payment.dto.ManagementFeeResponse;
import com.urbano.payment.service.ManagementFeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/management-fees")
@RequiredArgsConstructor
public class ManagementFeeController {

    private final ManagementFeeService managementFeeService;

    @PostMapping
    public ResponseEntity<ManagementFeeResponse> generateFee(@Valid @RequestBody ManagementFeeRequest request) {
        UUID pmAccountId = TenantContext.getPmAccountId();
        log.info("Generating management fee for property: {}", request.getPropertyId());
        ManagementFeeResponse response = managementFeeService.generateFee(request, pmAccountId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<ManagementFeeResponse>> getManagementFees(Pageable pageable) {
        UUID pmAccountId = TenantContext.getPmAccountId();
        Page<ManagementFeeResponse> response = managementFeeService.getManagementFees(pmAccountId, pageable);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/pay")
    public ResponseEntity<ManagementFeeResponse> markAsPaid(@PathVariable UUID id) {
        UUID pmAccountId = TenantContext.getPmAccountId();
        ManagementFeeResponse response = managementFeeService.markAsPaid(id, pmAccountId);
        return ResponseEntity.ok(response);
    }
}