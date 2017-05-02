package com.modelsystem.service.resources.impl;

import com.modelsystem.model.po.resources.Property;
import com.modelsystem.service.BaseServiceImpl;
import com.modelsystem.service.resources.PropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * Created by bingchenglin.
 */
@Service
public class PropertyServiceImpl extends BaseServiceImpl<Property> implements PropertyService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

}
