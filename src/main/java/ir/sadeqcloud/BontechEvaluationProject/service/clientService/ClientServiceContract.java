package ir.sadeqcloud.BontechEvaluationProject.service.clientService;

import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialServiceName;
import ir.sadeqcloud.BontechEvaluationProject.service.dto.CommercialServiceRequiredInput;

public interface ClientServiceContract {
    void useCommercialService(CommercialServiceName commercialServiceName, CommercialServiceRequiredInput commercialServiceRequiredInput);
}
