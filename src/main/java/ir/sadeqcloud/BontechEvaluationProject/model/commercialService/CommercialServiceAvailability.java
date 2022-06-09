package ir.sadeqcloud.BontechEvaluationProject.model.commercialService;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "service_availability")
public class CommercialServiceAvailability {

    @EmbeddedId
    /**
     * composite primary key to
     * identify service availability in each day
     */
    private CommercialServiceAvailabilityKey commercialServiceAvailabilityKey;
    //TODO custom constraint of 12 hours
    private LocalTime startOfAvailability;
    private LocalTime endOfAvailability;

    @OneToOne(fetch = FetchType.LAZY,orphanRemoval = true)
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
}
