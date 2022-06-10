package ir.sadeqcloud.BontechEvaluationProject.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(info = @Info(title = "simple financial application",version = "1.0",description = "simple users can use commercial services but must pay for it and also they have usage limit"))
@SecurityScheme(name = "simpleUser",type= SecuritySchemeType.HTTP,scheme = "basic",in = SecuritySchemeIn.COOKIE)
@SecurityScheme(name = "admin",type= SecuritySchemeType.HTTP,scheme = "basic",in = SecuritySchemeIn.COOKIE)
public class SwaggerConfiguration {
}
