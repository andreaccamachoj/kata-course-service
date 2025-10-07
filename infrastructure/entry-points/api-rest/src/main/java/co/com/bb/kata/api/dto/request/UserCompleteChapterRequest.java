package co.com.bb.kata.api.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCompleteChapterRequest {
    private Long userId;
    private Long courseId;
    private Long chapterId;
}