package com.urbano.listing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListingRequest {
    private UUID propertyId;
    private UUID unitId;
    private String title;
    private String description;
    private Double price;
    private String currency;
    private Integer bedrooms;
    private Integer bathrooms;
    private Double squareFootage;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
