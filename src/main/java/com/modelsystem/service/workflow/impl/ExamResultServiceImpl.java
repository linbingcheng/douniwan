package com.modelsystem.service.workflow.impl;

import com.modelsystem.model.po.workflow.ExamResult;
import com.modelsystem.service.BaseServiceImpl;
import com.modelsystem.service.workflow.ExamResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * Created by bingchenglin.
 */
@Service
public class ExamResultServiceImpl extends BaseServiceImpl<ExamResult> implements ExamResultService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

}
