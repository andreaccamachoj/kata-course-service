package co.com.bb.kata.model.usercourseprogress;

import co.com.bb.kata.model.course.Course;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserCourseProgress {
    private Long id;
    private Long userId; // viene del MS de autenticación
    private Course course;
    private Double progressPct;
    private LocalDateTime completedAt;
}
