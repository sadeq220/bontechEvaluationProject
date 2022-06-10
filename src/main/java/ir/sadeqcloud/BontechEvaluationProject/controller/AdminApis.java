package ir.sadeqcloud.BontechEvaluationProject.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import ir.sadeqcloud.BontechEvaluationProject.controller.dto.*;
import ir.sadeqcloud.BontechEvaluationProject.model.report.CommercialServiceUsage;
import ir.sadeqcloud.BontechEvaluationProject.service.adminService.AdminServiceContract;
import ir.sadeqcloud.BontechEvaluationProject.service.dto.OperationResult;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.Filter;
import java.util.List;

@RestController
@RequestMapping("/ADMIN")
@SecurityRequirement(name = "admin")
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
    @PutMapping("/add/privilege")
    public ResponseEntity<OperationResult> addPrivilegeToUser(@RequestBody PrivilegeDto privilegeDto){
        OperationResult operationResult = adminServiceContract.addPrivilege(privilegeDto);
        return ResponseEntity.ok(operationResult);
    }
    @PutMapping("/remove/privilege")
    public ResponseEntity<OperationResult> removePrivilegeFromUser(@RequestBody PrivilegeDto privilegeDto){
        OperationResult operationResult = adminServiceContract.removePrivilege(privilegeDto);
        return ResponseEntity.ok(operationResult);
    }

    @PageableAsQueryParam
    @GetMapping("/report/service")
    public ResponseEntity<List<CommercialServiceUsage>> getReportOfServiceUsage(@RequestParam(defaultValue = "true")boolean success
                                                                               , @Parameter(hidden = true) @ModelAttribute PageDto pageDto
                                                                               , @RequestParam(required = false)String username){
        Page<CommercialServiceUsage> commercialServiceUsages = adminServiceContract.reportServiceUsage(success, pageDto,username);
        return ResponseEntity.ok(commercialServiceUsages.toList());
    }

}
