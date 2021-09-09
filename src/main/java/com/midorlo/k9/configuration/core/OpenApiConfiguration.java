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

    private final CoreConfiguration CoreConfiguration;

    public OpenApiConfiguration(CoreConfiguration props) {
        this.CoreConfiguration = props;
    }

    @Bean
    public OpenAPI customOpenAPI() {

        CoreConfiguration.ApiDocs       apiDocs = CoreConfiguration.getApidocs();
        CoreConfiguration.About.License license = CoreConfiguration.getAbout().getLicense();
        CoreConfiguration.About.Contact contact = CoreConfiguration.getAbout().getContact();

        return new OpenAPI()
                .info(new Info()
                              .title(apiDocs.getTitle())
                              .version(CoreConfiguration.getVersion())
                              .description(apiDocs.getDescription())
                              .termsOfService(apiDocs.getTos())
                              .license(
                                      new License()
                                              .name(license.getName())
                                              .url(license.getUrl()))
                              .contact(new Contact()
                                               .name(contact.getName())
                                               .email(contact.getEmail()))
                     );
    }
}
