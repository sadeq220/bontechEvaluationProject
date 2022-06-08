package ir.sadeqcloud.BontechEvaluationProject.externalizedConfiguration.dto;

import org.springframework.boot.context.properties.ConstructorBinding;

import java.math.BigDecimal;

@ConstructorBinding
public class CommercialServiceProperties {
    private final BigDecimal cost;
    private final BigDecimal limitation;

    public CommercialServiceProperties(BigDecimal cost, BigDecimal limitation) {
        this.cost = cost;
        this.limitation = limitation;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public BigDecimal getLimitation() {
        return limitation;
    }

}
