package co.com.bb.kata.api.mapper;

import co.com.bb.kata.model.chapter.UploadedFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileMapper {

    public UploadedFile toUploadedFile(MultipartFile file) {
        return UploadedFile.builder()
                .originalFilename(file.getOriginalFilename())
                .contentType(file.getContentType())
                .contentLength(file.getSize())
                .inputStreamSupplier(() -> {
                    try {
                        return file.getInputStream();
                    } catch (Exception e) {
                        throw new RuntimeException("Error reading input stream", e);
                    }
                })
                .build();
    }
}