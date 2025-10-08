package co.com.bb.kata.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CourseCreateRequest {

    @NotNull(message = "Module ID is required")
    private Long moduleId;

    @NotBlank(message = "Course title is required")
    @Size(max = 255)
    private String title;

    private String description;

    @Size(max = 255)
    private String tags;
    
    private String coverUrl;

    @Builder.Default
    private Boolean published = false;

    @NotNull(message = "Course must contain at least one chapter")
    private List<ChapterCreateRequest> chapters;
}