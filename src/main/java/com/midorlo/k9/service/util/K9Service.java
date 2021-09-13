package com.midorlo.k9.service.util;

import com.midorlo.k9.domain.util.ApplicationEntity;
import com.midorlo.k9.repository.ApplicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

/**
 * Service abstraction layer. Implements repeating required actions over all the application's entities.
 *
 * @param <E> Implementation Entity inheriting from {@link ApplicationEntity}.
 * @param <R> Implementation Entity Repository, inheriting from {@link ApplicationRepository}
 */
@Slf4j
public abstract class K9Service<
   E extends ApplicationEntity,
   R extends ApplicationRepository<E>> {

   protected final R repository;

   public K9Service(R repository) {
      this.repository = repository;
   }

   /**
    * Creates a new record from given entity.
    *
    * @param entity the entity.
    * @return the entity, representing a database record.
    */
   @Transactional
   public E create(E entity) {
      if (entity.getId() != null) {
         throw new java.lang.SecurityException("Forbidden to save a record with id present!");
      }
      K9Service.log.info("Creating {}", entity);
      return repository.save(entity);
   }

   /**
    * Creates a new record from given entity, if not found. Else reads and returns the record.
    *
    * @param entity the entity.
    * @return the entity, representing a database record.
    */
   public E createIfNotFound(E entity) {
      Optional<E> eByName = repository.findByName(entity.getName());
      return eByName.orElseGet(() -> create(entity));
   }

   /**
    * Query all records readonly.
    *
    * @return all stored records.
    */
   @Transactional(readOnly = true)
   public Page<E> findAllReadOnly(Pageable pageable) {
      return findAll(pageable);
   }

   /**
    * Query all records.
    *
    * @return all stored records.
    */
   public Page<E> findAll(Pageable pageable) {
      return repository.findAll(pageable);
   }

   public E findById(Long id) {
      return repository.findById(id)
                       .orElseThrow(EntityNotFoundException::new);
   }

   /**
    * Updates given entity.
    *
    * @param entity the entity.
    * @return the updated entity.
    */
   @Transactional
   public E update(E entity) {
      K9Service.log.info("Update Requested: {}", entity.toString());
      E entityById = repository.findById(entity.getId()).orElseThrow(EntityNotFoundException::new);
      if (!entityById.equals(entity)) {
         throw new RuntimeException("Cannot update distinct database records!");
      }
      K9Service.log.info("Updating {} to {}", entityById, entity);
      return repository.save(entity);
   }
}
