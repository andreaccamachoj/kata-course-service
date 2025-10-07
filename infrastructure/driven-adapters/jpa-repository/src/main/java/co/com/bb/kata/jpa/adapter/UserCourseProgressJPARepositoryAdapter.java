package co.com.bb.kata.jpa.adapter;

import co.com.bb.kata.jpa.UserCourseProgressJPARepository;
import co.com.bb.kata.jpa.entity.CourseEntity;
import co.com.bb.kata.jpa.entity.UserCourseProgressEntity;
import co.com.bb.kata.jpa.helper.AdapterOperations;
import co.com.bb.kata.model.usercourseprogress.CourseProgressResponse;
import co.com.bb.kata.model.usercourseprogress.UserCourseProgress;
import co.com.bb.kata.model.usercourseprogress.gateways.UserCourseProgressRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserCourseProgressJPARepositoryAdapter extends AdapterOperations<
        UserCourseProgress,
        UserCourseProgressEntity,
        Long,
        UserCourseProgressJPARepository>
        implements UserCourseProgressRepository
{


    @PersistenceContext
    private EntityManager entityManager;

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
                .map(entity -> UserCourseProgress.builder()
                        .id(entity.getId())
                        .userId(entity.getUserId())
                        .course(entity.getCourse() != null ? entity.getCourse().getId() : null)
                        .progressPct(entity.getProgressPct())
                        .completedAt(entity.getCompletedAt())
                        .build()
                )
                .orElse(null);
    }


    @Override
    public UserCourseProgress save(UserCourseProgress progress) {
        UserCourseProgressEntity existing = repository
                .findByUserIdAndCourse_Id(progress.getUserId(), progress.getCourse())
                .orElse(null);

        UserCourseProgressEntity entity;

        if (existing != null) {
            entity = existing;
            entity.setProgressPct(progress.getProgressPct());
            entity.setCompletedAt(progress.getCompletedAt());
        } else {
            entity = new UserCourseProgressEntity();
            entity.setUserId(progress.getUserId());
            entity.setProgressPct(progress.getProgressPct());
            entity.setCompletedAt(progress.getCompletedAt());

            CourseEntity courseRef = entityManager.getReference(CourseEntity.class, progress.getCourse());
            entity.setCourse(courseRef);
        }

        UserCourseProgressEntity saved = repository.save(entity);

        return UserCourseProgress.builder()
                .id(saved.getId())
                .userId(saved.getUserId())
                .course(saved.getCourse() != null ? saved.getCourse().getId() : null)
                .progressPct(saved.getProgressPct())
                .completedAt(saved.getCompletedAt())
                .build();
    }

    @Override
    public List<CourseProgressResponse> findProgressByUserId(Long userId) {
        return repository.findAllProgressByUserId(userId)
                .stream()
                .map(entity -> CourseProgressResponse.builder()
                        .courseId(entity.getCourse().getId())
                        .courseTitle(entity.getCourse().getTitle())
                        .progressPct(entity.getProgressPct())
                        .completedAt(entity.getCompletedAt() != null ? entity.getCompletedAt().toLocalDate() : null)
                        .build())
                .toList();
    }

    @Override
    public CourseProgressResponse findProgressByUserAndCourse(Long userId, Long courseId) {
        return repository.findProgressByUserIdAndCourseId(userId, courseId)
                .map(entity -> CourseProgressResponse.builder()
                        .courseId(entity.getCourse().getId())
                        .courseTitle(entity.getCourse().getTitle())
                        .progressPct(entity.getProgressPct())
                        .completedAt(entity.getCompletedAt() != null ? entity.getCompletedAt().toLocalDate() : null)
                        .build())
                .orElse(null);
    }
}