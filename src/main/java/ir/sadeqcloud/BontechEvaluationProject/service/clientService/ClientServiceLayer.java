package ir.sadeqcloud.BontechEvaluationProject.service.clientService;

import ir.sadeqcloud.BontechEvaluationProject.externalizedConfiguration.CommercialServiceConfig;
import ir.sadeqcloud.BontechEvaluationProject.repository.userRepository.UserRepository;
import ir.sadeqcloud.BontechEvaluationProject.service.dto.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceLayer implements ClientServiceContract{

   private final UserRepository userRepository;
   private final ApplicationEventPublisher applicationEventPublisher;
   private final CommercialServiceConfig commercialServiceConfig;
    @Autowired
    public ClientServiceLayer(UserRepository userRepository, ApplicationEventPublisher applicationEventPublisher, CommercialServiceConfig commercialServiceConfig) {
        this.userRepository = userRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.commercialServiceConfig = commercialServiceConfig;
    }

    @Override
    public OperationResult send_SMS(String SMS) {
        return null;
    }
}
