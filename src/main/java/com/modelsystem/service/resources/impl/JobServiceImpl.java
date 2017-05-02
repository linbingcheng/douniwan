package com.modelsystem.service.resources.impl;

import com.modelsystem.model.po.resources.Job;
import com.modelsystem.service.BaseServiceImpl;
import com.modelsystem.service.resources.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * Created by bingchenglin.
 */
@Service
public class JobServiceImpl extends BaseServiceImpl<Job> implements JobService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

}
