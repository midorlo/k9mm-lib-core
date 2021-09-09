package com.midorlo.k9.configuration.core;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configures the API-Docs generation.
 */
@Configuration
public class OpenApiConfiguration {

    private final CoreConfiguration               coreConfiguration;
    private final CoreConfiguration.ApiDocs       apiDocs;
    private final CoreConfiguration.About.License license;
    private final CoreConfiguration.About.Contact contact;

    public OpenApiConfiguration(CoreConfiguration coreConfiguration) {
        this.coreConfiguration = coreConfiguration;
        this.apiDocs           = coreConfiguration.getApidocs();
        this.license           = coreConfiguration.getAbout().getLicense();
        this.contact           = coreConfiguration.getAbout().getContact();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title(apiDocs.getTitle())
                                            .version(coreConfiguration.getVersion())
                                            .description(apiDocs.getDescription())
                                            .termsOfService(apiDocs.getTos())
                                            .license(new License().name(license.getName())
                                                                  .url(license.getUrl()))
                                            .contact(new Contact().name(contact.getName())
                                                                  .email(contact.getEmail())));
    }
}
