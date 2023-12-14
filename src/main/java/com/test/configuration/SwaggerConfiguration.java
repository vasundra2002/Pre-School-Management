package com.test.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;



@Configuration
public class SwaggerConfiguration {
	private ApiInfo apiInfo()
    {
        return new ApiInfo(
                
                "Student Details",
                "A basic REST app created with Spring boot",
                "1.0",
                "Terms of Services",
                "Vasundra",
                "www.abc.com",
                "vasundra2002@gmail.com"
                );
    }
    
    
    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
