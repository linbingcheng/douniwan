package com.modelsystem.service.workflow.impl;

import com.modelsystem.model.po.workflow.PublicFile;
import com.modelsystem.service.BaseServiceImpl;
import com.modelsystem.service.workflow.PublicFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * Created by bingchenglin.
 */
@Service
public class PublicFileServiceImpl extends BaseServiceImpl<PublicFile> implements PublicFileService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

}
