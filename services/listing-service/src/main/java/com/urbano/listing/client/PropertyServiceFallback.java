package com.urbano.listing.client;

import com.urbano.listing.dto.VacantUnitDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class PropertyServiceFallback implements PropertyServiceClient {

    @Override
    public List<VacantUnitDto> getVacantUnits() {
        log.warn("Property service is unavailable. Returning empty list.");
        return Collections.emptyList();
    }
}
