package ir.sadeqcloud.BontechEvaluationProject.aspect;

import ir.sadeqcloud.BontechEvaluationProject.repository.endpointRepository.EndpointAvailabilityRepository;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectForSecurity {
    private EndpointAvailabilityRepository endpointAvailabilityRepository;
    @Autowired
    public AspectForSecurity(EndpointAvailabilityRepository endpointAvailabilityRepository){
        this.endpointAvailabilityRepository=endpointAvailabilityRepository;
    }
}
