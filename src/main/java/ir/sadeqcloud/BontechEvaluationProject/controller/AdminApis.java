package ir.sadeqcloud.BontechEvaluationProject.controller;

import ir.sadeqcloud.BontechEvaluationProject.service.AdminServiceContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ADMIN")
public class AdminApis {

    private final AdminServiceContract adminServiceContract;

    @Autowired
    public AdminApis(AdminServiceContract adminServiceContract){
        this.adminServiceContract=adminServiceContract;
    }
}
