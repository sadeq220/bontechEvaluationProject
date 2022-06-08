package ir.sadeqcloud.BontechEvaluationProject.service.dto;

import java.time.LocalDateTime;

public class ServiceOperationResult implements OperationResult{
    private boolean isSuccessful;
    private String message;
    private String operationName;
    private LocalDateTime operationPerformTime;

    public ServiceOperationResult(boolean isSuccessful, String message, String operationName) {
        this.isSuccessful = isSuccessful;
        this.message = message;
        this.operationName = operationName;
        this.operationPerformTime=LocalDateTime.now();
    }

    @Override
    public boolean getIsSuccessful() {
        return isSuccessful;
    }

    @Override
    public void setIsSuccessful(boolean isSuccessful) {
    this.isSuccessful=isSuccessful;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
    this.message=message;
    }

    @Override
    public String getOperationName() {
        return operationName;
    }

    @Override
    public LocalDateTime getOperationPerformTime() {
        return operationPerformTime;
    }
}
