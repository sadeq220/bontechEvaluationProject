package ir.sadeqcloud.BontechEvaluationProject.controller.dto;

import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialService;

import java.math.BigDecimal;

public class CommercialServiceDto {
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
    public CommercialService createEntity(){
        return new CommercialService(commercialServiceName, cost, usageLimitation);
    }
    public static CommercialServiceDto factory(CommercialService commercialService){
        CommercialServiceDto commercialServiceDto = new CommercialServiceDto();
        commercialServiceDto.commercialServiceName=commercialService.getCommercialServiceName();
        commercialServiceDto.cost=commercialService.getCost();
        commercialServiceDto.usageLimitation=commercialService.getUsageLimitation();
        return commercialServiceDto;
    }
}
