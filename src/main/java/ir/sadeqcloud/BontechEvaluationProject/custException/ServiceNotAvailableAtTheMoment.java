package ir.sadeqcloud.BontechEvaluationProject.custException;

public class ServiceNotAvailableAtTheMoment extends RuntimeException {
    private String commercialServiceName;
    public ServiceNotAvailableAtTheMoment(String commercialServiceName){
        this.commercialServiceName = commercialServiceName;
    }

    public String getCommercialServiceName() {
        return commercialServiceName;
    }
}
