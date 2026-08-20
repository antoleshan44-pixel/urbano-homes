package com.urbano.common.storage;

public class PhotoStorageService {
    public String uploadFile(String key, byte[] content, String contentType) {
        return "File upload disabled - AWS SDK not configured";
    }

    public byte[] downloadFile(String key) {
        return new byte[0];
    }

    public String generatePresignedUrl(String key) {
        return "http://localhost:9000/" + key;
    }

    public void deleteFile(String key) {
    }
}