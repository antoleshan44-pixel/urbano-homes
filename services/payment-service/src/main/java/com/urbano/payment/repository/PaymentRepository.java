package com.urbano.payment.repository;

import com.urbano.common.enums.PaymentStatus;
import com.urbano.payment.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    Page<Payment> findByTenantIdOrderByCreatedAtDesc(UUID tenantId, Pageable pageable);
    Page<Payment> findByPropertyIdOrderByCreatedAtDesc(UUID propertyId, Pageable pageable);
    Page<Payment> findByStatus(PaymentStatus status, Pageable pageable);
    Optional<Payment> findByReferenceNumber(String referenceNumber);
    boolean existsByMpesaReceiptNumber(String mpesaReceiptNumber);
    List<Payment> findByIsReconciledFalse();
    List<Payment> findByReconciledAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Additional methods for PaymentService
    Page<Payment> findByPmAccountIdAndStatusOrderByTransactionDateDesc(UUID pmAccountId, PaymentStatus status, Pageable pageable);
    Page<Payment> findByPmAccountIdOrderByTransactionDateDesc(UUID pmAccountId, Pageable pageable);
    Page<Payment> findByPmAccountIdAndTenantIdOrderByTransactionDateDesc(UUID pmAccountId, UUID tenantId, Pageable pageable);
}
