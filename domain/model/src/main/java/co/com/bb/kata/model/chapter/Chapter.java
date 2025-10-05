package co.com.bb.kata.model.chapter;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Chapter {
    private Long id;
    private Long courseId;
    private String title;
    private Integer orderIndex;
    private String fileName;
    private String s3Key;
    private String contentType;
}