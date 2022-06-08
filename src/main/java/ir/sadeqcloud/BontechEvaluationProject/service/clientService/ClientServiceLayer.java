package ir.sadeqcloud.BontechEvaluationProject.service.clientService;

import ir.sadeqcloud.BontechEvaluationProject.externalizedConfiguration.CommercialServiceConfig;
import ir.sadeqcloud.BontechEvaluationProject.externalizedConfiguration.dto.CommercialServiceProperties;
import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialServiceName;
import ir.sadeqcloud.BontechEvaluationProject.model.userModel.Simple;
import ir.sadeqcloud.BontechEvaluationProject.model.userModel.User;
import ir.sadeqcloud.BontechEvaluationProject.repository.userRepository.UserRepository;
import ir.sadeqcloud.BontechEvaluationProject.service.dto.CommercialServiceRequiredInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@PreAuthorize("!hasRole('ADMIN')")
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
    @PreAuthorize("hasAuthority(commercialServiceName.correlatePrivilege)")
    @Transactional(propagation = Propagation.MANDATORY)// enforce existence of AbstractionLayer
    @Override
    public void useCommercialService(CommercialServiceName commercialServiceName, CommercialServiceRequiredInput commercialServicePerformInput) {
        CommercialServiceProperties commercialServiceProperties = commercialServiceConfig.getCommercialServiceMap().get(commercialServiceName);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> simpleUserOptional = userRepository.findById(authentication.getName());
        Simple simpleUser = ((Simple) simpleUserOptional.get());
        // do commercial service e.g. send_SMS
        simpleUser.subtractCostFromCredit(commercialServiceProperties.getCost());
    }
}
