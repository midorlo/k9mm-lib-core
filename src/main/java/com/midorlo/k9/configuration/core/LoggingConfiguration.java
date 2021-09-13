package com.midorlo.k9.configuration.core;

import ch.qos.logback.classic.LoggerContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static com.midorlo.k9.configuration.core.LoggingUtils.*;

/*
 * Configures the console and Logstash log appenders from the app properties
 */
@Configuration
public class LoggingConfiguration {

    private final CoreProperties coreProperties;

    public LoggingConfiguration(CoreProperties coreProperties,
                                ObjectMapper mapper) throws JsonProcessingException {
        this.coreProperties = coreProperties;

        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

        Map<String, String> map = new HashMap<>();
        map.put("app_name", "k9");
        map.put("app_port", "8080");

        String customFields = mapper.writeValueAsString(map);

        CoreProperties.Logging loggingProperties = coreProperties.getLogging();

        if (loggingProperties.isUseJsonFormat()) {
            addJsonConsoleAppender(context, customFields);
        }
        if (coreProperties.getLogging().getLogstash().isEnabled()) {
            addLogstashTcpSocketAppender(context, customFields, coreProperties.getLogging().getLogstash());
        }
        if (loggingProperties.isUseJsonFormat() || coreProperties.getLogging().getLogstash().isEnabled()) {
            addContextListener(context, customFields, loggingProperties);
        }
    }
}
