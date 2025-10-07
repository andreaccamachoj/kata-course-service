package co.com.bb.kata.jpa;

import co.com.bb.kata.jpa.entity.UserCourseProgressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

public interface UserCourseProgressJPARepository extends CrudRepository<UserCourseProgressEntity, Long>, QueryByExampleExecutor<UserCourseProgressEntity> {
    boolean existsByUserIdAndCourse_Id(Long userId, Long courseId);

    Optional<UserCourseProgressEntity> findByUserIdAndCourse_Id(Long userId, Long courseId);


}