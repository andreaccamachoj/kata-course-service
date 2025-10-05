package co.com.bb.kata.usecase.module;

import co.com.bb.kata.model.module.Module;
import co.com.bb.kata.model.module.gateways.ModuleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ModuleUseCase {

    private final ModuleRepository moduleRepository;

    public List<Module> getAllModules() {
        return moduleRepository.findAllModules();
    }
}