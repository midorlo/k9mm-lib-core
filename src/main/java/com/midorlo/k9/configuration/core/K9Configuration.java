//package com.midorlo.k9.configuration.core;
//
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Component;
//
//@Component
//@PropertySource("classpath:configuration.yaml")
//public class K9Configuration {
//
//    private final Environment env;
//
//    public K9Configuration(Environment env) {
//        this.env = env;
//    }
//
//    public String getConfigValue(String configKey) {
//        return env.getProperty(configKey);
//    }
//}
