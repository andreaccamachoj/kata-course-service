package co.com.bb.kata.model.usercourseprogress;

import java.time.LocalDate;
import java.util.List;

import co.com.bb.kata.model.userchapterprogress.ChapterCompletedResponse;
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
    private List<ChapterCompletedResponse> completedChapters;

}