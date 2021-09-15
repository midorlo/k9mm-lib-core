package com.midorlo.k9.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Slf4j
public class DefaultService<E, PK extends Serializable, R extends JpaRepository<E, PK>> {

    protected final R repository;

    public DefaultService(R repository) {this.repository = repository;}

    @Transactional(readOnly = true)
    public List<E> findAll() {
        return repository.findAll();
    }

    @Transactional
    public E create(E entity) {
        E storedEntity = repository.save(entity);
        log.debug("Stored {}", storedEntity);
        return storedEntity;
    }

    @Transactional
    public E update(PK pk, E updatedEntity) {
        E currentEntity = repository.findById(pk).orElseThrow(EntityNotFoundException::new);
        BeanUtils.copyProperties(updatedEntity, currentEntity);
        return repository.save(currentEntity);
    }

    @Transactional
    public void delete(E entity) {
        repository.delete(entity);
    }

    @Transactional(readOnly = true)
    public List<E> findAllById(Iterable<PK> pks) {
        return repository.findAllById(pks);
    }

    @Transactional
    public Optional<E> findById(PK pk) {
        return repository.findById(pk);
    }

    @Transactional(readOnly = true)
    public Page<E> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

}
