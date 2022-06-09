package ir.sadeqcloud.BontechEvaluationProject.service.report;

import ir.sadeqcloud.BontechEvaluationProject.model.report.CommercialServiceUsage;
import ir.sadeqcloud.BontechEvaluationProject.repository.report.CommercialServiceUsageRepository;
import ir.sadeqcloud.BontechEvaluationProject.service.dto.CommercialServiceResultContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommercialServiceReport implements CommercialServiceReportContract{
    private final CommercialServiceUsageRepository commercialServiceUsageRepository;
    @Autowired
    public CommercialServiceReport(CommercialServiceUsageRepository commercialServiceUsageRepository) {
        this.commercialServiceUsageRepository = commercialServiceUsageRepository;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void saveReport(CommercialServiceResultContract resultContract) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CommercialServiceUsage commercialServiceUsage = new CommercialServiceUsage(resultContract.getCommercialServiceName(), resultContract.getCommercialServicePerformTime(), resultContract.getIsSuccessful(), authentication.getName());
        commercialServiceUsageRepository.save(commercialServiceUsage);
    }

    @Override
    public List<CommercialServiceUsage> getReports(Pageable pageable) {
        return null;
    }

    @Override
    public List<CommercialServiceUsage> getReportsByAdmin(Pageable pageable) {
        return null;
    }
}
