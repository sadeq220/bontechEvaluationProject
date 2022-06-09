package ir.sadeqcloud.BontechEvaluationProject.model.commercialService;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class CommercialServiceAvailabilityKey implements Serializable {
    @Column(name = "commercial_service_name")
    private String commercialServiceName;
    private LocalDate date;



    public CommercialServiceAvailabilityKey(){
        //empty constructor to comply with POJO
    }
    public CommercialServiceAvailabilityKey(String commercialServiceName, LocalDate date){
        this.date=date;
        this.commercialServiceName = commercialServiceName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==this)
            return true;
        if (!(obj instanceof CommercialServiceAvailabilityKey))
            return false;
        CommercialServiceAvailabilityKey commercialServiceAvailabilityKey = (CommercialServiceAvailabilityKey) obj;
        if (this.date==null && this.commercialServiceName==null)
            return false;
        return this.date.isEqual(commercialServiceAvailabilityKey.date) && (this.commercialServiceName.equals( commercialServiceAvailabilityKey.commercialServiceName));
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
        hashCodeBuilder.append(commercialServiceName);
        hashCodeBuilder.append(date);
        return hashCodeBuilder.toHashCode();
    }
}
