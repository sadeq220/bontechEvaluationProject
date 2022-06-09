package ir.sadeqcloud.BontechEvaluationProject.custException;

public class ServiceNotAvailableAtTheMoment extends RuntimeException {
    private String commercialServiceName;
    public ServiceNotAvailableAtTheMoment(String commercialServiceName){
        super(commercialServiceName+" not available at the moment!");
        this.commercialServiceName = commercialServiceName;
    }

    public String getCommercialServiceName() {
        return commercialServiceName;
    }
}
