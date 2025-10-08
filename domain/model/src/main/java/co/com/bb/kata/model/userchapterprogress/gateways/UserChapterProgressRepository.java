package co.com.bb.kata.model.userchapterprogress.gateways;

import co.com.bb.kata.model.userchapterprogress.ChapterCompletedResponse;
import co.com.bb.kata.model.userchapterprogress.UserChapterProgress;

import java.util.List;

public interface UserChapterProgressRepository {

    boolean existsByUserIdAndChapterId(Long userId, Long chapterId);

    UserChapterProgress save(UserChapterProgress progress);

    long countCompletedByUserAndCourse(Long userId, Long courseId);

    List<ChapterCompletedResponse> findCompletedChaptersByUserAndCourse(Long userId, Long courseId);

}
