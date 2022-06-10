package ir.sadeqcloud.BontechEvaluationProject.model.commercialService;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name="service_availability")
@Table(name = "service_availability")
public class CommercialServiceAvailability {

    @EmbeddedId
    /**
     * composite primary key to
     * identify service availability in each day
     */
    private CommercialServiceAvailabilityKey commercialServiceAvailabilityKey;

    private LocalTime startOfAvailability;
    private LocalTime endOfAvailability;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commercial_service_name")
    @MapsId("commercialServiceName")//maps to commercialServiceName in commercialServiceAvailabilityKey
    private CommercialService commercialService;//enforce ForeignKey constraint

    public CommercialServiceAvailability(){
        //empty constructor to comply with POJO
    }
    public CommercialServiceAvailability(CommercialService commercialService, LocalDate date,LocalTime startOfAvailability,LocalTime endOfAvailability){
        this.commercialService=commercialService;
        this.commercialServiceAvailabilityKey=new CommercialServiceAvailabilityKey(commercialService.getCommercialServiceName(),date);
        this.startOfAvailability=startOfAvailability;
        this.endOfAvailability=endOfAvailability;
    }
    public LocalTime getStartOfAvailability() {
        return startOfAvailability;
    }

    public void setStartOfAvailability(LocalTime startOfAvailability) {
        this.startOfAvailability = startOfAvailability;
    }

    public LocalTime getEndOfAvailability() {
        return endOfAvailability;
    }

    public void setEndOfAvailability(LocalTime endOfAvailability) {
        this.endOfAvailability = endOfAvailability;
    }

    public CommercialServiceAvailabilityKey getCommercialServiceAvailabilityKey() {
        return commercialServiceAvailabilityKey;
    }
    @PrePersist
    private void checkTimeOverBusinessRule(){
        if (endOfAvailability.isBefore(startOfAvailability)){
            throw new RuntimeException("endOfAvailability time must be AFTER startOfAvailability");
        }
        if(endOfAvailability.minus(Duration.ofHours(12l)).isAfter(startOfAvailability)){
            throw new RuntimeException("duration between startOfAvailability and endOfAvailability must be 12 hours at most");
        }
    }
}
