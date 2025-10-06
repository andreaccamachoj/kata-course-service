package co.com.bb.kata.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_badges", schema = "training")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBadgeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "badge_id", nullable = false)
    private BadgeEntity badge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @Column(name = "awarded_at", nullable = false)
    private LocalDateTime awardedAt;
}