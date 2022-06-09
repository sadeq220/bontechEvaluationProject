package ir.sadeqcloud.BontechEvaluationProject.centralExceptionHandler.dto;

public class SimpleUSerExceptionResponseDto {
    private String commercialServiceName;
    private String message;

    public SimpleUSerExceptionResponseDto(String commercialServiceName, String message) {
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
