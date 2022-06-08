package ir.sadeqcloud.BontechEvaluationProject.utils;

import ir.sadeqcloud.BontechEvaluationProject.service.dto.OperationResult;

public class OperationResultContextHolder {
    private static final ThreadLocal<OperationResult> operationResultRollBackContextHolder = new ThreadLocal<>();

    public static void setOperationResult(OperationResult operationResult){
        OperationResult PreviousOperationResult = operationResultRollBackContextHolder.get();
        if (PreviousOperationResult==null)
            operationResultRollBackContextHolder.set(operationResult);
    }
    public static OperationResult getOperationResult(){
        return operationResultRollBackContextHolder.get();
    }
    public static void clearContext(){
        operationResultRollBackContextHolder.remove();
    }
}
