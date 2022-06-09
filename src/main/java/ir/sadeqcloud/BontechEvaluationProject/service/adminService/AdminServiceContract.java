package ir.sadeqcloud.BontechEvaluationProject.service.adminService;

import ir.sadeqcloud.BontechEvaluationProject.controller.dto.CommercialServiceDto;
import ir.sadeqcloud.BontechEvaluationProject.controller.dto.PrivilegeDto;
import ir.sadeqcloud.BontechEvaluationProject.controller.dto.ServiceAvailabilityDto;
import ir.sadeqcloud.BontechEvaluationProject.controller.dto.SimpleUserDto;
import ir.sadeqcloud.BontechEvaluationProject.service.dto.OperationResult;


public interface AdminServiceContract {
    OperationResult createUser(SimpleUserDto simpleUserDto);
    OperationResult createServiceAvailability(ServiceAvailabilityDto serviceAvailabilityDto);
    OperationResult createCommercialService(CommercialServiceDto commercialServiceDto);
    OperationResult addPrivilege(PrivilegeDto privilegeDto);
}
