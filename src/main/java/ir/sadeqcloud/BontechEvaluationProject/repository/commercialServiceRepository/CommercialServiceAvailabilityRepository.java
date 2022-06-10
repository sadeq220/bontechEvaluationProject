package ir.sadeqcloud.BontechEvaluationProject.repository.commercialServiceRepository;

import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialServiceAvailability;
import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialServiceAvailabilityKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface CommercialServiceAvailabilityRepository extends JpaRepository<CommercialServiceAvailability, CommercialServiceAvailabilityKey> {
    @Query("SELECT sa FROM service_availability sa WHERE sa.startOfAvailability <= :moment AND sa.endOfAvailability >= :moment AND sa.commercialServiceAvailabilityKey.date = :date")
    Page<CommercialServiceAvailability> getAvailableServicesAtTheMoment(@Param("moment") LocalTime moment, @Param("date")LocalDate date,Pageable pageable);
}
