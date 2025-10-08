package co.com.bb.kata.api.mapper;

import co.com.bb.kata.api.dto.request.CourseCreateRequest;
import co.com.bb.kata.model.chapter.Chapter;
import co.com.bb.kata.model.course.Course;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CourseMapper {

    public Course toDomain(CourseCreateRequest dto) {
        List<Chapter> chapters = dto.getChapters().stream()
                .map(ch -> Chapter.builder()
                        .title(ch.getTitle())
                        .orderIndex(ch.getOrderIndex())
                        .contentType(ch.getContentType())
                        .fileName(ch.getFileName())
                        .build())
                .toList();

        return Course.builder()
                .moduleId(dto.getModuleId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .tags(dto.getTags())
                .published(dto.getPublished())
                .chapterList(chapters)
                .coverUrl(dto.getCoverUrl())
                .build();
    }
}
