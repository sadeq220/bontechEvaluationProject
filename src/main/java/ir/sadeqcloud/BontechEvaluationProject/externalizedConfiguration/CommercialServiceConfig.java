package ir.sadeqcloud.BontechEvaluationProject.externalizedConfiguration;

import com.google.common.collect.ImmutableMap;
import ir.sadeqcloud.BontechEvaluationProject.externalizedConfiguration.dto.CommercialServiceProperties;
import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialServiceName;
import org.hibernate.mapping.Bag;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.Map;


@ConfigurationProperties(prefix = "commercial.service")
@ConstructorBinding// to create immutable object
public class CommercialServiceConfig {
    private final CommercialServiceProperties send_SMS;
    private final CommercialServiceProperties send_EMAIL;
    private final Map<CommercialServiceName,CommercialServiceProperties> commercialServiceMap;

    public CommercialServiceConfig(CommercialServiceProperties send_SMS, CommercialServiceProperties send_EMAIL) {
        this.send_SMS = send_SMS;
        this.send_EMAIL = send_EMAIL;
       commercialServiceMap= ImmutableMap.of(CommercialServiceName.SEND_SMS,send_SMS,CommercialServiceName.SEND_EMAIL,send_EMAIL);
    }
/*
    public CommercialServiceProperties getSend_SMS() {
        return send_SMS;
    }

    public CommercialServiceProperties getSend_EMAIL() {
        return send_EMAIL;
    }
*/
    public Map<CommercialServiceName, CommercialServiceProperties> getCommercialServiceMap() {
        return commercialServiceMap;
    }
}
