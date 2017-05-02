package com.modelsystem.service.workflow;

import com.modelsystem.model.po.workflow.RecruitmentRequest;
import com.modelsystem.service.BaseService;

/**
 * Created by bingchenglin.
 */
public interface RecruitmentRequestService  extends BaseService<RecruitmentRequest> {


    RecruitmentRequest agree(Integer id);

    RecruitmentRequest overrule(Integer id);
}
