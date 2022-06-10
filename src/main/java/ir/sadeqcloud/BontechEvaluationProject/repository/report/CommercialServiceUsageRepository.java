package ir.sadeqcloud.BontechEvaluationProject.repository.report;

import ir.sadeqcloud.BontechEvaluationProject.model.report.CommercialServiceUsage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommercialServiceUsageRepository extends JpaRepository<CommercialServiceUsage,Long>{
    @Query("SELECT serviceUsage FROM service_usage serviceUsage WHERE serviceUsage.wasSuccessful=:success")
    Page<CommercialServiceUsage> getPageableResult(@Param("success")boolean success, Pageable pageable);
    Page<CommercialServiceUsage> getAllByUsername(String username,Pageable pageable);
    Page<CommercialServiceUsage> getAllByUsernameAndWasSuccessful(String username,boolean wasSuccessful,Pageable pageable);
}
