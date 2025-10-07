package co.com.bb.kata.model.usercourseprogress.gateways;

import co.com.bb.kata.model.usercourseprogress.CourseProgressResponse;
import co.com.bb.kata.model.usercourseprogress.UserCourseProgress;

import java.util.List;

public interface UserCourseProgressRepository {

    UserCourseProgress save(UserCourseProgress progress);

    UserCourseProgress findByUserIdAndCourseId(Long userId, Long courseId);

    boolean existsByUserIdAndCourseId(Long userId, Long courseId);

    List<CourseProgressResponse> findProgressByUserId(Long userId);

}
