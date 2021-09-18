package com.midorlo.k9.beans;

import com.midorlo.k9.configuration.core.CoreProperties;
import com.midorlo.k9.domain.core.Module;
import com.midorlo.k9.service.core.ModuleServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CoreComponent {

    @Bean
    public CommandLineRunner initCore(ModuleServices moduleServices,
                                      CoreProperties coreProperties) {
        return args -> {
            if (!moduleServices.isModuleInstalled("Core")) {
                moduleServices.createOne(new Module("Core", true));
            }
            log.info(coreProperties.toString());
        };
    }
}
