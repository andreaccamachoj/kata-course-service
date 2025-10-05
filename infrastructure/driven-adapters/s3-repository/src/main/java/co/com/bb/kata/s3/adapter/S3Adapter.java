package co.com.bb.kata.s3.adapter;

import co.com.bb.kata.model.chapter.UploadedFile;
import co.com.bb.kata.model.exception.TechnicalException;
import co.com.bb.kata.model.exception.message.TechnicalExceptionMessage;
import co.com.bb.kata.model.gateway.StorageGateway;
import org.springframework.stereotype.Repository;
import co.com.bb.kata.s3.config.model.S3ConnectionProperties;
import co.com.bb.kata.s3.operations.S3Operations;
import lombok.AllArgsConstructor;

import java.io.InputStream;

@Repository
@AllArgsConstructor
public class S3Adapter implements StorageGateway {

    private final S3Operations s3Operations;

    private final S3ConnectionProperties properties;

    @Override
    public String upload(UploadedFile file, String key) {
        try (InputStream is = file.getInputStreamSupplier().get()) {
            s3Operations.uploadObject(
                    properties.bucketName(),
                    key,
                    is.readAllBytes(),
                    file.getContentType()
            );
            return key;
        } catch (Exception e) {
            System.out.println("Error uploading file to S3: " + e.getMessage());
            throw new TechnicalException(TechnicalExceptionMessage.UPLOAD_FILE_ERROR);
        }
    }


    @Override
    public String generatePresignedUrl(String s3Key) {
        return String.format("https://%s.s3.amazonaws.com/%s",
                properties.bucketName(), s3Key);
    }
}