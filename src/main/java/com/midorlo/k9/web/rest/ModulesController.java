package com.midorlo.k9.web.rest;

import com.midorlo.k9.domain.core.Module;
import com.midorlo.k9.service.core.ModuleServices;
import com.midorlo.k9.web.PaginationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/modules")
public class ModulesController {

    private final ModuleServices moduleServices;

    private static final List<String> ALLOWED_ORDERED_PROPERTIES = List.of("name");

    public ModulesController(ModuleServices moduleServices) {this.moduleServices = moduleServices;}

    private boolean onlyContainsAllowedProperties(Pageable pageable) {
        return pageable.getSort().stream().map(Sort.Order::getProperty).allMatch(ALLOWED_ORDERED_PROPERTIES::contains);
    }

    @GetMapping("/")
    public ResponseEntity<List<Module>> getAllModules(Pageable pageable) {
        log.debug("REST request to get all modules");
        if (!onlyContainsAllowedProperties(pageable)) {
            return ResponseEntity.badRequest().build();
        }

        final Page<Module> page = moduleServices.findAll(pageable);
        HttpHeaders headers =
                PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Module> getAllModules(@PathVariable("name") String name) {
        log.debug("REST request to get modules with name {}", name);
        Module byId = moduleServices.findById(name);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }
}
