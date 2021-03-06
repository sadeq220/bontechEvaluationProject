package ir.sadeqcloud.BontechEvaluationProject.service.clientAbstractionLayer;

import ir.sadeqcloud.BontechEvaluationProject.custException.ClientLimitPassedException;
import ir.sadeqcloud.BontechEvaluationProject.custException.CommercialServiceNotReachableException;
import ir.sadeqcloud.BontechEvaluationProject.custException.ServiceNotAvailableAtTheMoment;
import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialService;
import ir.sadeqcloud.BontechEvaluationProject.repository.commercialServiceRepository.CommercialServiceRepository;
import ir.sadeqcloud.BontechEvaluationProject.service.clientService.ClientServiceContract;
import ir.sadeqcloud.BontechEvaluationProject.service.dto.*;
import ir.sadeqcloud.BontechEvaluationProject.service.limitation.ClientLimitationServiceContract;
import ir.sadeqcloud.BontechEvaluationProject.service.serviceAvailability.CommercialServiceAvailabilityContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ClientAbstractionLayerOverService implements ClientAbstractionLayerOverServiceLayerContract{
    private final ClientServiceContract clientServiceContract;
    private final CommercialServiceRepository commercialServiceRepository;
    private final CommercialServiceAvailabilityContract commercialServiceAvailabilityContract;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ClientLimitationServiceContract clientLimitationServiceContract;
    @Autowired
    public ClientAbstractionLayerOverService(ClientServiceContract clientServiceContract
                                             ,CommercialServiceRepository commercialServiceRepository
                                             ,CommercialServiceAvailabilityContract commercialServiceAvailabilityContract
                                             ,ApplicationEventPublisher applicationEventPublisher
                                             ,ClientLimitationServiceContract clientLimitationServiceContract) {
        this.clientServiceContract = clientServiceContract;
        this.commercialServiceRepository = commercialServiceRepository;
        this.commercialServiceAvailabilityContract = commercialServiceAvailabilityContract;
        this.applicationEventPublisher=applicationEventPublisher;
        this.clientLimitationServiceContract=clientLimitationServiceContract;
    }
    @Transactional
    @Override
    public CommercialServiceResultContract useCommercialService(String commercialServiceName, CommercialServiceRequiredInput commercialServiceRequiredInput) {

        CommercialServiceResultContract commercialServiceResultContract = createTransactionalEventAndPublishIt(commercialServiceName);
        /**
         * check if commercial service has been enabled by admin at the moment
         */
        boolean isServiceAvailable = commercialServiceAvailabilityContract.isServiceAvailable(commercialServiceName, LocalDateTime.now());
        if (!isServiceAvailable) {
            throw new ServiceNotAvailableAtTheMoment(commercialServiceName);
        }

        Optional<CommercialService> commercialServiceOptional = commercialServiceRepository.findById(commercialServiceName);
        commercialServiceOptional.orElseThrow(()->new CommercialServiceNotReachableException(commercialServiceName));

        CommercialService commercialService = commercialServiceOptional.get();
        /**
         * check if limit of commercial service not passed
         */
        checkCommercialServiceLimitationUsage(commercialService);
        /**
         * actual use of commercial service here
         * 1) check if simpleUSer has authority to use commercialService
         * 2) use commercial service (e.g. send SMS)
         * 3) subtract the cost of commercialService from simpleUSer credit
         */
        clientServiceContract.useCommercialService(commercialService,commercialServiceRequiredInput);

        return commercialServiceResultContract;
    }
    private CommercialServiceResultContract createTransactionalEventAndPublishIt(String commercialServiceName){
        CommercialServiceResult commercialServiceResult = new CommercialServiceResult(true, commercialServiceName);
        publishTransactionalEvent(commercialServiceResult);
        return commercialServiceResult;
    }

    /**
     * to follow the separate the concern's principle
     * we should log a simpleUser commercialService usage after transaction commit or rollback
     */
    private void publishTransactionalEvent(CommercialServiceResultContract commercialServiceResultContract){
        applicationEventPublisher.publishEvent(commercialServiceResultContract);
    }

    private void checkCommercialServiceLimitationUsage(CommercialService commercialService){
        /**
         * check if limit of commercial service not passed
         */
        boolean clientLimitationPassed = clientLimitationServiceContract.isClientLimitationPassed(commercialService);
        if (clientLimitationPassed)
            throw new ClientLimitPassedException(commercialService.getCommercialServiceName(),commercialService.getUsageLimitation());
    }

}
