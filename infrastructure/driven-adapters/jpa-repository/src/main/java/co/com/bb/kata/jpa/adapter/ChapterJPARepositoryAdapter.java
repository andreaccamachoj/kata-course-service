package co.com.bb.kata.jpa.adapter;

import co.com.bb.kata.jpa.ChapterJPARepository;
import co.com.bb.kata.jpa.entity.ChapterEntity;
import co.com.bb.kata.jpa.helper.AdapterOperations;
import co.com.bb.kata.model.chapter.Chapter;
import co.com.bb.kata.model.chapter.gateways.ChapterRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ChapterJPARepositoryAdapter extends AdapterOperations<
        Chapter,
        ChapterEntity,
        Long,
        ChapterJPARepository>
        implements ChapterRepository
{

    public ChapterJPARepositoryAdapter(ChapterJPARepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Chapter.class));
    }
}