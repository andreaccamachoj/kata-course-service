package co.com.bb.kata.jpa;

import co.com.bb.kata.jpa.entity.UserChapterProgressEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface UserChapterProgressJPARepository extends CrudRepository<UserChapterProgressEntity, Long>, QueryByExampleExecutor<UserChapterProgressEntity> {

    boolean existsByUserIdAndChapter_Id(Long userId, Long chapterId);

    long countByUserIdAndChapter_CourseEntity_Id(Long userId, Long courseId);

    @Query("""
        SELECT ucp FROM UserChapterProgressEntity ucp
        JOIN FETCH ucp.chapter ch
        WHERE ucp.userId = :userId AND ch.courseEntity.id = :courseId
    """)
    List<UserChapterProgressEntity> findCompletedChaptersByUserAndCourse(
                    @Param("userId") Long userId,
                    @Param("courseId") Long courseId);

}