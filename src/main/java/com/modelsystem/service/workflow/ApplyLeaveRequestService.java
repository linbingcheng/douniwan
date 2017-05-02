package com.modelsystem.service.workflow;

import com.modelsystem.model.po.workflow.ApplyLeaveRequest;
import com.modelsystem.service.BaseService;

/**
 * Created by bingchenglin.
 */
public interface ApplyLeaveRequestService  extends BaseService<ApplyLeaveRequest> {


    ApplyLeaveRequest agree(Integer id);

    ApplyLeaveRequest overrule(Integer id);


}
