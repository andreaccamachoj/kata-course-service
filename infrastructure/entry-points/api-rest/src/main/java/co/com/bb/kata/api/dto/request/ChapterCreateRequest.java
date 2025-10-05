package co.com.bb.kata.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ChapterCreateRequest {

    @NotBlank(message = "Chapter title is required")
    private String title;

    @NotNull(message = "Chapter order index is required")
    private Integer orderIndex;
    @NotBlank(message = "File name is required for chapter association")
    private String fileName;

    private String contentType;
}

