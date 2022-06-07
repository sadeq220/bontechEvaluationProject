package ir.sadeqcloud.BontechEvaluationProject.service;

import ir.sadeqcloud.BontechEvaluationProject.controller.dto.SimpleUserDto;
import ir.sadeqcloud.BontechEvaluationProject.model.userModel.Simple;
import ir.sadeqcloud.BontechEvaluationProject.repository.endpointRepository.EndpointAvailabilityRepository;
import ir.sadeqcloud.BontechEvaluationProject.repository.userRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@PreAuthorize("hasRole('ADMIN')")
public class AdminServiceLayer implements AdminServiceContract{
    private final UserRepository userRepository;
    private final EndpointAvailabilityRepository endpointAvailabilityRepository;

    @Autowired
    public AdminServiceLayer(UserRepository userRepository,EndpointAvailabilityRepository endpointAvailabilityRepository){
        this.endpointAvailabilityRepository=endpointAvailabilityRepository;
        this.userRepository=userRepository;
    }

    @Override
    public boolean createUser(SimpleUserDto simpleUserDto) {
        Simple actualEntity = simpleUserDto.getActualEntity();
        userRepository.save(actualEntity);
        return true;
    }
}