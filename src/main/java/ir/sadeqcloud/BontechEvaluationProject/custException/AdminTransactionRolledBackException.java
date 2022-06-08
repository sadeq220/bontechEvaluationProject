package ir.sadeqcloud.BontechEvaluationProject.custException;

import ir.sadeqcloud.BontechEvaluationProject.service.dto.OperationResult;

public class AdminTransactionRolledBackException extends RuntimeException{
    private OperationResult operationResult;

    public AdminTransactionRolledBackException(OperationResult operationResult) {
        this.operationResult = operationResult;
    }

    public OperationResult getOperationResult() {
        return operationResult;
    }

    public void setOperationResult(OperationResult operationResult) {
        this.operationResult = operationResult;
    }
}
