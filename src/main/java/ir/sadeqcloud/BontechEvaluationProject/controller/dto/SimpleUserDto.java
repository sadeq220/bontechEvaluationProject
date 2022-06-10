package ir.sadeqcloud.BontechEvaluationProject.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.sadeqcloud.BontechEvaluationProject.model.userModel.Simple;

import java.math.BigDecimal;

public class SimpleUserDto {
    private String username;
    private String password;
    private BigDecimal initialCredit;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getInitialCredit() {
        return initialCredit;
    }

    public void setInitialCredit(BigDecimal initialCredit) {
        this.initialCredit = initialCredit;
    }
    @JsonIgnore
    public Simple getActualEntity(){
        return new Simple(username,password,initialCredit);
    }
}
