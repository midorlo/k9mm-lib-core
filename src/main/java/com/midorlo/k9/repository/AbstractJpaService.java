package com.midorlo.k9.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
public class AbstractJpaService<E, PK> {

    protected final JpaRepository<E, PK> repository;

    public AbstractJpaService(JpaRepository<E, PK> repository) {this.repository = repository;}

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
    public E findById(PK pk) {
        return repository.findById(pk).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Page<E> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

}
