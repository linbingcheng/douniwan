package com.modelsystem.service.resources.impl;

import com.modelsystem.model.po.resources.Employee;
import com.modelsystem.service.BaseServiceImpl;
import com.modelsystem.service.resources.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * Created by bingchenglin.
 */
@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements EmployeeService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

}
