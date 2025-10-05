package co.com.bb.kata.jpa;

import co.com.bb.kata.jpa.entity.ModuleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface ModuleJPARepository extends CrudRepository<ModuleEntity, Long>, QueryByExampleExecutor<ModuleEntity> {
}