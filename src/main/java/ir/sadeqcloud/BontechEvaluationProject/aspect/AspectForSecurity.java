package ir.sadeqcloud.BontechEvaluationProject.aspect;

import ir.sadeqcloud.BontechEvaluationProject.service.serviceAvailability.CommercialServiceAvailabilityContract;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectForSecurity {
    private CommercialServiceAvailabilityContract serviceAvailabilityService;
    @Autowired
    public AspectForSecurity(CommercialServiceAvailabilityContract commercialServiceAvailabilityContract){
        this.serviceAvailabilityService= commercialServiceAvailabilityContract;
    }
}
