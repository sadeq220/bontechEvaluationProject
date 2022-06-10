package ir.sadeqcloud.BontechEvaluationProject.controller.dto;

import java.math.BigDecimal;

public class CreditDto {
    private String username;
    private BigDecimal credit;

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
}
