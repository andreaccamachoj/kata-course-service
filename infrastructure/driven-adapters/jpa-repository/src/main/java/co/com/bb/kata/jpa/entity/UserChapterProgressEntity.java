package co.com.bb.kata.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_chapter_progress", schema = "training")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserChapterProgressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId; // viene del microservicio de autenticación

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id", nullable = false)
    private ChapterEntity chapter;

    @Column(name = "completed_at", nullable = false)
    private LocalDateTime completedAt;
}