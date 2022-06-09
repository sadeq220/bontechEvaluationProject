package ir.sadeqcloud.BontechEvaluationProject.service.adminService;

import ir.sadeqcloud.BontechEvaluationProject.controller.dto.CommercialServiceDto;
import ir.sadeqcloud.BontechEvaluationProject.controller.dto.PrivilegeDto;
import ir.sadeqcloud.BontechEvaluationProject.controller.dto.ServiceAvailabilityDto;
import ir.sadeqcloud.BontechEvaluationProject.controller.dto.SimpleUserDto;
import ir.sadeqcloud.BontechEvaluationProject.custException.CommercialServiceNotReachableException;
import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialService;
import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialServiceAvailability;
import ir.sadeqcloud.BontechEvaluationProject.model.userModel.Admin;
import ir.sadeqcloud.BontechEvaluationProject.model.userModel.Simple;
import ir.sadeqcloud.BontechEvaluationProject.model.userModel.User;
import ir.sadeqcloud.BontechEvaluationProject.repository.commercialServiceRepository.CommercialServiceAvailabilityRepository;
import ir.sadeqcloud.BontechEvaluationProject.repository.commercialServiceRepository.CommercialServiceRepository;
import ir.sadeqcloud.BontechEvaluationProject.repository.userRepository.UserRepository;
import ir.sadeqcloud.BontechEvaluationProject.service.dto.OperationResult;
import ir.sadeqcloud.BontechEvaluationProject.service.dto.ServiceOperationResult;
import ir.sadeqcloud.BontechEvaluationProject.utils.OperationResultContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@PreAuthorize("hasRole('ADMIN')")
public class AdminServiceLayer implements AdminServiceContract {
    private final UserRepository userRepository;
    private final CommercialServiceAvailabilityRepository commercialServiceAvailabilityRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final CommercialServiceRepository commercialServiceRepository;

    @Autowired
    public AdminServiceLayer(ApplicationEventPublisher applicationEventPublisher
                            , UserRepository userRepository
                            , CommercialServiceAvailabilityRepository commercialServiceAvailabilityRepository
                            , CommercialServiceRepository commercialServiceRepository){
        this.commercialServiceAvailabilityRepository = commercialServiceAvailabilityRepository;
        this.userRepository=userRepository;
        this.applicationEventPublisher=applicationEventPublisher;
        this.commercialServiceRepository=commercialServiceRepository;
    }

    @Override
    @Transactional
    public OperationResult createUser(SimpleUserDto simpleUserDto) {
        OperationResult operationResult = createTransactionalEventAndPublishIt("simpleUserCreation", "user created!");//TODO write by ResourceBundle
        Simple actualEntity = simpleUserDto.getActualEntity();
        userRepository.save(actualEntity);
        return operationResult;
    }

    @Transactional
    @Override
    public OperationResult createServiceAvailability(ServiceAvailabilityDto serviceAvailabilityDto) {
        OperationResult operationResult = createTransactionalEventAndPublishIt("service availability creation", "service availability created!");
        String commercialServiceName = serviceAvailabilityDto.getCommercialServiceName();
        Optional<CommercialService> commercialServiceOptional = commercialServiceRepository.findById(commercialServiceName);
        commercialServiceOptional.orElseThrow(()->new CommercialServiceNotReachableException(commercialServiceName));
        CommercialService commercialService = commercialServiceOptional.get();
        CommercialServiceAvailability commercialServiceAvailability = new CommercialServiceAvailability(commercialService, serviceAvailabilityDto.getDate(), serviceAvailabilityDto.getStartOfAvailability(), serviceAvailabilityDto.getEndOfAvailability());
        commercialServiceAvailabilityRepository.save(commercialServiceAvailability);
        return operationResult;
    }

    @Transactional
    @Override
    public OperationResult createCommercialService(CommercialServiceDto commercialServiceDto) {
        OperationResult operationResult = createTransactionalEventAndPublishIt("commercial service creation", "commercial service created!");
        CommercialService commercialService = commercialServiceDto.createEntity();
        commercialServiceRepository.save(commercialService);
        return operationResult;
    }
    @Transactional
    @Override
    public OperationResult addPrivilege(PrivilegeDto privilegeDto) {
        OperationResult operationResult = createTransactionalEventAndPublishIt("add privilege to simpleUser", "privilege added!");
        Optional<User> optionalUser = userRepository.findById(privilegeDto.getUsername());
        optionalUser.orElseThrow(()->new RuntimeException(privilegeDto.getUsername()+" not exists!"));//it will be translated
        User user = optionalUser.get();
        if (!(user instanceof Simple))
            throw new RuntimeException(privilegeDto.getUsername()+" is not a simple user!");// it will be translated
        Optional<CommercialService> optionalCommercialService = commercialServiceRepository.findById(privilegeDto.getCommercialServiceName());
        optionalCommercialService.orElseThrow(()->new CommercialServiceNotReachableException(privilegeDto.getCommercialServiceName()));

        ((Simple) user).addAllowableService(optionalCommercialService.get() );
        userRepository.flush();//force update user in database transaction
        return operationResult;
    }

    private OperationResult createTransactionalEventAndPublishIt(String operationName,String message){
        ServiceOperationResult serviceOperationResult = new ServiceOperationResult(true, message, operationName);
        publishTransactionalEvent(serviceOperationResult);
        OperationResultContextHolder.setOperationResult(serviceOperationResult);
        return serviceOperationResult;
    }

    private void publishTransactionalEvent(OperationResult operationResult){
        applicationEventPublisher.publishEvent(operationResult);
    }
}
