package com.midorlo.k9.repository.core;

import com.midorlo.k9.domain.core.Servlet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServletRepository extends JpaRepository<Servlet, String> {
}