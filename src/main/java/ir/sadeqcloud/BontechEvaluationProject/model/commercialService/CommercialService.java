package ir.sadeqcloud.BontechEvaluationProject.model.commercialService;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "commercial_service")
public class CommercialService {
    @Id
    @Column(name = "commercial_service_name")
    private String commercialServiceName;
    private BigDecimal cost;
    private Long usageLimitation;

    public String getCommercialServiceName() {
        return commercialServiceName;
    }

    public void setCommercialServiceName(String commercialServiceName) {
        this.commercialServiceName = commercialServiceName;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Long getUsageLimitation() {
        return usageLimitation;
    }

    public void setUsageLimitation(Long usageLimitation) {
        this.usageLimitation = usageLimitation;
    }

    @Override
    /**
     * to comply with HashTable data structure standards
     */
    public int hashCode() {
        HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
        hashCodeBuilder.append(commercialServiceName);
        return hashCodeBuilder.toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==this)
            return true;
        if (!(obj instanceof CommercialService))
            return false;
        return this.commercialServiceName != null && commercialServiceName.equals(((CommercialService) obj).commercialServiceName);
    }
}
