package com.urbano.property.controller;

import com.urbano.property.dto.UnitDto;
import com.urbano.property.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/internal")
@RequiredArgsConstructor
public class InternalController {

    private final UnitService unitService;

    @GetMapping("/vacant-units")
    public ResponseEntity<List<UnitDto>> getVacantPublishedUnits() {
        return ResponseEntity.ok(unitService.getVacantPublishedUnits());
    }
}
