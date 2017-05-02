package com.modelsystem.service.workflow.impl;

import com.modelsystem.model.po.userinfo.User;
import com.modelsystem.model.po.workflow.ApplyLeaveRequest;
import com.modelsystem.service.BaseServiceImpl;
import com.modelsystem.service.workflow.ApplyLeaveRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * Created by bingchenglin.
 */
@Service
public class ApplyLeaveRequestServiceImpl extends BaseServiceImpl<ApplyLeaveRequest> implements ApplyLeaveRequestService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ApplyLeaveRequest agree(Integer id) {
        ApplyLeaveRequest leaveRequest = get(id);
        User user = (User) getSessionAttribute("user");
        if(user.getId() == leaveRequest.getManagerId()){
            leaveRequest.setIsManagerPass("同意");
        }
        if(user.getUserType().equals("HR")){
            leaveRequest.setIsHrPass("同意");
            leaveRequest.setHrId(user.getId());
        }
        return leaveRequest;
    }

    @Override
    public ApplyLeaveRequest overrule(Integer id) {
        ApplyLeaveRequest leaveRequest = get(id);
        User user = (User) getSessionAttribute("user");
        if(user.getId() == leaveRequest.getManagerId()){
            leaveRequest.setIsManagerPass("驳回");
        }
        if(user.getUserType().equals("HR")){
            leaveRequest.setIsHrPass("驳回");
            leaveRequest.setHrId(user.getId());
        }
        return leaveRequest;
    }
}
