package com.urbano.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String mobile;
    private String type;
    private String company;
    private String position;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String preferredContactMethod;
    private String notes;
    private UUID assignedTo;
    private String source;
}
