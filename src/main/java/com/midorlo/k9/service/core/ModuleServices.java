package com.midorlo.k9.service.core;

import com.midorlo.k9.domain.core.Module;
import com.midorlo.k9.service.AbstractCrudService;
import com.midorlo.k9.repository.core.ModuleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ModuleServices extends AbstractCrudService<Module, String, ModuleRepository> {

    public ModuleServices(ModuleRepository moduleRepository) {super(moduleRepository);}

    public boolean isModuleInstalled(String moduleName) {
        Optional<Module> maybeModule = repository.findByName(moduleName);
        return !(maybeModule.isEmpty() || !maybeModule.get().isInitialized());
    }
}
