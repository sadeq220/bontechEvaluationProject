package ir.sadeqcloud.BontechEvaluationProject.service.dto;

import java.time.LocalDateTime;

public interface CommercialServiceResultContract {
    boolean isSuccessful();
    String getCommercialServiceName();
    LocalDateTime getCommercialServicePerformTime();
}
