package co.com.bb.kata.model.course;

import co.com.bb.kata.model.chapter.Chapter;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Course {

    private Long id;
    private Long moduleId;
    private String title;
    private String description;
    private String tags;
    private Boolean published;
    private LocalDateTime createdAt;
    private List<Chapter> chapterList;
}