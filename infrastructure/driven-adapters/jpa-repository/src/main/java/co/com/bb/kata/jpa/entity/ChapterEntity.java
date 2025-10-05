package co.com.bb.kata.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chapters", schema = "training")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChapterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity courseEntity;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(name = "order_index", nullable = false)
    private Integer orderIndex;

    @Column(name = "s3_key", length = 255)
    private String s3Key;

    @Column(name = "content_type", length = 50)
    private String contentType;
}