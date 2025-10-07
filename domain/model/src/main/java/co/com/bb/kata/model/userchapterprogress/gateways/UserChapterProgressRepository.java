package co.com.bb.kata.model.userchapterprogress.gateways;

import co.com.bb.kata.model.userchapterprogress.UserChapterProgress;

public interface UserChapterProgressRepository {

    boolean existsByUserIdAndChapterId(Long userId, Long chapterId);

    UserChapterProgress save(UserChapterProgress progress);

    long countCompletedByUserAndCourse(Long userId, Long courseId);
}
