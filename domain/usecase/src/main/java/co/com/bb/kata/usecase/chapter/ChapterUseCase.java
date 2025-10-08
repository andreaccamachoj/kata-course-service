package co.com.bb.kata.usecase.chapter;

import co.com.bb.kata.model.chapter.gateways.ChapterRepository;
import co.com.bb.kata.model.exception.BusinessException;
import co.com.bb.kata.model.exception.message.BusinessExceptionMessage;
import co.com.bb.kata.model.userbadge.UserBadge;
import co.com.bb.kata.model.userbadge.gateways.UserBadgeRepository;
import co.com.bb.kata.model.userchapterprogress.UserChapterProgress;
import co.com.bb.kata.model.userchapterprogress.gateways.UserChapterProgressRepository;
import co.com.bb.kata.model.usercourseprogress.UserCourseProgress;
import co.com.bb.kata.model.usercourseprogress.gateways.UserCourseProgressRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class ChapterUseCase {

    private final UserChapterProgressRepository userChapterProgressRepository;
    private final UserCourseProgressRepository userCourseProgressRepository;
    private final ChapterRepository chapterRepository;
    private final UserBadgeRepository userBadgeRepository;

    public void completeChapter(Long userId, Long courseId, Long chapterId) {
        if (userChapterProgressRepository.existsByUserIdAndChapterId(userId, chapterId)) {
            throw new BusinessException(BusinessExceptionMessage.CHAPTER_ALREADY_COMPLETED);
        }

        UserChapterProgress chapterProgress = UserChapterProgress.builder()
                .userId(userId)
                .chapterId(chapterId)
                .completedAt(LocalDateTime.now())
                .build();

        userChapterProgressRepository.save(chapterProgress);

        long completedChapters = userChapterProgressRepository.countCompletedByUserAndCourse(userId, courseId);
        long totalChapters = chapterRepository.countTotalChaptersByCourse(courseId);

        if (totalChapters == 0) {
            throw new BusinessException(BusinessExceptionMessage.NO_CHAPTERS_FOR_COURSE);
        }

        double progressPct = ((double) completedChapters / totalChapters) * 100.0;

        UserCourseProgress progress = userCourseProgressRepository.findByUserIdAndCourseId(userId, courseId);
        if (progress == null) {
            progress = UserCourseProgress.builder()
                    .userId(userId)
                    .course(courseId)
                    .progressPct(progressPct)
                    .completedAt(progressPct >= 100.0 ? LocalDateTime.now() : null)
                    .build();
        } else {
            progress.setProgressPct(progressPct);
            if (progressPct >= 100.0 && progress.getCompletedAt() == null) {
                progress.setCompletedAt(LocalDateTime.now());
            }
        }

        userCourseProgressRepository.save(progress);

        if (progressPct >= 100.0) {
            assignBadgeIfNotExists(userId, courseId);
        }
    }

    private void assignBadgeIfNotExists(Long userId, Long courseId) {

        if (!userBadgeRepository.existsByUserIdAndBadgeId(userId, courseId)) {
            UserBadge userBadge = UserBadge.builder()
                    .userId(userId)
                    .badgeId(courseId)
                    .build();
            userBadgeRepository.assignBadge(userBadge);
        }
    }

}