package com.midorlo.k9.web.rest.core;

import com.midorlo.k9.domain.core.Module;
import com.midorlo.k9.repository.core.ModuleRepository;
import com.midorlo.k9.service.core.ModuleServices;
import com.midorlo.k9.web.rest.DefaultController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/modules")
public class ModulesController extends DefaultController<Module, String, ModuleRepository, ModuleServices> {

    public ModulesController(ModuleServices moduleServices) {super(moduleServices);}


}
