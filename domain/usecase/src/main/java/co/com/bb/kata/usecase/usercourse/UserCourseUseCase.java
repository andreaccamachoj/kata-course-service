package co.com.bb.kata.usecase.usercourse;

import co.com.bb.kata.model.exception.BusinessException;
import co.com.bb.kata.model.exception.message.BusinessExceptionMessage;
import co.com.bb.kata.model.gateway.RestConsumerAuthGateway;
import co.com.bb.kata.model.gateway.model.User;
import co.com.bb.kata.model.usercourseprogress.UserCourseProgress;
import co.com.bb.kata.model.usercourseprogress.gateways.UserCourseProgressRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserCourseUseCase {

    private final UserCourseProgressRepository userCourseProgressRepository;
    private final RestConsumerAuthGateway restConsumerAuthGateway;

    public UserCourseProgress assignCourse(Long userId, Long courseId) {

        User user = restConsumerAuthGateway.getUserById(userId);
        if (user == null) {
            throw new BusinessException(BusinessExceptionMessage.USER_NOT_FOUND);
        }

        if (userCourseProgressRepository.existsByUserIdAndCourseId(userId, courseId)) {
            throw new BusinessException(BusinessExceptionMessage.COURSE_ALREADY_ASSIGNED);
        }

        UserCourseProgress progress = UserCourseProgress.builder()
                .userId(userId)
                .course(courseId)
                .progressPct(0.0)
                .completedAt(null)
                .build();

        return userCourseProgressRepository.save(progress);
    }

}