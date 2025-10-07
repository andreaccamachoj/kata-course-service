package co.com.bb.kata.jpa.adapter;

import co.com.bb.kata.jpa.UserBadgeJPARepository;
import co.com.bb.kata.jpa.entity.BadgeEntity;
import co.com.bb.kata.jpa.entity.UserBadgeEntity;
import co.com.bb.kata.jpa.helper.AdapterOperations;
import co.com.bb.kata.model.userbadge.UserBadge;
import co.com.bb.kata.model.userbadge.gateways.UserBadgeRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class UserBadgeJPARepositoryAdapter extends AdapterOperations<
        UserBadge,
        UserBadgeEntity,
        Long,
        UserBadgeJPARepository>
        implements UserBadgeRepository
{

    public UserBadgeJPARepositoryAdapter(UserBadgeJPARepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, UserBadge.class));
    }

    @Override
    public boolean existsByUserIdAndBadgeId(Long userId, Long badgeId) {
        return repository.existsByUserIdAndBadge_Id(userId, badgeId);
    }

    @Override
    public UserBadge assignBadge(Long userId, Long badgeId) {
        UserBadgeEntity entity = new UserBadgeEntity();
        entity.setUserId(userId);
        entity.setAwardedAt(LocalDateTime.now());

        BadgeEntity badge = new BadgeEntity();
        badge.setId(badgeId);
        entity.setBadge(badge);

        UserBadgeEntity saved = repository.save(entity);

        return UserBadge.builder()
                .id(saved.getId())
                .userId(saved.getUserId())
                .badgeId(saved.getBadge().getId())
                .awardedAt(saved.getAwardedAt())
                .build();
    }
}