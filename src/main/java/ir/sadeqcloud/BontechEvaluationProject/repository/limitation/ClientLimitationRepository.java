package ir.sadeqcloud.BontechEvaluationProject.repository.limitation;

import ir.sadeqcloud.BontechEvaluationProject.model.limitation.ClientLimitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientLimitationRepository extends JpaRepository<ClientLimitation,Long> {

    Optional<ClientLimitation> findClientLimitationByUsernameAndCommercialServiceName(String username,String commercialServiceName);

}
