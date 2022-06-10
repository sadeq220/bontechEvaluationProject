package ir.sadeqcloud.BontechEvaluationProject.service.clientService;

import ir.sadeqcloud.BontechEvaluationProject.controller.dto.CommercialServiceDto;
import ir.sadeqcloud.BontechEvaluationProject.controller.dto.PageDto;
import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialServiceAvailability;
import ir.sadeqcloud.BontechEvaluationProject.model.report.CommercialServiceUsage;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface ClientReportServiceContract {
    Page<CommercialServiceUsage> reportServiceUsage(boolean success, PageDto pageDto);
    Page<CommercialServiceAvailability> getAvailableServicesAtTheMoment(PageDto pageDto);
    Set<CommercialServiceDto> getAllowableServices();
}
