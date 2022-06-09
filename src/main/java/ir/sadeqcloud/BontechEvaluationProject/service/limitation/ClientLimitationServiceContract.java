package ir.sadeqcloud.BontechEvaluationProject.service.limitation;

import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialService;

public interface ClientLimitationServiceContract {
    boolean isClientLimitationPassed(CommercialService commercialService);
    void incrementServiceUse(String commercialServiceName);
    void deleteAllRows();
}
