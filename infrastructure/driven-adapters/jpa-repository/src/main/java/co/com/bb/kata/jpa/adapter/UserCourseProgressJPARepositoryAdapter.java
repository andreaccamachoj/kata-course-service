package co.com.bb.kata.jpa.adapter;

import co.com.bb.kata.jpa.UserCourseProgressJPARepository;
import co.com.bb.kata.jpa.entity.UserCourseProgressEntity;
import co.com.bb.kata.jpa.helper.AdapterOperations;
import co.com.bb.kata.model.usercourseprogress.UserCourseProgress;
import co.com.bb.kata.model.usercourseprogress.gateways.UserCourseProgressRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserCourseProgressJPARepositoryAdapter extends AdapterOperations<
        UserCourseProgress,
        UserCourseProgressEntity,
        Long,
        UserCourseProgressJPARepository>
        implements UserCourseProgressRepository
{

    public UserCourseProgressJPARepositoryAdapter(UserCourseProgressJPARepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, UserCourseProgress.class));
    }
}