package ir.sadeqcloud.BontechEvaluationProject.service.limitation;

import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialService;
import ir.sadeqcloud.BontechEvaluationProject.model.limitation.ClientLimitation;
import ir.sadeqcloud.BontechEvaluationProject.repository.limitation.ClientLimitationRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientLimitationService implements ClientLimitationServiceContract{

    private final ClientLimitationRepository clientLimitationRepository;

    public ClientLimitationService(ClientLimitationRepository clientLimitationRepository) {
        this.clientLimitationRepository = clientLimitationRepository;
    }
    @Transactional(readOnly = true)
    @Override
    public boolean isClientLimitationPassed(CommercialService commercialService) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<ClientLimitation> optionalClientLimitation = clientLimitationRepository.findClientLimitationByUsernameAndCommercialServiceName(authentication.getName(), commercialService.getCommercialServiceName());
        if (!optionalClientLimitation.isPresent())
        return false;
        ClientLimitation clientLimitation = optionalClientLimitation.get();
        return clientLimitation.getServiceUse() >= commercialService.getUsageLimitation();
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void incrementServiceUse(String commercialServiceName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<ClientLimitation> optionalClientLimitation = clientLimitationRepository.findClientLimitationByUsernameAndCommercialServiceName(authentication.getName(), commercialServiceName);
        if (!optionalClientLimitation.isPresent()) {
            ClientLimitation clientLimitation = new ClientLimitation(commercialServiceName, authentication.getName());
            clientLimitationRepository.save(clientLimitation);
        }else {
            ClientLimitation clientLimitation = optionalClientLimitation.get();
            clientLimitation.incrementServiceUse();
            clientLimitationRepository.flush();
        }
    }
    @Transactional
    @Override
    public void deleteAllRows() {
    clientLimitationRepository.deleteAll();
    }
}
