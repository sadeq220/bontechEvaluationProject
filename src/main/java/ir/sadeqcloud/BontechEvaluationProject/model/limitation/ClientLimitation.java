package ir.sadeqcloud.BontechEvaluationProject.model.limitation;

import javax.persistence.*;

@Entity
@Table(name="client_limitation")
public class ClientLimitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientLimitationId;
    private String commercialServiceName;
    private String username;
    private Long serviceUse;
    @Version//optimistic lock to prevent lost updates
    private long version=0;

    public ClientLimitation(){
        //empty constructor to comply with POJO
    }
    public ClientLimitation(String commercialServiceName,String username){
        this.commercialServiceName=commercialServiceName;
        this.username=username;
        this.serviceUse= 1l;
    }

    public Long getClientLimitationId() {
        return clientLimitationId;
    }

    public void setClientLimitationId(Long clientLimitationId) {
        this.clientLimitationId = clientLimitationId;
    }

    public String getCommercialServiceName() {
        return commercialServiceName;
    }

    public void setCommercialServiceName(String commercialServiceName) {
        this.commercialServiceName = commercialServiceName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getServiceUse() {
        return serviceUse;
    }

    public void incrementServiceUse() {
        ++this.serviceUse;
    }
}
