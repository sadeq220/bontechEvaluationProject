package ir.sadeqcloud.BontechEvaluationProject.service.dto;

import java.time.LocalDateTime;

public interface OperationResult {
    boolean getIsSuccessful();
    void setIsSuccessful(boolean isSuccessful);
    String getMessage();
    void setMessage(String message);
    String getModelName();
    LocalDateTime getOperationPerformTime();
}
