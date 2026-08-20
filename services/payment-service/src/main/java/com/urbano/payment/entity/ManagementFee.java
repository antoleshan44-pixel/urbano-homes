package com.urbano.payment.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.UUID;

@Entity
@Table(name = "management_fees")
@Data
public class ManagementFee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "property_id", nullable = false)
    private UUID propertyId;

    @Column(name = "pm_account_id", nullable = false)
    private UUID pmAccountId;

    @Column(name = "period", nullable = false)
    private YearMonth period;

    @Column(name = "amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "invoiced_at")
    private LocalDateTime invoicedAt;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    @Column(name = "status", nullable = false)
    private String status = "PENDING";

    @Column(name = "description")
    private String description;
}