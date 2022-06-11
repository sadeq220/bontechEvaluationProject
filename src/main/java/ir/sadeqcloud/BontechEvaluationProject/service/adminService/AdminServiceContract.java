package ir.sadeqcloud.BontechEvaluationProject.service.adminService;

import ir.sadeqcloud.BontechEvaluationProject.controller.dto.*;
import ir.sadeqcloud.BontechEvaluationProject.model.report.CommercialServiceUsage;
import ir.sadeqcloud.BontechEvaluationProject.service.dto.OperationResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AdminServiceContract {
    OperationResult createUser(SimpleUserDto simpleUserDto);
    OperationResult createServiceAvailability(ServiceAvailabilityDto serviceAvailabilityDto);
    OperationResult createCommercialService(CommercialServiceDto commercialServiceDto);
    OperationResult addPrivilege(PrivilegeDto privilegeDto);
    OperationResult removePrivilege(PrivilegeDto privilegeDto);
    OperationResult removeCommercialService(String commercialServiceName);
    OperationResult removeSimpleUser(String username);
    OperationResult addCredit(CreditDto creditDto);
    Page<CommercialServiceUsage> reportServiceUsage(boolean success, PageDto pageDto);
    Page<CommercialServiceUsage> reportServiceUsage(boolean success, PageDto pageDto,String username);
    Page<SimpleUserDetailedDto> reportSimpleUsersDetails(Pageable pageable);
    Page<ServiceAvailabilityDto> reportListOfServiceAvailability(Pageable pageable);
    Page<CommercialServiceDto> reportListOfCommercialServices(Pageable pageable);
}
