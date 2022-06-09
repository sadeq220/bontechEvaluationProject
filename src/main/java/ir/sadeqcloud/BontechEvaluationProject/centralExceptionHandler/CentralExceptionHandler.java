package ir.sadeqcloud.BontechEvaluationProject.centralExceptionHandler;

import ir.sadeqcloud.BontechEvaluationProject.centralExceptionHandler.dto.ClientLimitationPassedResponseDto;
import ir.sadeqcloud.BontechEvaluationProject.centralExceptionHandler.dto.SimpleUSerExceptionResponseDto;
import ir.sadeqcloud.BontechEvaluationProject.custException.AdminTransactionRolledBackException;
import ir.sadeqcloud.BontechEvaluationProject.custException.ClientLimitPassedException;
import ir.sadeqcloud.BontechEvaluationProject.custException.ServiceNotAvailableAtTheMoment;
import ir.sadeqcloud.BontechEvaluationProject.service.dto.OperationResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CentralExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ServiceNotAvailableAtTheMoment.class)
    public ResponseEntity<SimpleUSerExceptionResponseDto> serviceNotAvailableHandler(ServiceNotAvailableAtTheMoment serviceNotAvailableAtTheMoment){
        SimpleUSerExceptionResponseDto simpleUSerExceptionResponseDto = new SimpleUSerExceptionResponseDto(serviceNotAvailableAtTheMoment.getCommercialServiceName(), "service not available at the moment!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(simpleUSerExceptionResponseDto);
    }
    @ExceptionHandler(AdminTransactionRolledBackException.class)
    public ResponseEntity<OperationResult> adminTransactionRollback(AdminTransactionRolledBackException adminTransactionRolledBackException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(adminTransactionRolledBackException.getOperationResult());
    }
    @ExceptionHandler(ClientLimitPassedException.class)
    public ResponseEntity<ClientLimitationPassedResponseDto> clientPassedServiceLimitation(ClientLimitPassedException clientLimitPassedException){
        ClientLimitationPassedResponseDto clientLimitationPassedResponseDto = new ClientLimitationPassedResponseDto(clientLimitPassedException.getCommercialServiceName(), clientLimitPassedException.getLimitationUsage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(clientLimitationPassedResponseDto);
    }

}
