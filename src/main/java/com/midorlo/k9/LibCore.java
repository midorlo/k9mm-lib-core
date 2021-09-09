package com.midorlo.k9;

import com.midorlo.k9.configuration.core.CoreConfiguration;
import com.midorlo.k9.domain.core.ModuleMeta;
import com.midorlo.k9.utils.K9Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.lang.NonNull;

/**
 * The (solely technical!) consensus on how a k9 module works.
 */
@SpringBootApplication
@EnableConfigurationProperties({ CoreConfiguration.class })
public class LibCore extends K9Module {

    public static void main(String[] args) {
        SpringApplication.run(LibCore.class, args);
    }

    @Override
    @NonNull
    public ModuleMeta getModuleMeta() {
        return new ModuleMeta();
    }
}
