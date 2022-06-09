package ir.sadeqcloud.BontechEvaluationProject.service.transactionCallback;

import ir.sadeqcloud.BontechEvaluationProject.service.dto.OperationResult;
import ir.sadeqcloud.BontechEvaluationProject.utils.OperationResultContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class AdminTransactionCallback {

    /**
     * transaction synchronization
     * in other words,it is a way of registering callback methods to be invoked when the transaction is being completed
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
