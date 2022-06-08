package ir.sadeqcloud.BontechEvaluationProject.service;

import ir.sadeqcloud.BontechEvaluationProject.controller.dto.SimpleUserDto;
import ir.sadeqcloud.BontechEvaluationProject.custException.AdminTransactionRolledBackException;
import ir.sadeqcloud.BontechEvaluationProject.model.userModel.Simple;
import ir.sadeqcloud.BontechEvaluationProject.repository.endpointRepository.EndpointAvailabilityRepository;
import ir.sadeqcloud.BontechEvaluationProject.repository.userRepository.UserRepository;
import ir.sadeqcloud.BontechEvaluationProject.service.dto.OperationResult;
import ir.sadeqcloud.BontechEvaluationProject.service.dto.ServiceOperationResult;
import ir.sadeqcloud.BontechEvaluationProject.utils.OperationResultContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@PreAuthorize("hasRole('ADMIN')")
public class AdminServiceLayer implements AdminServiceContract{
    private final UserRepository userRepository;
    private final EndpointAvailabilityRepository endpointAvailabilityRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public AdminServiceLayer(ApplicationEventPublisher applicationEventPublisher,UserRepository userRepository,EndpointAvailabilityRepository endpointAvailabilityRepository){
        this.endpointAvailabilityRepository=endpointAvailabilityRepository;
        this.userRepository=userRepository;
        this.applicationEventPublisher=applicationEventPublisher;
    }

    @Override
    @Transactional
    public OperationResult createUser(SimpleUserDto simpleUserDto) {
        OperationResult operationResult = createTransactionalEventAndPublishIt("simpleUser", "user created!");
        Simple actualEntity = simpleUserDto.getActualEntity();
        userRepository.save(actualEntity);
        return operationResult;
    }

    private OperationResult createTransactionalEventAndPublishIt(String modelName,String message){
        ServiceOperationResult serviceOperationResult = new ServiceOperationResult(true, message, modelName);
        publishTransactionalEvent(serviceOperationResult);
        OperationResultContextHolder.setOperationResult(serviceOperationResult);
        return serviceOperationResult;
    }

    private void publishTransactionalEvent(OperationResult operationResult){
        applicationEventPublisher.publishEvent(operationResult);
    }
}
