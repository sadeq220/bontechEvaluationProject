package ir.sadeqcloud.BontechEvaluationProject.service.adminService;

import ir.sadeqcloud.BontechEvaluationProject.controller.dto.*;
import ir.sadeqcloud.BontechEvaluationProject.model.report.CommercialServiceUsage;
import ir.sadeqcloud.BontechEvaluationProject.service.dto.OperationResult;
import org.springframework.data.domain.Page;


public interface AdminServiceContract {
    OperationResult createUser(SimpleUserDto simpleUserDto);
    OperationResult createServiceAvailability(ServiceAvailabilityDto serviceAvailabilityDto);
    OperationResult createCommercialService(CommercialServiceDto commercialServiceDto);
    OperationResult addPrivilege(PrivilegeDto privilegeDto);
    OperationResult removePrivilege(PrivilegeDto privilegeDto);
    Page<CommercialServiceUsage> reportServiceUsage(boolean success, PageDto pageDto);
    Page<CommercialServiceUsage> reportServiceUsage(boolean success, PageDto pageDto,String username);
}
