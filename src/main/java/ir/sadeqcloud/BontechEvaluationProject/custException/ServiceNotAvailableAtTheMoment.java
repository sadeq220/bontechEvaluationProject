package ir.sadeqcloud.BontechEvaluationProject.custException;

import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialServiceName;

public class ServiceNotAvailableAtTheMoment extends RuntimeException {
    private CommercialServiceName commercialServiceName;
    public ServiceNotAvailableAtTheMoment(CommercialServiceName commercialServiceName){
        this.commercialServiceName = commercialServiceName;
    }

    public CommercialServiceName getCommercialServiceName() {
        return commercialServiceName;
    }
}
