package com.urbano.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactResponse {
    private UUID id;
    private UUID pmAccountId;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String phone;
    private String type;
    private String company;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}