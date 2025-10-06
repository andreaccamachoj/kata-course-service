package co.com.bb.kata.jpa;

import co.com.bb.kata.jpa.entity.UserBadgeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface UserBadgeJPARepository extends CrudRepository<UserBadgeEntity, Long>, QueryByExampleExecutor<UserBadgeEntity> {
}
