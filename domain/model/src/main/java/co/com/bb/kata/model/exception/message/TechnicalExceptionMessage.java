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
            "TEC0002", "Upload file error", "500",
                    "Error occurred while uploading the file."
    ),
    REST_CONSUMER_ERROR(
            "TEC0003", "Error calling authentication service", "500",
            "Error occurred while calling the authentication service."
    ),
    REST_CONSUMER_FALLBACK(
            "TEC0004", "Fallback triggered in authentication service", "500",
            "Fallback method executed due to an error in the authentication service."
    );
    private final String code;
    private final String description;
    private final String itcCode;
    private final String message;
}
