package com.midorlo.k9.service.core;

import com.midorlo.k9.domain.core.Module;
import com.midorlo.k9.service.DefaultService;
import com.midorlo.k9.repository.core.ModuleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ModuleServices extends DefaultService<Module, String, ModuleRepository> {

    public ModuleServices(ModuleRepository moduleRepository) {super(moduleRepository);}
}
