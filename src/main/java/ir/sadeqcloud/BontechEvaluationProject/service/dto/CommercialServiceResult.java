package ir.sadeqcloud.BontechEvaluationProject.service.dto;

import java.time.LocalDateTime;

public class CommercialServiceResult implements CommercialServiceResultContract{
    private boolean isSuccessful;
    private String commercialServiceName;
    private LocalDateTime commercialServicePerformTime;

    public CommercialServiceResult(boolean isSuccessful, String commercialServiceName) {
        this.isSuccessful = isSuccessful;
        this.commercialServiceName = commercialServiceName;
        this.commercialServicePerformTime = LocalDateTime.now();
    }

    @Override
    public boolean isSuccessful() {
        return isSuccessful;
    }

    @Override
    public String getCommercialServiceName() {
        return commercialServiceName;
    }

    @Override
    public LocalDateTime getCommercialServicePerformTime() {
        return commercialServicePerformTime;
    }
}
