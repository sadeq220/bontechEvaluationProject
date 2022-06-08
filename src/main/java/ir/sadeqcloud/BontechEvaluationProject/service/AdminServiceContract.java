package ir.sadeqcloud.BontechEvaluationProject.service;

import ir.sadeqcloud.BontechEvaluationProject.controller.dto.SimpleUserDto;
import ir.sadeqcloud.BontechEvaluationProject.service.dto.OperationResult;


public interface AdminServiceContract {
    OperationResult createUser(SimpleUserDto simpleUserDto);
}
