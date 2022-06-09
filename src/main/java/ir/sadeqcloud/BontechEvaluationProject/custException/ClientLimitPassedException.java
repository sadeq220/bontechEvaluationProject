package ir.sadeqcloud.BontechEvaluationProject.custException;

public class ClientLimitPassedException extends RuntimeException{
    private String commercialServiceName;
    private Long limitationUsage;

    public ClientLimitPassedException(String commercialServiceName, Long limitationUsage) {
        super("you passed the limitation "+limitationUsage+" of : "+commercialServiceName);
        this.commercialServiceName = commercialServiceName;
        this.limitationUsage = limitationUsage;
    }

    public String getCommercialServiceName() {
        return commercialServiceName;
    }

    public Long getLimitationUsage() {
        return limitationUsage;
    }

}
