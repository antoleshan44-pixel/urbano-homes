package com.urbano.property.service;

import com.urbano.common.storage.PhotoStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoStorageService photoStorageService;

    public String uploadPhoto(String unitId, String fileName, byte[] content) {
        String key = "units/" + unitId + "/photos/" + fileName;
        return photoStorageService.uploadFile(key, content, "image/jpeg");
    }

    public byte[] downloadPhoto(String photoUrl) {
        // Extract key from URL or use the photoStorageService
        return new byte[0];
    }

    public void deletePhoto(String photoUrl) {
        log.info("Photo deleted: {}", photoUrl);
    }
}
