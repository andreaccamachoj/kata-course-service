package co.com.bb.kata.jpa;

import co.com.bb.kata.jpa.entity.UserChapterProgressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface UserChapterProgressJPARepository extends CrudRepository<UserChapterProgressEntity, Long>, QueryByExampleExecutor<UserChapterProgressEntity> {

    boolean existsByUserIdAndChapter_Id(Long userId, Long chapterId);

    long countByUserIdAndChapter_CourseEntity_Id(Long userId, Long courseId);

}