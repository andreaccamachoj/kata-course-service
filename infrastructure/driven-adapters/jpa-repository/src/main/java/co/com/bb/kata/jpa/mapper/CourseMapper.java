package co.com.bb.kata.jpa.mapper;

import co.com.bb.kata.jpa.entity.ChapterEntity;
import co.com.bb.kata.jpa.entity.CourseEntity;
import co.com.bb.kata.jpa.entity.ModuleEntity;
import co.com.bb.kata.model.chapter.Chapter;
import co.com.bb.kata.model.course.Course;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    @Mapping(target = "moduleId", source = "moduleId.id")
    @Mapping(target = "chapterList", source = "chapterEntities")
    Course toModel(CourseEntity entity);

    List<Course> toModelList(List<CourseEntity> entities);

    @Mapping(target = "courseId", source = "courseEntity.id")
    Chapter toModel(ChapterEntity entity);

    List<Chapter> toModelChapterList(List<ChapterEntity> entities);

    @Mapping(target = "moduleId", source = "moduleId", qualifiedByName = "mapModuleEntity")
    @Mapping(target = "chapterEntities", source = "chapterList")
    CourseEntity toEntity(Course model);

    List<CourseEntity> toEntityList(List<Course> models);

    @Mapping(target = "courseEntity", ignore = true)
    ChapterEntity toEntity(Chapter model);

    List<ChapterEntity> toEntityChapterList(List<Chapter> models);

    @Named("mapModuleEntity")
    default ModuleEntity mapModuleEntity(Long moduleId) {
        if (moduleId == null) return null;
        ModuleEntity moduleEntity = new ModuleEntity();
        moduleEntity.setId(moduleId);
        return moduleEntity;
    }
    @AfterMapping
    default void linkChapters(@MappingTarget CourseEntity courseEntity) {
        if (courseEntity.getChapterEntities() != null) {
            courseEntity.getChapterEntities()
                    .forEach(ch -> ch.setCourseEntity(courseEntity));
        }
    }
}