package ir.sadeqcloud.BontechEvaluationProject.service.serviceAvailability;

import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialServiceAvailability;
import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialServiceAvailabilityKey;
import ir.sadeqcloud.BontechEvaluationProject.repository.commercialServiceRepository.CommercialServiceAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class CommercialServiceAvailabilityCacheable implements CommercialServiceAvailabilityContract {

    private final CommercialServiceAvailabilityRepository endpointAvailabilityRepository;
    @Autowired
    public CommercialServiceAvailabilityCacheable(CommercialServiceAvailabilityRepository endpointAvailabilityRepository){
        this.endpointAvailabilityRepository=endpointAvailabilityRepository;
    }
    @Override
    public boolean isServiceAvailable(String commercialServiceName, LocalDateTime localDateTime) {
        LocalDate date = localDateTime.toLocalDate();
        CommercialServiceAvailabilityKey commercialServiceAvailabilityKey = new CommercialServiceAvailabilityKey(commercialServiceName, date);
        Optional<CommercialServiceAvailability> endpointAvailabilityOptional = endpointAvailabilityRepository.findById(commercialServiceAvailabilityKey);
        if (!endpointAvailabilityOptional.isPresent())
        return false;
        CommercialServiceAvailability commercialServiceAvailability = endpointAvailabilityOptional.get();

        return isInTimeInterval(commercialServiceAvailability.getStartOfAvailability(), commercialServiceAvailability.getEndOfAvailability(),localDateTime.toLocalTime());
    }

    private boolean isInTimeInterval(LocalTime startOfInterval,LocalTime endOfInterval,LocalTime specifiedTime){

        return specifiedTime.isAfter(startOfInterval) && specifiedTime.isBefore(endOfInterval);
    }
}
