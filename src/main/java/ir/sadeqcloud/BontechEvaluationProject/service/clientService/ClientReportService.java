package ir.sadeqcloud.BontechEvaluationProject.service.clientService;

import ir.sadeqcloud.BontechEvaluationProject.controller.dto.CommercialServiceDto;
import ir.sadeqcloud.BontechEvaluationProject.controller.dto.PageDto;
import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialService;
import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialServiceAvailability;
import ir.sadeqcloud.BontechEvaluationProject.model.report.CommercialServiceUsage;
import ir.sadeqcloud.BontechEvaluationProject.model.userModel.Simple;
import ir.sadeqcloud.BontechEvaluationProject.model.userModel.User;
import ir.sadeqcloud.BontechEvaluationProject.repository.commercialServiceRepository.CommercialServiceAvailabilityRepository;
import ir.sadeqcloud.BontechEvaluationProject.repository.report.CommercialServiceUsageRepository;
import ir.sadeqcloud.BontechEvaluationProject.repository.userRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@PreAuthorize("!hasAuthority('ADMIN')")
public class ClientReportService implements ClientReportServiceContract{
    private final CommercialServiceUsageRepository commercialServiceUsageRepository;
    private final CommercialServiceAvailabilityRepository commercialServiceAvailabilityRepository;
    private final UserRepository userRepository;
    @Autowired
    public ClientReportService(CommercialServiceUsageRepository commercialServiceUsageRepository
                             , CommercialServiceAvailabilityRepository commercialServiceAvailabilityRepository
                             , UserRepository userRepository) {
        this.commercialServiceUsageRepository = commercialServiceUsageRepository;
        this.commercialServiceAvailabilityRepository = commercialServiceAvailabilityRepository;
        this.userRepository=userRepository;
    }
    @Transactional(readOnly = true)
    @Override
    public Page<CommercialServiceUsage> reportServiceUsage(boolean success, PageDto pageDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Pageable pageable = PageRequest.of(pageDto.getPage(), pageDto.getSize());
        return commercialServiceUsageRepository.getAllByUsernameAndWasSuccessful(authentication.getName(), success, pageable);
    }
    @Transactional(readOnly = true)
    @Override
    public Page<CommercialServiceAvailability> getAvailableServicesAtTheMoment(PageDto pageDto) {
        Pageable pageable = PageRequest.of(pageDto.getPage(), pageDto.getSize());
        return commercialServiceAvailabilityRepository.getAvailableServicesAtTheMoment(LocalTime.now(), LocalDate.now(), pageable);
    }
    @Transactional(readOnly = true)
    @Override
    public Set<CommercialServiceDto> getAllowableServices() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Simple user = findUser(authentication.getName());
        Set<CommercialService> allowableCommercialServices = user.getAllowableCommercialServices();
        Set<CommercialServiceDto> commercialServiceDtoSet = allowableCommercialServices.stream().map(commercialService -> CommercialServiceDto.factory(commercialService)).collect(Collectors.toSet());
        return commercialServiceDtoSet;
    }
    private Simple findUser(String username){
        Optional<User> optionalUser = userRepository.findById(username);
        optionalUser.orElseThrow(()->new UsernameNotFoundException(username+" you deleted by admin"));
        User user = optionalUser.get();
        if (!(user instanceof Simple))
            throw new AccessDeniedException("you are not simple user");
        return (Simple) user;
    }
}
