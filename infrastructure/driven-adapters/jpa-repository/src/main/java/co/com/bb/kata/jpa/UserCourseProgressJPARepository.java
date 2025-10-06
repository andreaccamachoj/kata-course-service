package co.com.bb.kata.jpa;

import co.com.bb.kata.jpa.entity.UserCourseProgressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface UserCourseProgressJPARepository extends CrudRepository<UserCourseProgressEntity, Long>, QueryByExampleExecutor<UserCourseProgressEntity> {
}
