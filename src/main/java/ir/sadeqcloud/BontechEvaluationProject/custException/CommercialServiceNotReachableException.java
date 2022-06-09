package ir.sadeqcloud.BontechEvaluationProject.custException;

public class CommercialServiceNotReachableException extends RuntimeException {
    private String commercialServiceName;

    public CommercialServiceNotReachableException(String commercialServiceName) {
        super(commercialServiceName+" not exists!");
        this.commercialServiceName = commercialServiceName;
    }

    public String getCommercialServiceName() {
        return commercialServiceName;
    }
}
