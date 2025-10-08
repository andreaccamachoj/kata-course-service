package co.com.bb.kata.model.userbadge.gateways;

import co.com.bb.kata.model.userbadge.UserBadge;
import co.com.bb.kata.model.userbadge.UserBadgesList;

import java.util.List;


public interface UserBadgeRepository {

    boolean existsByUserIdAndBadgeId(Long userId, Long badgeId);

    UserBadge assignBadge(UserBadge userBadge);

    List<UserBadgesList> findByUserId(Long userId);
}
