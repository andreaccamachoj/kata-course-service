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
    private String iconUrl;
    private String criterion;
    private Long courseId;
}
