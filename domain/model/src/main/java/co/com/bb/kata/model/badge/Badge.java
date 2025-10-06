package co.com.bb.kata.model.badge;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Badge {

    private Long id;
    private String name;
    private String description;
    private String iconS3Key;
    private String criterion;

}