package ir.sadeqcloud.BontechEvaluationProject.centralExceptionHandler.dto;

public class ClientLimitationPassedResponseDto {
    private String commercialServiceName;
    private Long serviceLimitationUsage;
    private String message;

    public ClientLimitationPassedResponseDto(String commercialServiceName, Long serviceLimitationUsage) {
        this.commercialServiceName = commercialServiceName;
        this.serviceLimitationUsage = serviceLimitationUsage;
        this.message=String.format("service %s limitation %s passed",commercialServiceName,serviceLimitationUsage);
    }

    public String getCommercialServiceName() {
        return commercialServiceName;
    }

    public Long getServiceLimitationUsage() {
        return serviceLimitationUsage;
    }

    public String getMessage() {
        return message;
    }
}
