package co.com.bb.kata.jpa;

import co.com.bb.kata.jpa.entity.ChapterEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface ChapterJPARepository extends CrudRepository<ChapterEntity, Long>, QueryByExampleExecutor<ChapterEntity> {
    long countByCourseEntity_Id(Long courseId);
}