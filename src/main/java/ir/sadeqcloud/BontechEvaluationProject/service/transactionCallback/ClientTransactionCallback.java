package ir.sadeqcloud.BontechEvaluationProject.service.transactionCallback;

import ir.sadeqcloud.BontechEvaluationProject.service.dto.CommercialServiceResultContract;
import ir.sadeqcloud.BontechEvaluationProject.service.limitation.ClientLimitationServiceContract;
import ir.sadeqcloud.BontechEvaluationProject.service.report.CommercialServiceReportContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class ClientTransactionCallback {
    private final CommercialServiceReportContract commercialServiceReportContract;
    private final ClientLimitationServiceContract clientLimitationServiceContract;
    @Autowired
    public ClientTransactionCallback(CommercialServiceReportContract commercialServiceReportContract
                                    , ClientLimitationServiceContract clientLimitationServiceContract) {
        this.commercialServiceReportContract = commercialServiceReportContract;
        this.clientLimitationServiceContract = clientLimitationServiceContract;
    }

    /**
     *  Ideally service usage would be an event
     * and it here we should publish it To kafka
     * then these reports might be gathered and analyzed in another microservices
     */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void simpleUserCommercialServiceUse(CommercialServiceResultContract commercialServiceResultContract){
        commercialServiceReportContract.saveReport(commercialServiceResultContract);
        /**
         * increment serviceUsage only if Transaction Was successful
         */
        clientLimitationServiceContract.incrementServiceUse(commercialServiceResultContract.getCommercialServiceName());
    }
    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void simpleUserCommercialServiceUsedFailed(CommercialServiceResultContract commercialServiceResultContract){
        commercialServiceResultContract.setIsSuccessful(false);
        commercialServiceReportContract.saveReport(commercialServiceResultContract);
    }

    /**
     * delete all rows on start of each day
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void deleteAllClientLimitation(){
    clientLimitationServiceContract.deleteAllRows();
    }
}
