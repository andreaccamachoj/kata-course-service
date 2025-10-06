package co.com.bb.kata.model.userchapterprogress;

import co.com.bb.kata.model.chapter.Chapter;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserChapterProgress {
    private Long id;
    private Long userId; // viene del microservicio de autenticación
    private Chapter chapter;
    private LocalDateTime completedAt;
}
