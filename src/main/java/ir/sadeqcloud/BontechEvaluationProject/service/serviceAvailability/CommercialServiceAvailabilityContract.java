package ir.sadeqcloud.BontechEvaluationProject.service.serviceAvailability;

import java.time.LocalDateTime;

public interface CommercialServiceAvailabilityContract {
    boolean isServiceAvailable(String commercialServiceName, LocalDateTime localDateTime);
}
