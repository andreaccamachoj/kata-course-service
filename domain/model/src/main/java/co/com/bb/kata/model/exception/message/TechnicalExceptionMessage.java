package co.com.bb.kata.model.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TechnicalExceptionMessage {
    GET_MODULES_ERROR(
            "TEC0001", "Get modules error", "500",
            "Error occurred while fetching modules."
    ),
    UPLOAD_FILE_ERROR(
            "TEC0001", "Upload file error", "500",
                    "Error occurred while uploading the file."
    );
    private final String code;
    private final String description;
    private final String itcCode;
    private final String message;
}
