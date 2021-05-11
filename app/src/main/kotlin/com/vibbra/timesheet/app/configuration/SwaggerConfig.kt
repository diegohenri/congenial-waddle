package com.vibbra.timesheet.app.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*


@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun api(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .securityContexts(listOf(securityContext()))
            .securitySchemes(listOf(apiKey()))
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
    }


    private fun apiKey(): BasicAuth? {
        return BasicAuth("basicAuth")
    }

    fun securityContext(): SecurityContext {
        return SecurityContext.builder().securityReferences(defaultAuth()).build()
    }

    private fun defaultAuth(): List<SecurityReference> {
        val authorizationScope = AuthorizationScope("global", "accessEverything")
        return listOf(SecurityReference("JWT", arrayOf(authorizationScope)))
    }


    private fun apiInfo(): ApiInfo? {
        return ApiInfo(
            "My REST API",
            "Some custom description of API.",
            "1.0",
            "Terms of service",
            Contact("Sallo Szrajbman", "www.baeldung.com", "salloszraj@gmail.com"),
            "License of API",
            "API license URL",
            Collections.emptyList())
    }

}
