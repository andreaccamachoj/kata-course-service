package co.com.bb.kata.usecase.usercourse;

import co.com.bb.kata.model.exception.BusinessException;
import co.com.bb.kata.model.exception.message.BusinessExceptionMessage;
import co.com.bb.kata.model.gateway.RestConsumerAuthGateway;
import co.com.bb.kata.model.gateway.model.User;
import co.com.bb.kata.model.usercourseprogress.CourseProgressResponse;
import co.com.bb.kata.model.usercourseprogress.UserCourseProgress;
import co.com.bb.kata.model.usercourseprogress.gateways.UserCourseProgressRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

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

    public List<CourseProgressResponse> getUserCoursesProgress(Long userId) {
        var user = restConsumerAuthGateway.getUserById(userId);
        if (user == null) {
            throw new BusinessException(BusinessExceptionMessage.USER_NOT_FOUND);
        }

        return userCourseProgressRepository.findProgressByUserId(userId);
    }

    public CourseProgressResponse getUserCourseProgress(Long userId, Long courseId) {
        var user = restConsumerAuthGateway.getUserById(userId);
        if (user == null) {
            throw new BusinessException(BusinessExceptionMessage.USER_NOT_FOUND);
        }

        CourseProgressResponse progress = userCourseProgressRepository.findProgressByUserAndCourse(userId, courseId);
        if (progress == null) {
            throw new BusinessException(BusinessExceptionMessage.COURSE_NOT_ASSIGNED);
        }

        return progress;
    }

}