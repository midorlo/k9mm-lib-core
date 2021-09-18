package com.midorlo.k9.util;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.Properties;

/**
 * As of Spring 4.3, @PropertySource comes with the factory attribute. We can make use of it to provide our custom
 * implementation of the PropertySourceFactory, which will handle the YAML file processing.
 */
public class YamlPropertySourceFactory implements PropertySourceFactory {

    @Override
    @NonNull
    public PropertySource<?> createPropertySource(@Nullable String name,
                                                  @NonNull EncodedResource encodedResource) {
        YamlPropertiesFactoryBean factory  = new YamlPropertiesFactoryBean();
        Resource                  resource = encodedResource.getResource();
        factory.setResources(resource);
        Properties properties = factory.getObject();
        assert properties != null;
        return new PropertiesPropertySource(Objects.requireNonNull(resource.getFilename()), properties);
    }
}