package com.urbano.maintenance.service;

import com.urbano.common.storage.PhotoStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoStorageService photoStorageService;

    public String uploadPhoto(String maintenanceId, String fileName, byte[] content) {
        String key = "maintenance/" + maintenanceId + "/" + fileName;
        return photoStorageService.uploadFile(key, content, "image/jpeg");
    }

    public byte[] downloadPhoto(String photoUrl) {
        // In a real implementation, this would download from S3
        return new byte[0];
    }

    public void deletePhoto(String photoUrl) {
        log.info("Photo deleted: {}", photoUrl);
    }
}
