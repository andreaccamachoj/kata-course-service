package co.com.bb.kata.usecase.badge;

import co.com.bb.kata.model.badge.gateways.BadgeRepository;
import co.com.bb.kata.model.exception.BusinessException;
import co.com.bb.kata.model.exception.message.BusinessExceptionMessage;
import co.com.bb.kata.model.userbadge.BadgeByCourse;
import co.com.bb.kata.model.userbadge.UserBadge;
import co.com.bb.kata.model.userbadge.UserBadgesList;
import co.com.bb.kata.model.userbadge.gateways.UserBadgeRepository;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class BadgeUseCase {

    private final UserBadgeRepository userBadgeRepository;

    private final BadgeRepository badgeRepository;

    public List<UserBadgesList> getBadgesByUser(Long userId) {
        return userBadgeRepository.findByUserId(userId);
    }

    public BadgeByCourse getBadgeByCourseId(Long courseId) {
        BadgeByCourse badge = badgeRepository.findByCourseId(courseId);
        if (badge == null) {
            throw new BusinessException(BusinessExceptionMessage.BADGE_NOT_FOUND);
        }
        return badge;

    }

    public UserBadge assignBadgeToUser(UserBadge userBadge) {
        if (userBadgeRepository.existsByUserIdAndBadgeId(userBadge.getUserId(), userBadge.getBadgeId())) {
            throw new BusinessException(BusinessExceptionMessage.BADGE_ALREADY_ASSIGNED);
        }
        return userBadgeRepository.assignBadge(userBadge);
    }
}
