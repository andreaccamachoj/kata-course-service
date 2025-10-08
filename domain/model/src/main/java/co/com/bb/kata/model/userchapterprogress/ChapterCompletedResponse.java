package co.com.bb.kata.model.userchapterprogress;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChapterCompletedResponse {
    private Long chapterId;
    private String chapterTitle;
}