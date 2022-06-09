package ir.sadeqcloud.BontechEvaluationProject.model.report;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "service_usage")
@Table(name = "service_usage")
public class CommercialServiceUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceUsageId;
    private String commercialServiceName;
    private LocalDateTime serviceUseTime;
    private boolean wasSuccessful;
    private String username;

    public CommercialServiceUsage(){
        //empty constructor to comply with POJO
    }
    public CommercialServiceUsage(String commercialServiceName,LocalDateTime serviceUseTime,boolean wasSuccessful,String username){
        this.commercialServiceName=commercialServiceName;
        this.serviceUseTime=serviceUseTime;
        this.wasSuccessful=wasSuccessful;
        this.username=username;
    }

    public String getCommercialServiceName() {
        return commercialServiceName;
    }

    public void setCommercialServiceName(String commercialServiceName) {
        this.commercialServiceName = commercialServiceName;
    }

    public LocalDateTime getServiceUseTime() {
        return serviceUseTime;
    }

    public void setServiceUseTime(LocalDateTime serviceUseTime) {
        this.serviceUseTime = serviceUseTime;
    }

    public boolean isWasSuccessful() {
        return wasSuccessful;
    }

    public void setWasSuccessful(boolean wasSuccessful) {
        this.wasSuccessful = wasSuccessful;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
