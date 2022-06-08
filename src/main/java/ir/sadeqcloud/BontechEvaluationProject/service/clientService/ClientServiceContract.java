package ir.sadeqcloud.BontechEvaluationProject.service.clientService;

import ir.sadeqcloud.BontechEvaluationProject.service.dto.OperationResult;

public interface ClientServiceContract {
    OperationResult send_SMS(String SMS);
}
