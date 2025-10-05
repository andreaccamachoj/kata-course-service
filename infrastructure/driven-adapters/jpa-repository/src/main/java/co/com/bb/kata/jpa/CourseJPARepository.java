package co.com.bb.kata.jpa;

import co.com.bb.kata.jpa.entity.CourseEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface CourseJPARepository extends CrudRepository<CourseEntity, Long>, QueryByExampleExecutor<CourseEntity> {

    List<CourseEntity> findByModuleId_Id(Long moduleId);
    @Query("""
        SELECT c FROM CourseEntity c
        LEFT JOIN FETCH c.chapterEntities
        WHERE c.id = :courseId
    """)
    List<CourseEntity> findByIdWithChapters(@Param("courseId") Long courseId);
}