package co.com.bb.kata.model.chapter;

import lombok.Builder;
import lombok.Getter;
import java.io.InputStream;
import java.util.function.Supplier;

@Getter
@Builder(toBuilder = true)
public class UploadedFile {
    private final String originalFilename;
    private final String contentType;
    private final long contentLength;
    private final Supplier<InputStream> inputStreamSupplier;
}