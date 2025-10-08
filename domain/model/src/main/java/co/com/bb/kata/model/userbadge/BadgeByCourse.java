package co.com.bb.kata.model.userbadge;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BadgeByCourse {
    private Long id;
    private String name;
    private String description;

    public BadgeByCourse(String name, String description, String iconUrl, String criterion, Long courseId) {
        this.name = name;
        this.description = description;
        this.iconUrl = iconUrl;
        this.criterion = criterion;
        this.courseId = courseId;
    }

    private String iconUrl;
    private String criterion;
    private Long courseId;
}
