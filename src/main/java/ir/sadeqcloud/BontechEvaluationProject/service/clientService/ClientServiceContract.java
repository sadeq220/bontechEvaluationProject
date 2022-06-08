package ir.sadeqcloud.BontechEvaluationProject.service.clientService;

import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialService;
import ir.sadeqcloud.BontechEvaluationProject.service.dto.CommercialServiceRequiredInput;

public interface ClientServiceContract {
    void useCommercialService(CommercialService commercialService, CommercialServiceRequiredInput commercialServiceRequiredInput);
}
