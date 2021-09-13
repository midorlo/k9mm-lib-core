package com.midorlo.k9.repository;

import com.midorlo.k9.domain.util.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * Contract for all Application Repositories. Has to be excluded from Repository Scan!
 *
 * @param <E> target Entity inheriting from {@link ApplicationEntity}.
 */
@NoRepositoryBean
public interface ApplicationRepository<E extends ApplicationEntity> extends JpaRepository<E, Long> {

   @NonNull
   Optional<E> findById(@NonNull Long id);

   @NonNull
   Optional<E> findByName(@NonNull String name);
}
