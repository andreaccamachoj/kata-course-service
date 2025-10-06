package co.com.bb.kata.jpa.adapter;

import co.com.bb.kata.jpa.UserChapterProgressJPARepository;
import co.com.bb.kata.jpa.entity.UserChapterProgressEntity;
import co.com.bb.kata.jpa.helper.AdapterOperations;
import co.com.bb.kata.model.userchapterprogress.UserChapterProgress;
import co.com.bb.kata.model.userchapterprogress.gateways.UserChapterProgressRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserChapterProgressJPARepositoryAdapter extends AdapterOperations<
        UserChapterProgress,
        UserChapterProgressEntity,
        Long,
        UserChapterProgressJPARepository>
        implements UserChapterProgressRepository
{

    public UserChapterProgressJPARepositoryAdapter(UserChapterProgressJPARepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, UserChapterProgress.class));
    }
}