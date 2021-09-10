package com.midorlo.k9.configuration.cache;

import com.midorlo.k9.configuration.core.CoreConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@EnableCaching
@Slf4j
public class CacheConfiguration {

    private       GitProperties                                           gitProperties;
    private       BuildProperties                                         buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jCacheConfig;

    public CacheConfiguration(CoreConfiguration coreConfiguration) {
        CoreConfiguration.Cache.Ehcache ehcache = coreConfiguration.getCache().getEhcache();
        jCacheConfig = Eh107Configuration
                .fromEhcacheCacheConfiguration(
                        CacheConfigurationBuilder
                                .newCacheConfigurationBuilder(Object.class,
                                                              Object.class,
                                                              ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                                .withExpiry(
                                        ExpiryPolicyBuilder
                                                .timeToLiveExpiration(
                                                        Duration.ofSeconds(
                                                                ehcache.getTimeToLiveSeconds())))
                                .build());
    }

    /**
     * Tell hibernate which cache manager to use.
     *
     * @param cacheManager (Spoiler: It's Eh107CacheManager)
     * @return further configured HibernatePropertiesCustomizer.
     */
    @Bean
    public HibernatePropertiesCustomizer configureHibernatePropertiesCustomizer(CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    /**
     * Creates this module's caches.
     *
     * @return further configured customizer.
     */
    @Bean
    public JCacheManagerCustomizer configureModuleCaches() {
        return cm -> createCache(cm, com.midorlo.k9.domain.core.ModuleMeta.class.getName(), jCacheConfig);
    }

    /**
     * Have some neat special keys.
     *
     * @return keygen.
     */
    @Bean
    public KeyGenerator configureKeyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }

    /**
     * Utility method to create a cache (within a Bean) in a readable manner.
     *
     * @param cacheManager injected.
     * @param cacheName    unique.
     */
    private void createCache(javax.cache.CacheManager cacheManager, String cacheName, javax.cache.configuration.Configuration<Object, Object> jCacheConfig) {
        javax.cache.Cache<Object, Object> cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        } else {
            cacheManager.createCache(cacheName, jCacheConfig);
            log.info("Created Cache {}" + cacheName);
        }
    }

    /**
     * Sets git properties (if found)
     *
     * @param gitProperties src.
     */
    @Autowired(required = false)
    public void loadGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    /**
     * Sets build properties (if found)
     *
     * @param buildProperties src.
     */
    @Autowired(required = false)
    public void loadBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

}
