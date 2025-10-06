package co.com.bb.kata.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "badges", schema = "training")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BadgeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "icon_s3_key", length = 255)
    private String iconS3Key;

    @Column(length = 50, nullable = false)
    private String criterion;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", unique = true, nullable = false)
    private CourseEntity course;
}