package ir.sadeqcloud.BontechEvaluationProject.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.SpringDocUtils;
import org.springframework.context.annotation.Bean;

import java.time.LocalTime;

/**
 * swagger 3 implements OpenApi specification
 */
@OpenAPIDefinition(info = @Info(title = "simple financial application",version = "1.0",description = "simple users can use commercial services but must pay for it and also they have usage limit"))
@SecurityScheme(name = "simpleUser",type= SecuritySchemeType.HTTP,scheme = "basic",in = SecuritySchemeIn.COOKIE)
@SecurityScheme(name = "admin",type= SecuritySchemeType.HTTP,scheme = "basic",in = SecuritySchemeIn.COOKIE)
public class SwaggerConfiguration {
/*    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("basicScheme",
                        new io.swagger.v3.oas.models.security.SecurityScheme().type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP).scheme("basic")))
                .info(new io.swagger.v3.oas.models.info.Info().title("SpringShop API").version("1.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
 */

}
