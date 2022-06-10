package ir.sadeqcloud.BontechEvaluationProject.model.userModel;

import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Simple extends User{

    @ManyToMany
    @JoinTable(name = "access_table",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name="commercial_service_name"))
    private Set<CommercialService> allowableCommercialServices =new LinkedHashSet<>();

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

    public Set<CommercialService> getAllowableCommercialServices() {
        return Collections.unmodifiableSet(allowableCommercialServices);
    }

    public boolean addAllowableService(CommercialService commercialService){
        return allowableCommercialServices.add(commercialService);
    }
    public boolean removeAllowableService(CommercialService commercialService){
        return allowableCommercialServices.remove(commercialService);
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public BigDecimal subtractCostFromCredit(BigDecimal cost){
        this.credit=this.credit.subtract(cost);
        return this.credit;
    }
    public BigDecimal addCredit(BigDecimal credit){
        this.credit=this.credit.add(credit);
        return this.credit;
    }

    /**
     * use hibernate callback method
     * this method will be executed after entity load to persistence context
     */
    @PostLoad
    private void loadGrantedAuthorities(){
    this.grantedAuthorities=  allowableCommercialServices.stream()
                                .map(allowableCommercialService -> new SimpleGrantedAuthority(allowableCommercialService.getCommercialServiceName()))
            .collect(Collectors.toSet());
    }

    public Set<GrantedAuthority> grantedAuthorities(){
        return Collections.unmodifiableSet(this.grantedAuthorities);
    }
}
