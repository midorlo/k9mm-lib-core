package com.midorlo.k9.configuration.core;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Shade over the section "wolkenbruch" within the application.yml resource file.
 */
@Configuration
@ConfigurationProperties(prefix = "k9")
@Data
public class ApplicationProperties {

    private final Security security = new Security();
    private final Storage storage = new Storage();
    private final Rest rest = new Rest();
    private final Apidocs apidocs = new Apidocs();
    private String version;

    @Data
    public static class Storage {
        private String basedir;
        private String headerFieldNameTotalCount;
    }

    @Data
    public static class Rest {
        private String headerFieldNameTotalCount;
    }

    @Data
    public static class Apidocs {

        private String title;
        private String description;
        private String tos;

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
    public static class Security {

        private String contentSecurityPolicy;

        private final Authentication authentication = new Authentication();
        private final Authorization authorization = new Authorization();
        private final Registration registration = new Registration();
        private final RememberMe rememberMe = new RememberMe();

        @Data
        public static class Registration {

            private String defaultAuthority;
            private String defaultLanguage;
            private Boolean requireActivation;
        }

        @Data
        public static class Authorization {

            private String headerName;
        }

        @Data
        public static class Authentication {

            private final Jwt jwt = new Jwt();

            @Data
            public static class Jwt {

                private String secret;
                private String base64Secret;
                private long tokenValidityInSeconds;
                private long tokenValidityInSecondsForRememberMe;
            }
        }

        @Data
        public static class RememberMe {

            @NotNull
            private String key;
        }
    }
    // All hail to our lord and savior, lombok!
}
