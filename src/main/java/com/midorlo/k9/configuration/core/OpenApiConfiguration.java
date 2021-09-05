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

   private final ApplicationProperties props;

   public OpenApiConfiguration(ApplicationProperties props) {
      this.props = props;
   }

   @Bean
   public OpenAPI customOpenAPI() {

      ApplicationProperties.Apidocs apidocs = props.getApidocs();
      ApplicationProperties.Apidocs.License license = apidocs.getLicense();
      ApplicationProperties.Apidocs.Contact contact = apidocs.getContact();

      return new OpenAPI()
         .info(new Info()
            .title(apidocs.getTitle())
            .version(props.getVersion())
            .description(apidocs.getDescription())
            .termsOfService(apidocs.getTos())
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
