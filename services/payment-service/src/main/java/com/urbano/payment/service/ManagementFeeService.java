package com.urbano.payment.service;

import com.urbano.common.exception.ResourceNotFoundException;
import com.urbano.payment.dto.ManagementFeeRequest;
import com.urbano.payment.dto.ManagementFeeResponse;
import com.urbano.payment.entity.ManagementFee;
import com.urbano.payment.repository.ManagementFeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManagementFeeService {

    private final ManagementFeeRepository managementFeeRepository;

    @Transactional
    public ManagementFeeResponse generateFee(ManagementFeeRequest request, UUID pmAccountId) {
        ManagementFee fee = new ManagementFee();
        fee.setPropertyId(request.getPropertyId());
        fee.setPmAccountId(pmAccountId);
        fee.setPeriod(request.getPeriod());
        fee.setAmount(request.getAmount());
        fee.setInvoicedAt(LocalDateTime.now());
        fee.setStatus("PENDING");
        fee.setDescription(request.getDescription());

        ManagementFee saved = managementFeeRepository.save(fee);
        log.info("Generated management fee: {} for property: {}", saved.getId(), request.getPropertyId());

        return toResponse(saved);
    }

    public Page<ManagementFeeResponse> getManagementFees(UUID pmAccountId, Pageable pageable) {
        return managementFeeRepository.findByPmAccountIdOrderByPeriodDesc(pmAccountId, pageable)
                .map(this::toResponse);
    }

    @Transactional
    public ManagementFeeResponse markAsPaid(UUID id, UUID pmAccountId) {
        ManagementFee fee = managementFeeRepository.findByIdAndPmAccountId(id, pmAccountId)
                .orElseThrow(() -> new ResourceNotFoundException("Management fee", id));

        fee.setPaidAt(LocalDateTime.now());
        fee.setStatus("PAID");

        ManagementFee saved = managementFeeRepository.save(fee);
        log.info("Marked management fee as paid: {}", id);

        return toResponse(saved);
    }

    private ManagementFeeResponse toResponse(ManagementFee fee) {
        return ManagementFeeResponse.builder()
                .id(fee.getId())
                .propertyId(fee.getPropertyId())
                .period(fee.getPeriod())
                .amount(fee.getAmount())
                .status(fee.getStatus())
                .invoicedAt(fee.getInvoicedAt())
                .paidAt(fee.getPaidAt())
                .description(fee.getDescription())
                .build();
    }
}