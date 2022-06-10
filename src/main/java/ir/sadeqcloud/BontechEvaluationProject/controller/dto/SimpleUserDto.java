package ir.sadeqcloud.BontechEvaluationProject.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.sadeqcloud.BontechEvaluationProject.model.userModel.Simple;
import ir.sadeqcloud.BontechEvaluationProject.utils.IoCContainerUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

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
        PasswordEncoder passwordEncoder = IoCContainerUtil.getBean(PasswordEncoder.class);
        this.password = passwordEncoder.encode(password);
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
