package com.modelsystem.service.workflow.impl;

import com.modelsystem.common.dao.resources.EmployeeDao;
import com.modelsystem.common.dao.userinfo.UserDao;
import com.modelsystem.model.po.resources.Employee;
import com.modelsystem.model.po.userinfo.User;
import com.modelsystem.model.po.workflow.AbdicateRequest;
import com.modelsystem.service.BaseServiceImpl;
import com.modelsystem.service.resources.EmployeeService;
import com.modelsystem.service.workflow.AbdicateRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by bingchenglin.
 */
@Service
public class AbdicateRequestServiceImpl extends BaseServiceImpl<AbdicateRequest> implements AbdicateRequestService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDao userDao;
    @Autowired
    private EmployeeService employeeService;

    public AbdicateRequest agree(Integer id) {
        AbdicateRequest request = get(id);
        User employeeUser = userDao.get(request.getRequestId());
        Employee employee = employeeService.findAll(new String[]{"employeeCode"},new String[]{employeeUser.getEmployeeCode()}).get(0);
        User user = (User) getSessionAttribute("user");
        if(user.getId() == request.getManagerId()){
            request.setIsManagerPass("同意");
        }
        if(user.getUserType().equals("HR")){
            request.setIsHrPass("同意");
            request.setHrId(user.getId());
            employee.setIncumbency("离职");
            employeeService.update(employee);
        }
        return request;
    }

    @Override
    public AbdicateRequest overrule(Integer id) {
        AbdicateRequest request = get(id);
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

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
