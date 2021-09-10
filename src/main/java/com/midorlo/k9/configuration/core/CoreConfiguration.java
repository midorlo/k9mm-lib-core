package com.midorlo.k9.configuration.core;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * <p>Properties specific to K9.</p>
 * Data-Bound from {@code core.yml}, also (optionally)  {@code git.properties} & {@code META-INF/build-info.properties}
 */
@Data
@Configuration
@ConfigurationProperties(value = "core", ignoreUnknownFields = false)
@PropertySources({
        @PropertySource(value = "classpath:git.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "classpath:META-INF/build-info.properties", ignoreResourceNotFound = true)
})
public class CoreConfiguration {

    private final ApiDocs apidocs = new ApiDocs();
    private final About   about   = new About();
    private final Cache   cache   = new Cache();

    private String version;

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

    @Data
    public static class Cache {

        private final Hazelcast hazelcast = new Hazelcast();
        private final Caffeine  caffeine  = new Caffeine();
        private final Ehcache   ehcache   = new Ehcache();
        private final Redis     redis     = new Redis();

        @Data
        @Accessors(chain = true)
        public static class Hazelcast {

            private int timeToLiveSeconds = 3600;
            private int backupCount       = 1;
        }

        @Data
        @Accessors(chain = true)
        public static class Caffeine {

            private int  timeToLiveSeconds = 3600;
            private long maxEntries        = 100;
        }

        @Data
        @Accessors(chain = true)
        public static class Ehcache {
            private int  timeToLiveSeconds = 3600;
            private long maxEntries        = 100;
        }

        @Data
        @Accessors(chain = true)
        public static class Redis {
            private String[] server                                = { "redis://localhost:6379" };
            private int      expiration                            = 300; // 5 minutes
            private boolean  cluster                               = false;
            private int      connectionPoolSize                    = 64; // default as in redisson
            private int      connectionMinimumIdleSize             = 24; // default as in redisson
            private int      subscriptionConnectionPoolSize        = 50; // default as in redisson
            private int      subscriptionConnectionMinimumIdleSize = 1; // default as in redisson
        }
    }
}
