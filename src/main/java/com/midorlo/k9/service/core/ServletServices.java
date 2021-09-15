package com.midorlo.k9.service.core;

import com.midorlo.k9.domain.core.Servlet;
import com.midorlo.k9.service.DefaultService;
import com.midorlo.k9.repository.core.ServletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ServletServices extends DefaultService<Servlet, String, ServletRepository> {

    public ServletServices(ServletRepository servletRepository) {
        super(servletRepository);
    }
}
