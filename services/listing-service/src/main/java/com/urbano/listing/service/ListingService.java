package com.urbano.listing.service;

import com.urbano.common.dto.PagedResponse;
import com.urbano.common.exception.ResourceNotFoundException;
import com.urbano.listing.dto.ListingDto;
import com.urbano.listing.dto.ListingRequest;
import com.urbano.listing.entity.Listing;
import com.urbano.listing.repository.ListingRepository;
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
public class ListingService {

    private final ListingRepository listingRepository;

    @Transactional
    public ListingDto createListing(ListingRequest request) {
        Listing listing = Listing.builder()
                .propertyId(request.getPropertyId())
                .unitId(request.getUnitId())
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .currency(request.getCurrency())
                .bedrooms(request.getBedrooms())
                .bathrooms(request.getBathrooms())
                .squareFootage(request.getSquareFootage())
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .zipCode(request.getZipCode())
                .country(request.getCountry())
                .status("ACTIVE")
                .published(true)
                .createdAt(LocalDateTime.now())
                .build();

        listing = listingRepository.save(listing);
        log.info("Listing created: {}", listing.getId());
        return mapToDto(listing);
    }

    public ListingDto getListing(UUID id) {
        Listing listing = listingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Listing not found"));
        return mapToDto(listing);
    }

    public PagedResponse<ListingDto> getAllListings(int page, int size) {
        Page<Listing> listingPage = listingRepository.findAll(PageRequest.of(page, size));
        List<ListingDto> content = listingPage.getContent().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return PagedResponse.<ListingDto>builder()
                .content(content)
                .page(page)
                .size(size)
                .totalElements(listingPage.getTotalElements())
                .totalPages(listingPage.getTotalPages())
                .first(listingPage.isFirst())
                .last(listingPage.isLast())
                .build();
    }

    public PagedResponse<ListingDto> getListingsByProperty(UUID propertyId, int page, int size) {
        Page<Listing> listingPage = listingRepository.findByPropertyId(propertyId, PageRequest.of(page, size));
        List<ListingDto> content = listingPage.getContent().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return PagedResponse.<ListingDto>builder()
                .content(content)
                .page(page)
                .size(size)
                .totalElements(listingPage.getTotalElements())
                .totalPages(listingPage.getTotalPages())
                .first(listingPage.isFirst())
                .last(listingPage.isLast())
                .build();
    }

    public PagedResponse<ListingDto> getActiveListings(int page, int size) {
        Page<Listing> listingPage = listingRepository.findByStatus("ACTIVE", PageRequest.of(page, size));
        List<ListingDto> content = listingPage.getContent().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return PagedResponse.<ListingDto>builder()
                .content(content)
                .page(page)
                .size(size)
                .totalElements(listingPage.getTotalElements())
                .totalPages(listingPage.getTotalPages())
                .first(listingPage.isFirst())
                .last(listingPage.isLast())
                .build();
    }

    @Transactional
    public ListingDto updateListing(UUID id, ListingRequest request) {
        Listing listing = listingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Listing not found"));

        listing.setTitle(request.getTitle());
        listing.setDescription(request.getDescription());
        listing.setPrice(request.getPrice());
        listing.setCurrency(request.getCurrency());
        listing.setBedrooms(request.getBedrooms());
        listing.setBathrooms(request.getBathrooms());
        listing.setSquareFootage(request.getSquareFootage());
        listing.setAddress(request.getAddress());
        listing.setCity(request.getCity());
        listing.setState(request.getState());
        listing.setZipCode(request.getZipCode());
        listing.setCountry(request.getCountry());

        listing = listingRepository.save(listing);
        log.info("Listing updated: {}", listing.getId());
        return mapToDto(listing);
    }

    @Transactional
    public void deleteListing(UUID id) {
        Listing listing = listingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Listing not found"));
        listing.setStatus("DELETED");
        listing.setDeletedAt(LocalDateTime.now());
        listingRepository.save(listing);
        log.info("Listing deleted: {}", id);
    }

    @Transactional
    public ListingDto updateListingStatus(UUID id, String status) {
        Listing listing = listingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Listing not found"));
        listing.setStatus(status);
        listing = listingRepository.save(listing);
        return mapToDto(listing);
    }

    @Transactional
    public ListingDto publishListing(UUID id) {
        Listing listing = listingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Listing not found"));
        listing.setPublished(true);
        listing.setStatus("ACTIVE");
        listing = listingRepository.save(listing);
        return mapToDto(listing);
    }

    @Transactional
    public ListingDto unpublishListing(UUID id) {
        Listing listing = listingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Listing not found"));
        listing.setPublished(false);
        listing.setStatus("DRAFT");
        listing = listingRepository.save(listing);
        return mapToDto(listing);
    }

    private ListingDto mapToDto(Listing listing) {
        return ListingDto.builder()
                .id(listing.getId())
                .propertyId(listing.getPropertyId())
                .unitId(listing.getUnitId())
                .title(listing.getTitle())
                .description(listing.getDescription())
                .price(listing.getPrice())
                .currency(listing.getCurrency())
                .bedrooms(listing.getBedrooms())
                .bathrooms(listing.getBathrooms())
                .squareFootage(listing.getSquareFootage())
                .address(listing.getAddress())
                .city(listing.getCity())
                .state(listing.getState())
                .zipCode(listing.getZipCode())
                .country(listing.getCountry())
                .status(listing.getStatus())
                .published(listing.isPublished())
                .createdAt(listing.getCreatedAt())
                .updatedAt(listing.getUpdatedAt())
                .build();
    }
}
