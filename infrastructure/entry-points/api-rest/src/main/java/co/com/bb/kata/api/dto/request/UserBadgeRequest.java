package co.com.bb.kata.api.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserBadgeRequest {

    private Long userId;
    private Long badgeId;
    private Long courseId;
    private LocalDateTime awardedAt;
}
