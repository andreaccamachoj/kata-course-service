package co.com.bb.kata.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "courses", schema = "training")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id", nullable = false)
    private ModuleEntity moduleId;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 255)
    private String tags;

    @Column(nullable = false)
    private Boolean published;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "courseEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChapterEntity> chapterEntities;
}