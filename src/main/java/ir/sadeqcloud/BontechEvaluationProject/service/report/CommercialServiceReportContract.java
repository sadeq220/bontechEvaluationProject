package ir.sadeqcloud.BontechEvaluationProject.service.report;

import ir.sadeqcloud.BontechEvaluationProject.model.report.CommercialServiceUsage;
import ir.sadeqcloud.BontechEvaluationProject.service.dto.CommercialServiceResultContract;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommercialServiceReportContract {
    void saveReport(CommercialServiceResultContract resultContract);
    List<CommercialServiceUsage> getReports(Pageable pageable);
    List<CommercialServiceUsage> getReportsByAdmin(Pageable pageable);
}
