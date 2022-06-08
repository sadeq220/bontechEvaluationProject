package ir.sadeqcloud.BontechEvaluationProject.service.dto;

import java.time.LocalDateTime;

public interface OperationResult {
    boolean getIsSuccessful();
    String getMessage();
    String getModelName();
    LocalDateTime getOperationPerformTime();
}
