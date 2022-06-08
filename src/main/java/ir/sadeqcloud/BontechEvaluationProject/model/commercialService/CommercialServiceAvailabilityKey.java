package ir.sadeqcloud.BontechEvaluationProject.model.commercialService;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class CommercialServiceAvailabilityKey implements Serializable {
    @Enumerated(EnumType.STRING)
    private CommercialServiceName commercialServiceName;
    private LocalDate date;

    public CommercialServiceAvailabilityKey(){
        //empty constructor to comply with POJO
    }
    public CommercialServiceAvailabilityKey(CommercialServiceName commercialServiceName, LocalDate date){
        this.date=date;
        this.commercialServiceName = commercialServiceName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==this)
            return true;
        if (!(obj instanceof CommercialServiceAvailabilityKey))
            return false;
        CommercialServiceAvailabilityKey endpointAvailabilityKey = (CommercialServiceAvailabilityKey) obj;
        if (this.date==null)
            return false;
        return this.date.isEqual(endpointAvailabilityKey.date) && (this.commercialServiceName == endpointAvailabilityKey.commercialServiceName);

    }
}
