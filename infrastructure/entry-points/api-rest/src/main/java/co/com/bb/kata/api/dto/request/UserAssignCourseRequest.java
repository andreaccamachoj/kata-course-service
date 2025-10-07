package co.com.bb.kata.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserAssignCourseRequest {

    @NotNull
    Long userId;
    @NotNull
    Long courseId;

}