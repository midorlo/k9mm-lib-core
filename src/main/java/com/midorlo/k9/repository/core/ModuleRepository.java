package com.midorlo.k9.repository.core;

import com.midorlo.k9.domain.core.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModuleRepository extends JpaRepository<Module, String> {

    Optional<Module> findByName(String name);
}