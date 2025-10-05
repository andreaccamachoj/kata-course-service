package co.com.bb.kata.jpa.adapter;

import co.com.bb.kata.jpa.ModuleJPARepository;
import co.com.bb.kata.jpa.entity.ModuleEntity;
import co.com.bb.kata.jpa.helper.AdapterOperations;
import co.com.bb.kata.model.exception.TechnicalException;
import co.com.bb.kata.model.exception.message.TechnicalExceptionMessage;
import co.com.bb.kata.model.module.Module;
import co.com.bb.kata.model.module.gateways.ModuleRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.StreamSupport;

@Repository
public class ModuleJPARepositoryAdapter extends AdapterOperations<
        Module,
        ModuleEntity,
        Long,
        ModuleJPARepository>
implements ModuleRepository
{

    public ModuleJPARepositoryAdapter(ModuleJPARepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Module.class));
    }

    private static final Logger log = LoggerFactory.getLogger(ModuleJPARepositoryAdapter.class);

    @Override
    public List<Module> findAllModules() {
        log.info("[MODULE-REPOSITORY] Starting retrieval of all modules from database...");

        try {
            Iterable<ModuleEntity> iterable = repository.findAll();
            List<ModuleEntity> entities = StreamSupport
                    .stream(iterable.spliterator(), false)
                    .toList();

            log.debug("[MODULE-REPOSITORY] Retrieved {} module records from database.", entities.size());

            List<Module> modules = entities.stream()
                    .map(entity -> mapper.map(entity, Module.class))
                    .toList();

            log.info("[MODULE-REPOSITORY] Successfully mapped and returned {} modules.", modules.size());
            return modules;

        } catch (Exception ex) {
            log.error("[MODULE-REPOSITORY] Error occurred while fetching modules: {}", ex.getMessage(), ex);
            throw new TechnicalException(TechnicalExceptionMessage.GET_MODULES_ERROR);
        }
    }
}