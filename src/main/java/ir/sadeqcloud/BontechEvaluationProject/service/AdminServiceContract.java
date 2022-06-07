package ir.sadeqcloud.BontechEvaluationProject.service;

import ir.sadeqcloud.BontechEvaluationProject.controller.dto.SimpleUserDto;


public interface AdminServiceContract {
    boolean createUser(SimpleUserDto simpleUserDto);
}
