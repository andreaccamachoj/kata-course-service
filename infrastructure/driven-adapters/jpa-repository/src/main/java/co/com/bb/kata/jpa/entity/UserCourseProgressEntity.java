package co.com.bb.kata.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_course_progress", schema = "training")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCourseProgressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId; // viene del MS de autenticación

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity course;

    @Column(name = "progress_pct")
    private Double progressPct;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;
}