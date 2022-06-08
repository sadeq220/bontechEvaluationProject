package ir.sadeqcloud.BontechEvaluationProject.centralExceptionHandler;

import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialServiceName;

public class SimpleUSerExceptionResponse {
    private CommercialServiceName commercialServiceName;
    private String message;

    public SimpleUSerExceptionResponse(CommercialServiceName commercialServiceName, String message) {
        this.commercialServiceName = commercialServiceName;
        this.message = message;
    }

    public CommercialServiceName getCommercialServiceName() {
        return commercialServiceName;
    }

    public String getMessage() {
        return message;
    }
}
