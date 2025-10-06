package co.com.bb.kata.jpa.adapter;

import co.com.bb.kata.jpa.BadgeJPARepository;
import co.com.bb.kata.jpa.entity.BadgeEntity;
import co.com.bb.kata.jpa.helper.AdapterOperations;
import co.com.bb.kata.model.badge.Badge;
import co.com.bb.kata.model.badge.gateways.BadgeRepository;
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
}
