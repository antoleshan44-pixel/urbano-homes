package com.urbano.payment.entity;

import com.urbano.common.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID tenantId;

    @Column(nullable = false)
    private UUID propertyId;

    @Column(nullable = false)
    private UUID unitId;

    @Column(nullable = false)
    private UUID leaseId;

    @Column(nullable = false)
    private Double amount;

    private String currency;

    @Column(nullable = false)
    private String paymentMethod;

    @Column(nullable = false)
    private String referenceNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    private LocalDateTime paymentDate;

    private LocalDateTime dueDate;

    private String description;

    private String transactionId;

    private String paymentGateway;

    @Column(nullable = false)
    private boolean isReconciled;

    private LocalDateTime reconciledAt;

    // M-Pesa specific fields
    private String mpesaReceiptNumber;

    private LocalDateTime transactionDate;

    private UUID pmAccountId;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
