package co.com.bb.kata.usecase.badge;

import co.com.bb.kata.model.userbadge.UserBadgesList;
import co.com.bb.kata.model.userbadge.gateways.UserBadgeRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BadgeUseCase {

    private final UserBadgeRepository userBadgeRepository;

    public List<UserBadgesList> getBadgesByUser(Long userId) {
        return userBadgeRepository.findByUserId(userId);
    }
}
