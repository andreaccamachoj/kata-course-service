package co.com.bb.kata.jpa.adapter;

import co.com.bb.kata.jpa.UserCourseProgressJPARepository;
import co.com.bb.kata.jpa.entity.CourseEntity;
import co.com.bb.kata.jpa.entity.UserCourseProgressEntity;
import co.com.bb.kata.jpa.helper.AdapterOperations;
import co.com.bb.kata.model.usercourseprogress.UserCourseProgress;
import co.com.bb.kata.model.usercourseprogress.gateways.UserCourseProgressRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class UserCourseProgressJPARepositoryAdapter extends AdapterOperations<
        UserCourseProgress,
        UserCourseProgressEntity,
        Long,
        UserCourseProgressJPARepository>
        implements UserCourseProgressRepository
{

    public UserCourseProgressJPARepositoryAdapter(UserCourseProgressJPARepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, UserCourseProgress.class));
    }

    @Override
    public boolean existsByUserIdAndCourseId(Long userId, Long courseId) {
        return repository.existsByUserIdAndCourse_Id(userId, courseId);
    }

    @Override
    public UserCourseProgress findByUserIdAndCourseId(Long userId, Long courseId) {
        return repository.findByUserIdAndCourse_Id(userId, courseId)
                .map(entity -> mapper.map(entity, UserCourseProgress.class))
                .orElse(null);
    }

    @Override
    public UserCourseProgress save(UserCourseProgress progress) {
        UserCourseProgressEntity entity = new UserCourseProgressEntity();
        entity.setUserId(progress.getUserId());
        entity.setProgressPct(progress.getProgressPct());
        entity.setCompletedAt(LocalDateTime.now());

        CourseEntity course = new CourseEntity();
        course.setId(progress.getCourse());
        entity.setCourse(course);

        UserCourseProgressEntity saved = repository.save(entity);

        return UserCourseProgress.builder()
                .id(saved.getId())
                .userId(saved.getUserId())
                .course(saved.getCourse() != null ? saved.getCourse().getId() : null)
                .progressPct(saved.getProgressPct())
                .completedAt(saved.getCompletedAt())
                .build();
    }

}