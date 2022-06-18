package ir.sadeqcloud.BontechEvaluationProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectToSwagger {
    @GetMapping("/")
    public String getSwaggerUi(){
        return "redirect:swagger-ui.html";
    }
}
