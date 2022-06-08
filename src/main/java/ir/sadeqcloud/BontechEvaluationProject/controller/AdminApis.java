package ir.sadeqcloud.BontechEvaluationProject.controller;

import ir.sadeqcloud.BontechEvaluationProject.controller.dto.SimpleUserDto;
import ir.sadeqcloud.BontechEvaluationProject.service.AdminServiceContract;
import ir.sadeqcloud.BontechEvaluationProject.service.dto.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @PostMapping("/create/user")
    public ResponseEntity createUser(@RequestBody SimpleUserDto simpleUserDto){
        OperationResult userCreated = adminServiceContract.createUser(simpleUserDto);
        return ResponseEntity.ok(userCreated);
    }
}
