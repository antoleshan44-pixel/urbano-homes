package com.urbano.payment.service;

import com.urbano.common.dto.PagedResponse;
import com.urbano.common.enums.PaymentStatus;
import com.urbano.common.exception.ResourceNotFoundException;
import com.urbano.payment.dto.PaymentDto;
import com.urbano.payment.dto.PaymentRequest;
import com.urbano.payment.entity.Payment;
import com.urbano.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Transactional
    public PaymentDto createPayment(PaymentRequest request) {
        Payment payment = Payment.builder()
                .tenantId(request.getTenantId())
                .propertyId(request.getPropertyId())
                .unitId(request.getUnitId())
                .leaseId(request.getLeaseId())
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .paymentMethod(request.getPaymentMethod())
                .referenceNumber(request.getReferenceNumber())
                .status(PaymentStatus.PENDING)
                .paymentDate(LocalDateTime.now())
                .dueDate(request.getDueDate())
                .description(request.getDescription())
                .transactionId(UUID.randomUUID().toString())
                .paymentGateway("MPESA")
                .isReconciled(false)
                .pmAccountId(request.getTenantId())
                .createdAt(LocalDateTime.now())
                .build();

        // M-Pesa specific fields
        if ("MPESA".equalsIgnoreCase(request.getPaymentMethod())) {
            payment.setMpesaReceiptNumber("MP" + System.currentTimeMillis());
            payment.setTransactionDate(LocalDateTime.now());
        }

        payment = paymentRepository.save(payment);
        log.info("Payment created: {}", payment.getId());
        return mapToDto(payment);
    }

    public PaymentDto getPayment(UUID id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        return mapToDto(payment);
    }

    public PagedResponse<PaymentDto> getPaymentsByTenant(UUID tenantId, int page, int size) {
        Page<Payment> paymentPage = paymentRepository.findByTenantIdOrderByCreatedAtDesc(tenantId, PageRequest.of(page, size));
        return mapToPagedResponse(paymentPage, page, size);
    }

    public PagedResponse<PaymentDto> getPaymentsByProperty(UUID propertyId, int page, int size) {
        Page<Payment> paymentPage = paymentRepository.findByPropertyIdOrderByCreatedAtDesc(propertyId, PageRequest.of(page, size));
        return mapToPagedResponse(paymentPage, page, size);
    }

    public PagedResponse<PaymentDto> getPaymentsByStatus(PaymentStatus status, int page, int size) {
        Page<Payment> paymentPage = paymentRepository.findByStatus(status, PageRequest.of(page, size));
        return mapToPagedResponse(paymentPage, page, size);
    }

    @Transactional
    public PaymentDto updatePaymentStatus(UUID id, PaymentStatus status) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        payment.setStatus(status);

        if (status == PaymentStatus.PAID) {
            payment.setPaymentDate(LocalDateTime.now());
        }

        payment = paymentRepository.save(payment);
        log.info("Payment status updated: {} -> {}", id, status);
        return mapToDto(payment);
    }

    @Transactional
    public PaymentDto reconcilePayment(UUID id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        payment.setReconciled(true);
        payment.setReconciledAt(LocalDateTime.now());
        payment.setStatus(PaymentStatus.COMPLETED);
        payment = paymentRepository.save(payment);
        log.info("Payment reconciled: {}", id);
        return mapToDto(payment);
    }

    @Transactional
    public PaymentDto unreconcilePayment(UUID id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        payment.setReconciled(false);
        payment.setReconciledAt(null);
        payment.setStatus(PaymentStatus.PENDING);
        payment = paymentRepository.save(payment);
        log.info("Payment unreconciled: {}", id);
        return mapToDto(payment);
    }

    public List<PaymentDto> getUnreconciledPayments() {
        return paymentRepository.findByIsReconciledFalse().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private PagedResponse<PaymentDto> mapToPagedResponse(Page<Payment> page, int pageNum, int size) {
        List<PaymentDto> content = page.getContent().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return PagedResponse.<PaymentDto>builder()
                .content(content)
                .page(pageNum)
                .size(size)
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
    }

    private PaymentDto mapToDto(Payment payment) {
        return PaymentDto.builder()
                .id(payment.getId())
                .tenantId(payment.getTenantId())
                .propertyId(payment.getPropertyId())
                .unitId(payment.getUnitId())
                .leaseId(payment.getLeaseId())
                .amount(payment.getAmount())
                .currency(payment.getCurrency())
                .paymentMethod(payment.getPaymentMethod())
                .referenceNumber(payment.getReferenceNumber())
                .status(payment.getStatus())
                .paymentDate(payment.getPaymentDate())
                .dueDate(payment.getDueDate())
                .description(payment.getDescription())
                .isReconciled(payment.isReconciled())
                .reconciledAt(payment.getReconciledAt())
                .mpesaReceiptNumber(payment.getMpesaReceiptNumber())
                .transactionDate(payment.getTransactionDate())
                .createdAt(payment.getCreatedAt())
                .updatedAt(payment.getUpdatedAt())
                .build();
    }
}
