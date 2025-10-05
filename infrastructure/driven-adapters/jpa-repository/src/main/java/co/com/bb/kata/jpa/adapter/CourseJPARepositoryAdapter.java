package co.com.bb.kata.jpa.adapter;
import co.com.bb.kata.jpa.CourseJPARepository;
import co.com.bb.kata.jpa.entity.CourseEntity;
import co.com.bb.kata.jpa.helper.AdapterOperations;
import co.com.bb.kata.jpa.mapper.CourseMapper;
import co.com.bb.kata.model.course.Course;
import co.com.bb.kata.model.course.gateways.CourseRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.StreamSupport;

@Repository
public class CourseJPARepositoryAdapter extends AdapterOperations<
        Course,
        CourseEntity,
        Long,
        CourseJPARepository>
        implements CourseRepository
{

    public CourseJPARepositoryAdapter(CourseJPARepository repository, ObjectMapper mapper, CourseMapper courseMapper) {
        super(repository, mapper, d -> mapper.map(d, Course.class));
        this.courseMapper = courseMapper;
    }

    private static final Logger log = LoggerFactory.getLogger(CourseJPARepositoryAdapter.class);
    private final CourseMapper courseMapper;

    @Override
    public List<Course> findAllCourses() {
        log.info("[COURSE-REPOSITORY] Starting retrieval of all courses from database...");
        try {
            var entities = repository.findAll();
            var courses = StreamSupport.stream(entities.spliterator(), false)
                    .map(entity -> mapper.map(entity, Course.class))
                    .toList();

            log.debug("[COURSE-REPOSITORY] Retrieved {} courses from database.", courses.size());
            return courses;

        } catch (Exception ex) {
            log.error("[COURSE-REPOSITORY] Error retrieving all courses: {}", ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public List<Course> findByIdModule(Long moduleId) {
        log.info("[COURSE-REPOSITORY] Starting retrieval of courses for moduleId={}", moduleId);
        try {
            var entities = repository.findByModuleId_Id(moduleId);
            var courses = entities.stream()
                    .map(courseMapper::toModel)
                    .toList();

            log.debug("[COURSE-REPOSITORY] Retrieved {} courses for moduleId={}", courses.size(), moduleId);
            return courses;

        } catch (Exception ex) {
            log.error("[COURSE-REPOSITORY] Error retrieving courses for moduleId={}: {}", moduleId, ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public List<Course> findByIdCourse(Long courseId) {
        log.info("[COURSE-REPOSITORY] Starting retrieval of course and chapters for courseId={}", courseId);
        try {
            var entities = repository.findByIdWithChapters(courseId);
            var courses = entities.stream()
                    .map(courseMapper::toModel)
                    .toList();

            log.debug("[COURSE-REPOSITORY] Retrieved {} course(s) with chapters for courseId={}", courses.size(), courseId);
            return courses;

        } catch (Exception ex) {
            log.error("[COURSE-REPOSITORY] Error retrieving course with chapters for courseId={}: {}", courseId, ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public Course saveAggregate(Course course) {
        log.info("[COURSE-REPOSITORY] Saving course '{}' with chapters={}",
                course.getTitle(),
                course.getChapterList() != null ? course.getChapterList().size() : 0);

        try {
            CourseEntity entity = courseMapper.toEntity(course);
            CourseEntity saved = repository.save(entity);
            return courseMapper.toModel(saved);

        } catch (Exception ex) {
            log.error("[COURSE-REPOSITORY] Error saving course: {}", ex.getMessage(), ex);
            throw ex;
        }
    }
}