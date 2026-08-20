package com.urbano.crm.entity;

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
@Table(name = "contacts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    private String mobile;

    @Column(nullable = false)
    private String type; // TENANT, PROPERTY_OWNER, VENDOR, EMPLOYEE, PROSPECT

    private String company;

    private String position;

    private String address;

    private String city;

    private String state;

    private String zipCode;

    private String country;

    private String preferredContactMethod; // EMAIL, PHONE, SMS, NONE

    @Column(columnDefinition = "TEXT")
    private String notes;

    private UUID assignedTo;

    private boolean isActive;

    private LocalDateTime lastContactDate;

    private String source; // WEBSITE, REFERRAL, SOCIAL_MEDIA, OTHER

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
