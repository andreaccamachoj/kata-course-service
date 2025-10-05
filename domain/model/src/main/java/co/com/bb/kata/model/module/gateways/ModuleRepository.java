package co.com.bb.kata.model.module.gateways;

import co.com.bb.kata.model.module.Module;

import java.util.List;

public interface ModuleRepository {
    public List<Module> findAllModules();
}
