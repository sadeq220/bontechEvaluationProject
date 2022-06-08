package ir.sadeqcloud.BontechEvaluationProject.model.commercialService;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalTime;

@Entity
@Table(name = "endpoint_availability")
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