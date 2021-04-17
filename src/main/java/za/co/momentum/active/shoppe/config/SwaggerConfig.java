package za.co.momentum.active.shoppe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api (){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getAPiInformation())
                .select()
                .apis(RequestHandlerSelectors.basePackage("za.co.momentum.active.shoppe"))
                .paths(PathSelectors.ant("/**"))
                .build();
    }

    ApiInfo getAPiInformation(){
        return new ApiInfoBuilder()
                .title("Momentum Active Shoppe")
                .description("Momentum Active Shoppe is an online shopping site for customers wtih active days points")
                .version("0.0.1")
                .contact(new Contact("Kulani Nkwinika","","nkwinikak@gmail.com"))
                .build();
    }
}
