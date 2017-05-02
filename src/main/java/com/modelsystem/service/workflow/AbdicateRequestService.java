package com.modelsystem.service.workflow;

import com.modelsystem.model.po.workflow.AbdicateRequest;
import com.modelsystem.service.BaseService;

/**
 * Created by bingchenglin.
 */
public interface AbdicateRequestService  extends BaseService<AbdicateRequest> {


    AbdicateRequest agree(Integer id);

    AbdicateRequest overrule(Integer id);
}
