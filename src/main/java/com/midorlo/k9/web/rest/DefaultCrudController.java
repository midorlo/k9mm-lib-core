package com.midorlo.k9.web.rest;

import com.midorlo.k9.service.AbstractCrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.util.List;

@Slf4j
public abstract class DefaultCrudController<
        E,
        PK extends Serializable,
        R extends JpaRepository<E, PK>,
        S extends AbstractCrudService<E, PK, R>> {

    protected static final List<String> ALLOWED_ORDERED_PROPERTIES = List.of("name");
    protected final        S            service;

    protected DefaultCrudController(S service) {this.service = service;}

    private boolean onlyContainsAllowedProperties(Pageable pageable) {
        return pageable.getSort().stream().map(Sort.Order::getProperty)
                       .allMatch(DefaultCrudController.ALLOWED_ORDERED_PROPERTIES::contains);
    }

    @GetMapping("/")
    public ResponseEntity<List<E>> getAll(Pageable pageable) {
        log.debug("REST request to get all record");
        if (!onlyContainsAllowedProperties(pageable)) {
            return ResponseEntity.badRequest().build();
        }

        final Page<E> page = service.findAll(pageable);
        HttpHeaders headers =
                RestUtilities.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(),
                                                            page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/{pk}")
    public ResponseEntity<E> getOne(@PathVariable("pk") PK pk) {
        log.debug("REST request to get records with pk = {}", pk);
        E byId = service.findOne(pk).orElseThrow(() -> new RuntimeException("todo impl"));
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }
}
