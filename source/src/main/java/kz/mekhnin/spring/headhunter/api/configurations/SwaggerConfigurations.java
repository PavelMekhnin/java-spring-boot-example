package kz.mekhnin.spring.headhunter.api.configurations;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Configuration
@EnableSwagger2
public class SwaggerConfigurations {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)//
                .select()//
                .apis(RequestHandlerSelectors.any())//
                .paths(Predicates.not(PathSelectors.regex("/error")))//
                .build()//
                .apiInfo(metadata())//
                .useDefaultResponseMessages(false)//
                .securitySchemes(new ArrayList<>(Arrays.asList(new ApiKey("Bearer %token", "Authorization", "Header"))))//
                .genericModelSubstitutes(Optional.class);
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()//
                .title("Head Hunter API")//
                .description("This is a sample of hiring web service.")//
                .version("1.0.0")//
                .license("MIT License").licenseUrl("http://opensource.org/licenses/MIT")//
                .contact(new Contact("Pavel Mekhnin", null, "pavel.mekhnin@gmail.com"))//
                .build();
    }
}
