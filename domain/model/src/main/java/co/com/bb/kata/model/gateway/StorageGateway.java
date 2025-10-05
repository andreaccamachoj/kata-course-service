package co.com.bb.kata.model.gateway;

import co.com.bb.kata.model.chapter.UploadedFile;

public interface StorageGateway {
    String upload(UploadedFile file, String key);
    String generatePresignedUrl(String s3Key);
}