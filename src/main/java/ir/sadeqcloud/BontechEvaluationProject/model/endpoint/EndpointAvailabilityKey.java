package ir.sadeqcloud.BontechEvaluationProject.model.endpoint;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class EndpointAvailabilityKey implements Serializable {
    @Enumerated(EnumType.STRING)
    private EndpointName endpointName;
    private LocalDate date;

    @Override
    public boolean equals(Object obj) {
        if (obj==this)
            return true;
        if (!(obj instanceof EndpointAvailabilityKey))
            return false;
        EndpointAvailabilityKey endpointAvailabilityKey = (EndpointAvailabilityKey) obj;
        if (this.date==null)
            return false;
        return this.date.isEqual(endpointAvailabilityKey.date) && (this.endpointName == endpointAvailabilityKey.endpointName);

    }
}
