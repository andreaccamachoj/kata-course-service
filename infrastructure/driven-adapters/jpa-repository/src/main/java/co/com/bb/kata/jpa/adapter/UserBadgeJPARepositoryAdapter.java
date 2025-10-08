package co.com.bb.kata.jpa.adapter;

import co.com.bb.kata.jpa.UserBadgeJPARepository;
import co.com.bb.kata.jpa.entity.BadgeEntity;
import co.com.bb.kata.jpa.entity.CourseEntity;
import co.com.bb.kata.jpa.entity.UserBadgeEntity;
import co.com.bb.kata.jpa.helper.AdapterOperations;
import co.com.bb.kata.model.userbadge.UserBadge;
import co.com.bb.kata.model.userbadge.UserBadgesList;
import co.com.bb.kata.model.userbadge.gateways.UserBadgeRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

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
    public UserBadge assignBadge(UserBadge userBadge) {
        UserBadgeEntity entity = new UserBadgeEntity();
        entity.setUserId(userBadge.getUserId());
        entity.setAwardedAt(LocalDateTime.now());

        BadgeEntity badge = new BadgeEntity();
        badge.setId(userBadge.getBadgeId());
        entity.setBadge(badge);

        CourseEntity course = new CourseEntity();
        course.setId(userBadge.getCourse().getId());
        entity.setCourse(course);

        UserBadgeEntity saved = repository.save(entity);

        return UserBadge.builder()
                .id(saved.getId())
                .userId(saved.getUserId())
                .badgeId(saved.getBadge().getId())
                .awardedAt(saved.getAwardedAt())
                .build();
    }

    @Override
    public List<UserBadgesList> findByUserId(Long userId) {
        return repository.findByUserId(userId)
                .stream()
                .map(entity -> UserBadgesList.builder()
                        .id(entity.getId())
                        .userId(entity.getUserId())
                        .badgeId(entity.getBadge().getId())
                        .badgeName(entity.getBadge().getName())
                        .badgeDescription(entity.getBadge().getDescription())
                        .badgeIconUrl(entity.getBadge().getIconS3Key())
                        .assignedAt(entity.getAwardedAt())
                        .build())
                .toList();
    }

}