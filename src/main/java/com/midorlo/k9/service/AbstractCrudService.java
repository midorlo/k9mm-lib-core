package com.midorlo.k9.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implement simple crud features at service level.
 *
 * @param <E>  Entity Class.
 * @param <PK> Primary Key Serializable type of E.
 * @param <R> JPARepository of E
 */
@Slf4j
public class AbstractCrudService<E, PK extends Serializable, R extends JpaRepository<E, PK>> {

    protected final R repository;

    public AbstractCrudService(R repository) {this.repository = repository;}

    //<editor-fold desc="Create">
    @Transactional
    public E createOne(E entity) {
        return repository.save(entity);
    }

    @Transactional
    public List<E> createAll(Collection<E> entities) {
        return repository.saveAll(entities);
    }
    //</editor-fold>

    //<editor-fold desc="Read">
    @Transactional(readOnly = true)
    public List<E> readAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<E> readAll(Iterable<PK> pks) {
        return repository.findAllById(pks);
    }

    @Transactional(readOnly = true)
    public Page<E> readAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    public Optional<E> readOne(PK pk) {
        return repository.findById(pk);
    }
    //</editor-fold>

    //<editor-fold desc="Update">
    @Transactional
    public E update(PK pk, E updatedEntity) {
        E currentEntity = repository.findById(pk).orElseThrow(EntityNotFoundException::new);
        BeanUtils.copyProperties(updatedEntity, currentEntity);
        return repository.save(currentEntity);
    }

    @Transactional
    public List<E> updateAll(PK pk, List<E> updatedEntities) {
        return updatedEntities.stream()
                              .map(e -> update(pk, e))
                              .collect(Collectors.toList());
    }
    //</editor-fold>

    //<editor-fold desc="Delete">
    @Transactional
    public void delete(E entity) {
        repository.delete(entity);
    }

    @Transactional
    public void deleteAll(Collection<E> entity) {
        repository.deleteAll(entity);
    }

    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }
    //</editor-fold>
}
