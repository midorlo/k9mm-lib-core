package com.midorlo.k9;

import com.midorlo.k9.configuration.core.CoreProperties;
import com.midorlo.k9.domain.core.Module;
import com.midorlo.k9.service.core.ModuleServices;
import com.midorlo.k9.service.core.ServletServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * The (solely technical!) consensus on how a k9 module works.
 */
@Slf4j
@SpringBootApplication
@EnableConfigurationProperties({ CoreProperties.class })
public class LibCore implements ApplicationListener<ContextRefreshedEvent> {

    public static void main(String[] args) {
        SpringApplication.run(LibCore.class, args);
    }

    @Bean
    public CommandLineRunner initCore(ModuleServices moduleServices,
                                      ServletServices servletServices) {
        return args -> {
            Module coreModule = moduleServices.create(new Module("Core", true));
        };
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("loaded " + getClass().getSimpleName() + " after event " + event.toString());
    }
}
