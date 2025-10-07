package co.com.bb.kata.model.userbadge;

import co.com.bb.kata.model.course.Course;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserBadge {
    private Long id;
    private Long userId;
    private Long badgeId;
    private Course course;
    private LocalDateTime awardedAt;
}
