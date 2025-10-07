package co.com.bb.kata.jpa;

import co.com.bb.kata.jpa.entity.UserBadgeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface UserBadgeJPARepository extends CrudRepository<UserBadgeEntity, Long>, QueryByExampleExecutor<UserBadgeEntity> {


    boolean existsByUserIdAndBadge_Id(Long userId, Long badgeId);

    @Query("SELECT ub FROM UserBadgeEntity ub JOIN FETCH ub.badge WHERE ub.userId = :userId")
    List<UserBadgeEntity> findByUserId(@Param("userId") Long userId);
}