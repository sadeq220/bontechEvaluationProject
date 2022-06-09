package ir.sadeqcloud.BontechEvaluationProject.service.transactionCallback;

import ir.sadeqcloud.BontechEvaluationProject.service.dto.CommercialServiceResultContract;
import ir.sadeqcloud.BontechEvaluationProject.service.report.CommercialServiceReportContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class ClientTransactionCallback {
    private final CommercialServiceReportContract commercialServiceReportContract;
    @Autowired
    public ClientTransactionCallback(CommercialServiceReportContract commercialServiceReportContract) {
        this.commercialServiceReportContract = commercialServiceReportContract;
    }

    /**
     *  Ideally service usage would be an event
     * and it here we should publish it To kafka
     * then these reports might be gathered and analyzed in another microservices
     */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void simpleUserCommercialServiceUse(CommercialServiceResultContract commercialServiceResultContract){
        commercialServiceReportContract.saveReport(commercialServiceResultContract);
    }
    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void simpleUserCommercialServiceUsedFailed(CommercialServiceResultContract commercialServiceResultContract){
        commercialServiceResultContract.setIsSuccessful(false);
        commercialServiceReportContract.saveReport(commercialServiceResultContract);
    }
}
