package com.rainbowflavor.roxyapiserver.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info =
@Info(
        title="roxy api server",
        description = "roxy blog 에 필요한 api 엔드포인트 제공",
        termsOfService = "dev",
        contact = @Contact(name="irostub", url="https://github.com/irostub", email="irostub@gmail.com"),
        version = "1.0.0"
))
public class SwaggerConfig {
}
