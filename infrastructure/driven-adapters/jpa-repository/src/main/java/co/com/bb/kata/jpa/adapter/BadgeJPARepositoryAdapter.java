package co.com.bb.kata.jpa.adapter;

import co.com.bb.kata.jpa.BadgeJPARepository;
import co.com.bb.kata.jpa.entity.BadgeEntity;
import co.com.bb.kata.jpa.entity.CourseEntity;
import co.com.bb.kata.jpa.helper.AdapterOperations;
import co.com.bb.kata.model.badge.Badge;
import co.com.bb.kata.model.badge.gateways.BadgeRepository;
import co.com.bb.kata.model.userbadge.BadgeByCourse;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BadgeJPARepositoryAdapter extends AdapterOperations<
        Badge,
        BadgeEntity,
        Long,
        BadgeJPARepository>
        implements BadgeRepository
{

    public BadgeJPARepositoryAdapter(BadgeJPARepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Badge.class));
    }

    @Override
    public BadgeByCourse findByCourseId(Long courseId) {
        return repository.findByCourse_Id(courseId)
                .map(entity -> BadgeByCourse.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .description(entity.getDescription())
                        .iconUrl(entity.getIconS3Key())
                        .criterion(entity.getCriterion())
                        .courseId(entity.getCourse().getId())
                        .build())
                .orElse(null);
    }

    @Override
    public BadgeByCourse saveBadge(BadgeByCourse badge) {
        BadgeEntity entity = new BadgeEntity();
        entity.setName(badge.getName());
        entity.setDescription(badge.getDescription());
        entity.setIconS3Key(badge.getIconUrl());
        entity.setCriterion(badge.getCriterion());

        if (badge.getCourseId() != null) {
            CourseEntity course = new CourseEntity();
            course.setId(badge.getCourseId());
            entity.setCourse(course);
        }

        BadgeEntity savedEntity = repository.save(entity);

        return mapper.map(savedEntity, BadgeByCourse.class);
    }
}