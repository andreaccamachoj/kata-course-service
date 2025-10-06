package co.com.bb.kata.jpa.adapter;

import co.com.bb.kata.jpa.UserBadgeJPARepository;
import co.com.bb.kata.jpa.entity.UserBadgeEntity;
import co.com.bb.kata.jpa.helper.AdapterOperations;
import co.com.bb.kata.model.userbadge.UserBadge;
import co.com.bb.kata.model.userbadge.gateways.UserBadgeRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

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
}