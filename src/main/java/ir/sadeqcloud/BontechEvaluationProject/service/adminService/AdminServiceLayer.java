package ir.sadeqcloud.BontechEvaluationProject.service.adminService;

import ir.sadeqcloud.BontechEvaluationProject.controller.dto.*;
import ir.sadeqcloud.BontechEvaluationProject.custException.CommercialServiceNotReachableException;
import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialService;
import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialServiceAvailability;
import ir.sadeqcloud.BontechEvaluationProject.model.report.CommercialServiceUsage;
import ir.sadeqcloud.BontechEvaluationProject.model.userModel.Admin;
import ir.sadeqcloud.BontechEvaluationProject.model.userModel.Simple;
import ir.sadeqcloud.BontechEvaluationProject.model.userModel.User;
import ir.sadeqcloud.BontechEvaluationProject.repository.commercialServiceRepository.CommercialServiceAvailabilityRepository;
import ir.sadeqcloud.BontechEvaluationProject.repository.commercialServiceRepository.CommercialServiceRepository;
import ir.sadeqcloud.BontechEvaluationProject.repository.report.CommercialServiceUsageRepository;
import ir.sadeqcloud.BontechEvaluationProject.repository.userRepository.UserRepository;
import ir.sadeqcloud.BontechEvaluationProject.service.dto.OperationResult;
import ir.sadeqcloud.BontechEvaluationProject.service.dto.ServiceOperationResult;
import ir.sadeqcloud.BontechEvaluationProject.utils.OperationResultContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final CommercialServiceUsageRepository commercialServiceUsageRepository;

    @Autowired
    public AdminServiceLayer(ApplicationEventPublisher applicationEventPublisher
                            , UserRepository userRepository
                            , CommercialServiceAvailabilityRepository commercialServiceAvailabilityRepository
                            , CommercialServiceRepository commercialServiceRepository
                            , CommercialServiceUsageRepository commercialServiceUsageRepository){
        this.commercialServiceAvailabilityRepository = commercialServiceAvailabilityRepository;
        this.userRepository=userRepository;
        this.applicationEventPublisher=applicationEventPublisher;
        this.commercialServiceRepository=commercialServiceRepository;
        this.commercialServiceUsageRepository=commercialServiceUsageRepository;
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
        CommercialService commercialService=findCommercialService(serviceAvailabilityDto.getCommercialServiceName());
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
        User user=findUser(privilegeDto.getUsername());
        if (!(user instanceof Simple))
            throw new RuntimeException(privilegeDto.getUsername()+" is not a simple user!");// it will be translated
        CommercialService commercialService=findCommercialService(privilegeDto.getCommercialServiceName());

        ((Simple) user).addAllowableService(commercialService);
        userRepository.flush();//force update user in database transaction
        return operationResult;
    }

    @Override
    public OperationResult removePrivilege(PrivilegeDto privilegeDto) {
        OperationResult operationResult = createTransactionalEventAndPublishIt("remove privilege from simpleUser", "privilege removed!");
        User user=findUser(privilegeDto.getUsername());
        if (!(user instanceof Simple))
            throw new RuntimeException(privilegeDto.getUsername()+" is not a simple user!");// it will be translated
        CommercialService commercialService=findCommercialService(privilegeDto.getCommercialServiceName());
        ((Simple) user).removeAllowableService(commercialService);
        userRepository.flush();
        return operationResult;
    }
    @Transactional(readOnly = true)
    @Override
    public Page<CommercialServiceUsage> reportServiceUsage(boolean success, PageDto pageDto) {
        Pageable pageable = PageRequest.of(pageDto.getNo(), pageDto.getSize());
        return commercialServiceUsageRepository.getPageableResult(success,pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CommercialServiceUsage> reportServiceUsage(boolean success, PageDto pageDto, String username) {
        if (username==null)
            return this.reportServiceUsage(success,pageDto);
        Pageable pageable = PageRequest.of(pageDto.getNo(), pageDto.getSize());
        return commercialServiceUsageRepository.getAllByUsernameAndWasSuccessful(username, success,pageable);
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
    private User findUser(String username){
        Optional<User> optionalUser = userRepository.findById(username);
        optionalUser.orElseThrow(()->new RuntimeException(username+" not exists!"));//it will be translated
        return optionalUser.get();
    }
    private CommercialService findCommercialService(String commercialServiceName){
        Optional<CommercialService> optionalCommercialService = commercialServiceRepository.findById(commercialServiceName);
        optionalCommercialService.orElseThrow(()->new CommercialServiceNotReachableException(commercialServiceName));
        return optionalCommercialService.get();
    }
}
