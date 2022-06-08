package ir.sadeqcloud.BontechEvaluationProject.model.userModel;

import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialServicePrivilege;
import ir.sadeqcloud.BontechEvaluationProject.utils.StringToPrivilegeConverter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Simple extends User{
    /**
     * lump of data
     * we will use it later to create list of authorities
     */
    @Convert(converter = StringToPrivilegeConverter.class)
    @Column(name = "privileges",columnDefinition = "VARCHAR(100)")
    private Set<CommercialServicePrivilege> privileges;

    private BigDecimal credit;

    @Transient
    private Set<GrantedAuthority> grantedAuthorities;

    public Simple(){
        //empty constructor to comply with POJO
    }
    public Simple(String username,String password,BigDecimal initialCredit){
        this.setUsername(username);
        this.setPassword(password);
        this.credit=initialCredit;
    }

    public Set<CommercialServicePrivilege> getPrivileges() {
        return privileges;
    }

    public boolean addPrivilege(CommercialServicePrivilege endpointPrivilege){
        return privileges.add(endpointPrivilege);
    }
    public boolean removePrivilege(CommercialServicePrivilege endpointPrivilege){
        return privileges.remove(endpointPrivilege);
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public BigDecimal subtractCostFromCredit(BigDecimal cost){
        this.credit=this.credit.subtract(cost);
        return this.credit;
    }

    /**
     * use hibernate callback method
     * this method will be executed after entity load to persistence context
     */
    @PostLoad
    private void loadGrantedAuthorities(){
    this.grantedAuthorities=  privileges.stream()
                                .map(endpointPrivilege -> new SimpleGrantedAuthority(endpointPrivilege.name()))
            .collect(Collectors.toSet());
    }

    public Set<GrantedAuthority> grantedAuthorities(){
        return Collections.unmodifiableSet(this.grantedAuthorities);
    }
}
