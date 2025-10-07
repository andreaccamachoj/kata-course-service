package co.com.bb.kata.jpa;

import co.com.bb.kata.jpa.entity.UserCourseProgressEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;
import java.util.Optional;

public interface UserCourseProgressJPARepository extends CrudRepository<UserCourseProgressEntity, Long>, QueryByExampleExecutor<UserCourseProgressEntity> {
    boolean existsByUserIdAndCourse_Id(Long userId, Long courseId);

    Optional<UserCourseProgressEntity> findByUserIdAndCourse_Id(Long userId, Long courseId);

    @Query("""
        SELECT ucp FROM UserCourseProgressEntity ucp
        JOIN FETCH ucp.course c
        WHERE ucp.userId = :userId
    """)
    List<UserCourseProgressEntity> findAllProgressByUserId(@Param("userId") Long userId);

    @Query("""
        SELECT ucp FROM UserCourseProgressEntity ucp
        JOIN FETCH ucp.course c
        WHERE ucp.userId = :userId AND c.id = :courseId
    """)
    Optional<UserCourseProgressEntity> findProgressByUserIdAndCourseId(
            @Param("userId") Long userId,
            @Param("courseId") Long courseId
    );

}