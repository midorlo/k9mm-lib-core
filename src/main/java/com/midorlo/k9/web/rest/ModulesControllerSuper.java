package com.midorlo.k9.web.rest;

import com.midorlo.k9.domain.core.Module;
import com.midorlo.k9.service.core.ModuleServices;
import com.midorlo.k9.web.PaginationUtilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Slf4j
public abstract class ModulesControllerSuper {

    protected static final List<String>   ALLOWED_ORDERED_PROPERTIES = List.of("name");
    protected final        ModuleServices moduleServices;

    protected ModulesControllerSuper(ModuleServices moduleServices) {this.moduleServices = moduleServices;}

    private boolean onlyContainsAllowedProperties(Pageable pageable) {
        return pageable.getSort().stream().map(Sort.Order::getProperty)
                       .allMatch(ModulesControllerSuper.ALLOWED_ORDERED_PROPERTIES::contains);
    }

    @GetMapping("/")
    public ResponseEntity<List<Module>> getAll(Pageable pageable) {
        log.debug("REST request to get all modules");
        if (!onlyContainsAllowedProperties(pageable)) {
            return ResponseEntity.badRequest().build();
        }

        final Page<Module> page = moduleServices.findAll(pageable);
        HttpHeaders headers =
                PaginationUtilities.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(),
                                                                  page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
