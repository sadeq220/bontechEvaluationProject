package ir.sadeqcloud.BontechEvaluationProject.repository.endpointRepository;

import ir.sadeqcloud.BontechEvaluationProject.model.endpoint.EndpointAvailability;
import ir.sadeqcloud.BontechEvaluationProject.model.endpoint.EndpointAvailabilityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndpointAvailabilityRepository extends JpaRepository<EndpointAvailability, EndpointAvailabilityKey> {
}
