package com.modelsystem.service.workflow;

import com.modelsystem.model.po.workflow.OvertimeApplicationRequest;
import com.modelsystem.service.BaseService;

/**
 * Created by bingchenglin.
 */
public interface OvertimeApplicationRequestService  extends BaseService<OvertimeApplicationRequest> {


    OvertimeApplicationRequest agree(Integer id);

    OvertimeApplicationRequest overrule(Integer id);
}
