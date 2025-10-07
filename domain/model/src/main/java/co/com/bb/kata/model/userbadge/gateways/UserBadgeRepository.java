package co.com.bb.kata.model.userbadge.gateways;

import co.com.bb.kata.model.userbadge.UserBadge;

public interface UserBadgeRepository {

    boolean existsByUserIdAndBadgeId(Long userId, Long badgeId);

    UserBadge assignBadge(Long userId, Long badgeId);
}
