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
import org.springframework.data.domain.Pageable;
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
    @DeleteMapping("/remove/service/{serviceName}")
    public ResponseEntity<OperationResult> removeCommercialService(@PathVariable(name = "serviceName")String commercialServiceName){
        OperationResult operationResult = adminServiceContract.removeCommercialService(commercialServiceName);
        return ResponseEntity.ok(operationResult);
    }
    @PutMapping("/add/credit")
    public ResponseEntity<OperationResult> addCreditToUsername(@RequestBody CreditDto creditDto){
        OperationResult operationResult = adminServiceContract.addCredit(creditDto);
        return ResponseEntity.ok(operationResult);
    }
    @PageableAsQueryParam
    @GetMapping("/report/list/simple/users")
    public ResponseEntity<List<SimpleUserDetailedDto>> reportListOfSimpleUsers(@Parameter(hidden = true) Pageable pageable){
        Page<SimpleUserDetailedDto> simpleUserDetailedDtos = adminServiceContract.reportSimpleUsersDetails(pageable);
        return ResponseEntity.ok(simpleUserDetailedDtos.toList());
    }
    @PageableAsQueryParam
    @GetMapping("/report/list/service")
    public ResponseEntity<List<CommercialServiceDto>> reportListOfCommercialServices(@Parameter(hidden = true) Pageable pageable){
        Page<CommercialServiceDto> commercialServiceDtos = adminServiceContract.reportListOfCommercialServices(pageable);
        return ResponseEntity.ok(commercialServiceDtos.toList());
    }
    @PageableAsQueryParam
    @GetMapping("/report/list/availability")
    public ResponseEntity<List<ServiceAvailabilityDto>> reportListOfServiceAvailabilities(@Parameter(hidden = true) Pageable pageable){
        Page<ServiceAvailabilityDto> serviceAvailabilityDtos = adminServiceContract.reportListOfServiceAvailability(pageable);
        return ResponseEntity.ok(serviceAvailabilityDtos.toList());
    }

    @PageableAsQueryParam
    @GetMapping("/report/service")
    public ResponseEntity<List<CommercialServiceUsage>> getReportOfServiceUsage(@RequestParam(defaultValue = "true")boolean success
                                                                               , @Parameter(hidden = true) @ModelAttribute PageDto pageDto
                                                                               , @RequestParam(required = false)String username){
        Page<CommercialServiceUsage> commercialServiceUsages = adminServiceContract.reportServiceUsage(success, pageDto,username);
        return ResponseEntity.ok(commercialServiceUsages.toList());
    }
    @DeleteMapping("/remove/user/{username}")
    public ResponseEntity<OperationResult> deleteSimpleUser(@PathVariable("username") String username){
        OperationResult operationResult = adminServiceContract.removeSimpleUser(username);
        return ResponseEntity.ok(operationResult);
    }

}
