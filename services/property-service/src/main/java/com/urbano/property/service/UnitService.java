package com.urbano.property.service;

import com.urbano.common.dto.PagedResponse;
import com.urbano.common.enums.UnitStatus;
import com.urbano.common.exception.ResourceNotFoundException;
import com.urbano.common.storage.PhotoStorageService;
import com.urbano.property.dto.UnitDto;
import com.urbano.property.dto.UnitRequest;
import com.urbano.property.entity.Property;
import com.urbano.property.entity.Unit;
import com.urbano.property.repository.PropertyRepository;
import com.urbano.property.repository.UnitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UnitService {

    private final UnitRepository unitRepository;
    private final PropertyRepository propertyRepository;
    private final PhotoStorageService photoStorageService;

    @Transactional
    public UnitDto createUnit(UnitRequest request) {
        Property property = propertyRepository.findById(request.getPropertyId())
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

        Unit unit = Unit.builder()
                .property(property)
                .propertyId(property.getId())
                .unitNumber(request.getUnitNumber())
                .floor(request.getFloor())
                .squareFootage(request.getSquareFootage())
                .bedrooms(request.getBedrooms())
                .bathrooms(request.getBathrooms())
                .rentAmount(request.getRentAmount() != null ? request.getRentAmount().doubleValue() : 0.0)
                .currency(request.getCurrency())
                .isAvailable(true)
                .status(UnitStatus.AVAILABLE)
                .description(request.getDescription())
                .features(request.getFeatures())
                .published(true)
                .label("Unit " + request.getUnitNumber())
                .photoUrls(new ArrayList<>())
                .createdAt(LocalDateTime.now())
                .build();

        unit = unitRepository.save(unit);
        log.info("Unit created: {}", unit.getId());
        return mapToDto(unit);
    }

    public UnitDto getUnit(UUID id) {
        Unit unit = unitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unit not found"));
        return mapToDto(unit);
    }

    public PagedResponse<UnitDto> getUnits(UUID propertyId, Pageable pageable) {
        Page<Unit> unitPage = unitRepository.findByPropertyId(propertyId, pageable);
        List<UnitDto> content = unitPage.getContent().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return PagedResponse.<UnitDto>builder()
                .content(content)
                .page(pageable.getPageNumber())
                .size(pageable.getPageSize())
                .totalElements(unitPage.getTotalElements())
                .totalPages(unitPage.getTotalPages())
                .first(unitPage.isFirst())
                .last(unitPage.isLast())
                .build();
    }

    public List<UnitDto> getVacantPublishedUnits() {
        return unitRepository.findByIsAvailableTrueAndPublishedTrue().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public UnitDto updateUnit(UUID id, UnitRequest request) {
        Unit unit = unitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unit not found"));

        unit.setUnitNumber(request.getUnitNumber());
        unit.setFloor(request.getFloor());
        unit.setSquareFootage(request.getSquareFootage());
        unit.setBedrooms(request.getBedrooms());
        unit.setBathrooms(request.getBathrooms());
        unit.setRentAmount(request.getRentAmount() != null ? request.getRentAmount().doubleValue() : 0.0);
        unit.setCurrency(request.getCurrency());
        unit.setDescription(request.getDescription());
        unit.setFeatures(request.getFeatures());

        unit = unitRepository.save(unit);
        log.info("Unit updated: {}", unit.getId());
        return mapToDto(unit);
    }

    @Transactional
    public void deleteUnit(UUID id) {
        Unit unit = unitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unit not found"));
        unit.setDeletedAt(LocalDateTime.now());
        unitRepository.save(unit);
        log.info("Unit deleted: {}", id);
    }

    @Transactional
    public UnitDto publishUnit(UUID id) {
        Unit unit = unitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unit not found"));
        unit.setPublished(true);
        unit = unitRepository.save(unit);
        return mapToDto(unit);
    }

    @Transactional
    public UnitDto unpublishUnit(UUID id) {
        Unit unit = unitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unit not found"));
        unit.setPublished(false);
        unit = unitRepository.save(unit);
        return mapToDto(unit);
    }

    public String getPhotoUploadUrl(UUID unitId, String fileName) {
        String key = "units/" + unitId + "/" + fileName;
        return photoStorageService.uploadFile(key, new byte[0], "image/jpeg");
    }

    @Transactional
    public UnitDto addPhoto(UUID unitId, String photoUrl) {
        Unit unit = unitRepository.findById(unitId)
                .orElseThrow(() -> new ResourceNotFoundException("Unit not found"));
        if (unit.getPhotoUrls() == null) {
            unit.setPhotoUrls(new ArrayList<>());
        }
        unit.getPhotoUrls().add(photoUrl);
        unit = unitRepository.save(unit);
        return mapToDto(unit);
    }

    @Transactional
    public UnitDto updateStatus(UUID id, String status) {
        Unit unit = unitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unit not found"));
        try {
            UnitStatus newStatus = UnitStatus.valueOf(status.toUpperCase());
            unit.setStatus(newStatus);
            unit.setIsAvailable(newStatus == UnitStatus.AVAILABLE);
            unit = unitRepository.save(unit);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status: " + status);
        }
        return mapToDto(unit);
    }

    private UnitDto mapToDto(Unit unit) {
        return UnitDto.builder()
                .id(unit.getId())
                .propertyId(unit.getPropertyId())
                .unitNumber(unit.getUnitNumber())
                .floor(unit.getFloor())
                .squareFootage(unit.getSquareFootage())
                .bedrooms(unit.getBedrooms())
                .bathrooms(unit.getBathrooms())
                .rentAmount(unit.getRentAmount())
                .currency(unit.getCurrency())
                .isAvailable(unit.getIsAvailable())
                .status(unit.getStatus())
                .description(unit.getDescription())
                .features(unit.getFeatures())
                .photoUrls(unit.getPhotoUrls())
                .createdAt(unit.getCreatedAt())
                .updatedAt(unit.getUpdatedAt())
                .build();
    }
}
