package ir.sadeqcloud.BontechEvaluationProject.service.serviceAvailability;

import ir.sadeqcloud.BontechEvaluationProject.model.endpoint.EndpointName;

import java.time.LocalDateTime;

public interface ServiceAvailabilityService {
    boolean isServiceAvailable(EndpointName endpointName, LocalDateTime localDateTime);
}
