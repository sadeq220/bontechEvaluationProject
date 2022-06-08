package ir.sadeqcloud.BontechEvaluationProject.service.serviceAvailability;

import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialServiceName;

import java.time.LocalDateTime;

public interface ServiceAvailabilityService {
    boolean isServiceAvailable(CommercialServiceName commercialServiceName, LocalDateTime localDateTime);
}
