package ir.sadeqcloud.BontechEvaluationProject.service.dto;

import java.time.LocalDateTime;

public class ServiceOperationResult implements OperationResult{
    private boolean isSuccessful;
    private String message;
    private String modelName;
    private LocalDateTime operationPerformTime;

    public ServiceOperationResult(boolean isSuccessful, String message, String modelName) {
        this.isSuccessful = isSuccessful;
        this.message = message;
        this.modelName = modelName;
        this.operationPerformTime=LocalDateTime.now();
    }

    @Override
    public boolean getIsSuccessful() {
        return isSuccessful;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getModelName() {
        return modelName;
    }

    @Override
    public LocalDateTime getOperationPerformTime() {
        return operationPerformTime;
    }
}
