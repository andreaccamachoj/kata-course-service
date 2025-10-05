package co.com.bb.kata.model.module;

import co.com.bb.kata.model.course.Course;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Module {

    private Long id;
    private String key;
    private String name;
    private String description;
}