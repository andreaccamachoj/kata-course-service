package co.com.bb.kata.model.usercourseprogress;

import java.time.LocalDate;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseProgressResponse {

    private Long courseId;
    private String courseTitle;
    private Double progressPct;
    private LocalDate completedAt;

}