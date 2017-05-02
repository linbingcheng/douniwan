package com.modelsystem.service.workflow.impl;

import com.modelsystem.model.po.userinfo.User;
import com.modelsystem.model.po.workflow.RecruitmentRequest;
import com.modelsystem.service.BaseServiceImpl;
import com.modelsystem.service.workflow.RecruitmentRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * Created by bingchenglin.
 */
@Service
public class RecruitmentRequestServiceImpl extends BaseServiceImpl<RecruitmentRequest> implements RecruitmentRequestService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public RecruitmentRequest agree(Integer id) {
        RecruitmentRequest request = get(id);
        User user = (User) getSessionAttribute("user");
        if(user.getId() == request.getManagerId()){
            request.setIsManagerPass("同意");
        }
        if(user.getUserType().equals("HR")){
            request.setIsHrPass("同意");
            request.setHrId(user.getId());
        }
        return request;
    }

    @Override
    public RecruitmentRequest overrule(Integer id) {
        RecruitmentRequest request = get(id);
        User user = (User) getSessionAttribute("user");
        if(user.getId() == request.getManagerId()){
            request.setIsManagerPass("驳回");
        }
        if(user.getUserType().equals("HR")){
            request.setIsHrPass("驳回");
            request.setHrId(user.getId());
        }
        return request;
    }
}
