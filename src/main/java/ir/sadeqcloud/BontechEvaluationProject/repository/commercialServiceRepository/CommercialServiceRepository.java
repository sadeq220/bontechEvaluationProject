package ir.sadeqcloud.BontechEvaluationProject.repository.commercialServiceRepository;

import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommercialServiceRepository extends JpaRepository<CommercialService,String> {
}
