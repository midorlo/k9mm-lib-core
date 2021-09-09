package com.midorlo.k9.configuration.core;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Things that suck when working with modules: Accidentally Overwritten application.properties. So let's prevent
 * doing that in the first place by having a distinct filename per module.
 *
 * <p>Properties specific to K9.</p>
 * Data-Bound from {@code core.yml}.
 */
@Data
@Configuration
@ConfigurationProperties(value = "core", ignoreUnknownFields = false)
public class CoreConfiguration {
    private final ApiDocs apidocs = new ApiDocs();
    private final About   about   = new About();
    private       String  version;

    @Data
    public static class About {

        private License license = new License();
        private Contact contact = new Contact();

        @Data
        public static class License {
            private String name;
            private String url;
        }

        @Data
        public static class Contact {
            private String name;
            private String email;
        }

    }

    @Data
    public static class ApiDocs {

        private String title;
        private String description;
        private String tos;


    }
}
