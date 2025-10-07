package co.com.bb.kata.model.usercourseprogress.gateways;

import co.com.bb.kata.model.usercourseprogress.UserCourseProgress;

public interface UserCourseProgressRepository {

    UserCourseProgress save(UserCourseProgress progress);

    UserCourseProgress findByUserIdAndCourseId(Long userId, Long courseId);

    boolean existsByUserIdAndCourseId(Long userId, Long courseId);
}
