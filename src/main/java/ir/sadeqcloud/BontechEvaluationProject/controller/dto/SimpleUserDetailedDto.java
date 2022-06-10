package ir.sadeqcloud.BontechEvaluationProject.controller.dto;

import java.math.BigDecimal;
import java.util.Set;

public class SimpleUserDetailedDto {
    private String username;
    private BigDecimal credit;
    private Set<String> allowedServices;

    public SimpleUserDetailedDto() {
        //default constructor to comply with POJO
    }

    public SimpleUserDetailedDto(String username, BigDecimal credit, Set<String> allowedServices) {
        this.username = username;
        this.credit = credit;
        this.allowedServices = allowedServices;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public Set<String> getAllowedServices() {
        return allowedServices;
    }

    public void setAllowedServices(Set<String> allowedServices) {
        this.allowedServices = allowedServices;
    }
}
