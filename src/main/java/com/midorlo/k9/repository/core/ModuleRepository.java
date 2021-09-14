package com.midorlo.k9.repository.core;

import com.midorlo.k9.domain.core.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module, String> {
}