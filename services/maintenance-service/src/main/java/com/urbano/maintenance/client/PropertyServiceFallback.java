package com.urbano.maintenance.client;

import com.urbano.maintenance.dto.UnitResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class PropertyServiceFallback implements PropertyServiceClient {

    @Override
    public UnitResponse getUnit(UUID id) {
        log.warn("Property service unavailable - getUnit: {}", id);
        return null;
    }

    @Override
    public void updateUnitStatus(UUID id, String status) {
        log.warn("Property service unavailable - updateUnitStatus: {}", id);
    }
}
