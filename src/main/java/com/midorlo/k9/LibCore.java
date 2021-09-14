package com.midorlo.k9;

import com.midorlo.k9.configuration.core.CoreProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * The (solely technical!) consensus on how a k9 module works.
 */
@SpringBootApplication
@EnableConfigurationProperties({ CoreProperties.class })
public class LibCore extends AbstractK9Module {

    public static void main(String[] args) {
        SpringApplication.run(LibCore.class, args);
    }
}
