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

    private final CoreProperties               coreProperties;
    private final CoreProperties.ApiDocs       apiDocs;
    private final CoreProperties.About.License license;
    private final CoreProperties.About.Contact contact;

    public OpenApiConfiguration(CoreProperties coreProperties) {
        this.coreProperties = coreProperties;
        this.apiDocs        = coreProperties.getApidocs();
        this.license        = coreProperties.getAbout().getLicense();
        this.contact        = coreProperties.getAbout().getContact();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title(apiDocs.getTitle())
                                            .version(coreProperties.getVersion())
                                            .description(apiDocs.getDescription())
                                            .termsOfService(apiDocs.getTos())
                                            .license(new License().name(license.getName())
                                                                  .url(license.getUrl()))
                                            .contact(new Contact().name(contact.getName())
                                                                  .email(contact.getEmail())));
    }
}
