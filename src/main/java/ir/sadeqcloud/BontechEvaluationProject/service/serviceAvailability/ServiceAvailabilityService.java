package ir.sadeqcloud.BontechEvaluationProject.service.serviceAvailability;

import java.time.LocalDateTime;

public interface ServiceAvailabilityService {
    boolean isServiceAvailable(String commercialServiceName, LocalDateTime localDateTime);
}
