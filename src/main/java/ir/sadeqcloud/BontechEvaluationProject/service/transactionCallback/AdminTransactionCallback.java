package ir.sadeqcloud.BontechEvaluationProject.service.transactionCallback;

import ir.sadeqcloud.BontechEvaluationProject.service.dto.OperationResult;
import ir.sadeqcloud.BontechEvaluationProject.utils.OperationResultContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Component
public class AdminTransactionCallback {

    /**
     * transaction synchronization ( see TransactionSynchronizationManager class)
     * in other words,it is a way of registering callback methods to be invoked when the transaction is being completed
     *
     * Transaction synchronization allows the application server to be notified before and after the transaction completes.
     * For each transaction started, the application server may optionally register a Synchronization callback object to be invoked by the transaction manager either before or after completion.
     */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void transactionUnsuccessful(OperationResult operationResult){
        operationResult.setIsSuccessful(false);
        if (operationResult.getMessage()==null) {
            operationResult.setMessage("someThing went wrong!");
        }
        OperationResultContextHolder.clearContext();
    }
}
