package ir.sadeqcloud.BontechEvaluationProject.aspect;

import ir.sadeqcloud.BontechEvaluationProject.custException.AdminTransactionRolledBackException;
import ir.sadeqcloud.BontechEvaluationProject.service.adminService.AdminServiceContract;
import ir.sadeqcloud.BontechEvaluationProject.service.dto.OperationResult;
import ir.sadeqcloud.BontechEvaluationProject.utils.OperationResultContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectForExceptionTranslation {

    private final AdminServiceContract adminServiceContract;

    @Autowired
    public AspectForExceptionTranslation(AdminServiceContract adminServiceContract){
        this.adminServiceContract=adminServiceContract;
    }

    @Around("execution( * ir.sadeqcloud.BontechEvaluationProject.service.adminService.AdminServiceLayer.*(..))")
    public Object adminTransactionExceptionTranslation(ProceedingJoinPoint proceedingJoinPoint){
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable e) { //Exception translation , for security reasons and comprehensive api
            OperationResult operationResult = OperationResultContextHolder.getOperationResult();
            operationResult.setMessage(e.getMessage());
            // clear context on transactionalEvent side
            throw new AdminTransactionRolledBackException(operationResult);
        }

    }
}
