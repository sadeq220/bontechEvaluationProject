package ir.sadeqcloud.BontechEvaluationProject.controller;

import ir.sadeqcloud.BontechEvaluationProject.controller.dto.CommercialServiceDto;
import ir.sadeqcloud.BontechEvaluationProject.controller.dto.ServiceAvailabilityDto;
import ir.sadeqcloud.BontechEvaluationProject.controller.dto.SimpleUserDto;
import ir.sadeqcloud.BontechEvaluationProject.service.adminService.AdminServiceContract;
import ir.sadeqcloud.BontechEvaluationProject.service.dto.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.Filter;

@RestController
@RequestMapping("/ADMIN")
public class AdminApis {

    private final AdminServiceContract adminServiceContract;

    @Autowired
    @Qualifier("springSecurityFilterChain")
    private Filter springSecurityFilterChain;

    @Autowired
    public AdminApis(AdminServiceContract adminServiceContract){
        this.adminServiceContract=adminServiceContract;
    }

    @PostMapping("/create/user")
    public ResponseEntity<OperationResult> createUser(@RequestBody SimpleUserDto simpleUserDto){
        OperationResult userCreated = adminServiceContract.createUser(simpleUserDto);
        return ResponseEntity.ok(userCreated);
    }
    @PostMapping("/create/availability")
    public ResponseEntity<OperationResult> createServiceAvailability(@RequestBody ServiceAvailabilityDto serviceAvailabilityDto){
        OperationResult serviceAvailabilityCreated = adminServiceContract.createServiceAvailability(serviceAvailabilityDto);
        return ResponseEntity.ok(serviceAvailabilityCreated);
    }
    @PostMapping("/create/service")
    public ResponseEntity<OperationResult> createCommercialService(@RequestBody CommercialServiceDto commercialServiceDto){
        OperationResult serviceAvailabilityCreated = adminServiceContract.createCommercialService(commercialServiceDto);
        return ResponseEntity.ok(serviceAvailabilityCreated);
    }

}
