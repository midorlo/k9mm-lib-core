package com.midorlo.k9;

import com.midorlo.k9.configuration.core.CoreProperties;
import com.midorlo.k9.domain.core.ComponentDescription;
import com.midorlo.k9.util.K9Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.lang.NonNull;

/**
 * The (solely technical!) consensus on how a k9 module works.
 */
@SpringBootApplication
@EnableConfigurationProperties({ CoreProperties.class })
public class LibCore extends K9Module {

    public static void main(String[] args) {
        SpringApplication.run(LibCore.class, args);
    }

    @Override
    @NonNull
    public ComponentDescription getModuleMeta() {
        return new ComponentDescription();
    }
}
