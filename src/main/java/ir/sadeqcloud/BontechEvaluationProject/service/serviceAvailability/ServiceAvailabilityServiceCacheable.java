package ir.sadeqcloud.BontechEvaluationProject.service.serviceAvailability;

import ir.sadeqcloud.BontechEvaluationProject.model.endpoint.EndpointAvailability;
import ir.sadeqcloud.BontechEvaluationProject.model.endpoint.EndpointAvailabilityKey;
import ir.sadeqcloud.BontechEvaluationProject.model.endpoint.EndpointName;
import ir.sadeqcloud.BontechEvaluationProject.repository.endpointRepository.EndpointAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class ServiceAvailabilityServiceCacheable  implements ServiceAvailabilityService{

    private final EndpointAvailabilityRepository endpointAvailabilityRepository;
    @Autowired
    public ServiceAvailabilityServiceCacheable(EndpointAvailabilityRepository endpointAvailabilityRepository){
        this.endpointAvailabilityRepository=endpointAvailabilityRepository;
    }
    @Override
    public boolean isServiceAvailable(EndpointName endpointName, LocalDateTime localDateTime) {
        LocalDate date = localDateTime.toLocalDate();
        EndpointAvailabilityKey endpointAvailabilityKey = new EndpointAvailabilityKey(endpointName, date);
        Optional<EndpointAvailability> endpointAvailabilityOptional = endpointAvailabilityRepository.findById(endpointAvailabilityKey);
        if (!endpointAvailabilityOptional.isPresent())
        return false;
        EndpointAvailability endpointAvailability = endpointAvailabilityOptional.get();

        return isInTimeInterval(endpointAvailability.getStartOfAvailability(),endpointAvailability.getEndOfAvailability(),localDateTime.toLocalTime());
    }

    private boolean isInTimeInterval(LocalTime startOfInterval,LocalTime endOfInterval,LocalTime specifiedTime){

        return specifiedTime.isAfter(startOfInterval) && specifiedTime.isBefore(endOfInterval);
    }
}
