package ir.sadeqcloud.BontechEvaluationProject.centralExceptionHandler;

public class SimpleUSerExceptionResponse {
    private String commercialServiceName;
    private String message;

    public SimpleUSerExceptionResponse(String commercialServiceName, String message) {
        this.commercialServiceName = commercialServiceName;
        this.message = message;
    }

    public String getCommercialServiceName() {
        return commercialServiceName;
    }

    public String getMessage() {
        return message;
    }
}
