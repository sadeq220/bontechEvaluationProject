package ir.sadeqcloud.BontechEvaluationProject.aspect;

import ir.sadeqcloud.BontechEvaluationProject.service.serviceAvailability.ServiceAvailabilityService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectForSecurity {
    private ServiceAvailabilityService serviceAvailabilityService;
    @Autowired
    public AspectForSecurity(ServiceAvailabilityService serviceAvailabilityService){
        this.serviceAvailabilityService=serviceAvailabilityService;
    }
}
