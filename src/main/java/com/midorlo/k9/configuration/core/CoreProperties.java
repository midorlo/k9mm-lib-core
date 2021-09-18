package com.midorlo.k9.configuration.core;

import com.midorlo.k9.util.YamlPropertySourceFactory;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * <p>Properties specific to K9.</p>
 * Data-Bound from {@code core.yml}, also (optionally)  {@code git.properties} & {@code META-INF/build-info.properties}
 */
@Data
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "core")
@PropertySource(value = "classpath:core.yml", factory = YamlPropertySourceFactory.class)
public class CoreProperties {

    private String  name;
    private String  version;
    private Integer port;

    private final BuildProperties buildProperties;

    private final ApiDocs apidocs = new ApiDocs();
    private final About   about   = new About();
    private final Cache   cache   = new Cache();
    private final Logging logging = new Logging();

    public CoreProperties(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
                                  BuildProperties buildProperties) {
        log.info("Created" + this);
        this.buildProperties = buildProperties;
    }


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
            private int      expiration                            = 300;   // 5 minutes
            private boolean  cluster                               = false;
            private int      connectionPoolSize                    = 64;    // default as in redisson
            private int      connectionMinimumIdleSize             = 24;    // default as in redisson
            private int      subscriptionConnectionPoolSize        = 50;    // default as in redisson
            private int      subscriptionConnectionMinimumIdleSize = 1;     // default as in redisson
        }
    }

    @Data
    public static class Logging {

        private       boolean  useJsonFormat = false;
        private final Logstash logstash      = new Logstash();

        @Data
        @Accessors(chain = true)
        public static class Logstash {

            private boolean enabled   = false;
            private String  host      = null;
            private int     port      = 0;
            private int     queueSize = 0;
        }
    }

}
