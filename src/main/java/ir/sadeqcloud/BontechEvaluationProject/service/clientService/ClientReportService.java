package ir.sadeqcloud.BontechEvaluationProject.service.clientService;

import ir.sadeqcloud.BontechEvaluationProject.controller.dto.PageDto;
import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialServiceAvailability;
import ir.sadeqcloud.BontechEvaluationProject.model.report.CommercialServiceUsage;
import ir.sadeqcloud.BontechEvaluationProject.repository.commercialServiceRepository.CommercialServiceAvailabilityRepository;
import ir.sadeqcloud.BontechEvaluationProject.repository.report.CommercialServiceUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@PreAuthorize("!hasAuthority('ADMIN')")
public class ClientReportService implements ClientReportServiceContract{
    private final CommercialServiceUsageRepository commercialServiceUsageRepository;
    private final CommercialServiceAvailabilityRepository commercialServiceAvailabilityRepository;
    @Autowired
    public ClientReportService(CommercialServiceUsageRepository commercialServiceUsageRepository, CommercialServiceAvailabilityRepository commercialServiceAvailabilityRepository) {
        this.commercialServiceUsageRepository = commercialServiceUsageRepository;
        this.commercialServiceAvailabilityRepository = commercialServiceAvailabilityRepository;
    }
    @Transactional(readOnly = true)
    @Override
    public Page<CommercialServiceUsage> reportServiceUsage(boolean success, PageDto pageDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Pageable pageable = PageRequest.of(pageDto.getNo(), pageDto.getSize());
        return commercialServiceUsageRepository.getAllByUsernameAndWasSuccessful(authentication.getName(), success, pageable);
    }
    @Transactional(readOnly = true)
    @Override
    public Page<CommercialServiceAvailability> getAvailableServicesAtTheMoment(PageDto pageDto) {
        Pageable pageable = PageRequest.of(pageDto.getNo(), pageDto.getSize());
        return commercialServiceAvailabilityRepository.getAvailableServicesAtTheMoment(LocalTime.now(), LocalDate.now(), pageable);
    }
}
