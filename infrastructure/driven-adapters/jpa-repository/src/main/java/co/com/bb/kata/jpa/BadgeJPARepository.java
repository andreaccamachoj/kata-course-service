package co.com.bb.kata.jpa;

import co.com.bb.kata.jpa.entity.BadgeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface BadgeJPARepository extends CrudRepository<BadgeEntity, Long>, QueryByExampleExecutor<BadgeEntity> {
}
