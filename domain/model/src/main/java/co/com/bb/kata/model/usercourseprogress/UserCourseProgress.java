package co.com.bb.kata.model.usercourseprogress;

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
    private Long course;
    private Double progressPct;
    private LocalDateTime completedAt;
}
