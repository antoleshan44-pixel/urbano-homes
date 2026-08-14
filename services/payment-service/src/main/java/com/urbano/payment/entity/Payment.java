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