package com.midorlo.k9.web.rest.core;

import com.midorlo.k9.domain.core.Module;
import com.midorlo.k9.service.core.ModuleServices;
import com.midorlo.k9.web.rest.ModulesControllerSuper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/modules")
public class ModulesController extends ModulesControllerSuper {

    public ModulesController(ModuleServices moduleServices) {super(moduleServices);}

    @GetMapping("/{name}")
    public ResponseEntity<Module> getAllModules(@PathVariable("name") String name) {
        log.debug("REST request to get modules with name {}", name);
        Module byId = moduleServices.findById(name).orElseThrow(() -> new RuntimeException("todo impl"));
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }
}
