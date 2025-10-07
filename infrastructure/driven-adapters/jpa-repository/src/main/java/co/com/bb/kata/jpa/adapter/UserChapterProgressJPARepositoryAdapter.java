package co.com.bb.kata.jpa.adapter;

import co.com.bb.kata.jpa.UserChapterProgressJPARepository;
import co.com.bb.kata.jpa.entity.ChapterEntity;
import co.com.bb.kata.jpa.entity.UserChapterProgressEntity;
import co.com.bb.kata.jpa.helper.AdapterOperations;
import co.com.bb.kata.model.userchapterprogress.UserChapterProgress;
import co.com.bb.kata.model.userchapterprogress.gateways.UserChapterProgressRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class UserChapterProgressJPARepositoryAdapter extends AdapterOperations<
        UserChapterProgress,
        UserChapterProgressEntity,
        Long,
        UserChapterProgressJPARepository>
        implements UserChapterProgressRepository
{

    @PersistenceContext
    private EntityManager entityManager;

    public UserChapterProgressJPARepositoryAdapter(UserChapterProgressJPARepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, UserChapterProgress.class));
    }

    @Override
    public boolean existsByUserIdAndChapterId(Long userId, Long chapterId) {
        return repository.existsByUserIdAndChapter_Id(userId, chapterId);
    }

    @Override
    public UserChapterProgress save(UserChapterProgress progress) {
        UserChapterProgressEntity entity = new UserChapterProgressEntity();
        entity.setUserId(progress.getUserId());
        entity.setCompletedAt(LocalDateTime.now());

        ChapterEntity chapterRef = entityManager.getReference(ChapterEntity.class, progress.getChapterId());
        entity.setChapter(chapterRef);

        UserChapterProgressEntity saved = repository.save(entity);

        return UserChapterProgress.builder()
                .id(saved.getId())
                .userId(saved.getUserId())
                .chapterId(saved.getChapter() != null ? saved.getChapter().getId() : null)
                .completedAt(saved.getCompletedAt())
                .build();
    }

    @Override
    public long countCompletedByUserAndCourse(Long userId, Long courseId) {
        return repository.countByUserIdAndChapter_CourseEntity_Id(userId, courseId);
    }


}