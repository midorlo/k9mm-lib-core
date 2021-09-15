package com.midorlo.k9.service.core;

import com.midorlo.k9.domain.core.Module;
import com.midorlo.k9.service.AbstractJpaService;
import com.midorlo.k9.repository.core.ModuleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ModuleServices extends AbstractJpaService<Module, String, ModuleRepository> {

    public ModuleServices(ModuleRepository moduleRepository) {super(moduleRepository);}
}
