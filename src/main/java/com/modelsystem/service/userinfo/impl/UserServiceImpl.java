package com.modelsystem.service.userinfo.impl;

import com.modelsystem.common.dao.resources.DepartmentDao;
import com.modelsystem.common.dao.resources.EmployeeDao;
import com.modelsystem.common.dao.resources.JobDao;
import com.modelsystem.framework.enumtype.StatusType;
import com.modelsystem.model.po.resources.Department;
import com.modelsystem.model.po.resources.Employee;
import com.modelsystem.model.po.resources.Job;
import com.modelsystem.model.po.userinfo.User;
import com.modelsystem.model.util.MD5Encryption;
import com.modelsystem.service.BaseServiceImpl;
import com.modelsystem.service.userinfo.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bingchenglin on 2017/4/5.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private JobDao jobDao;

    @Override
    public String checkUserMessage(String employeeCode, String username) {
        List<User> users = null;
        if((users = findAll(new String[]{"employeeCode"},new String[]{employeeCode}))!= null && !users.isEmpty()){
            return "该员工已设置帐号";
        }
        if((users = findAll(new String[]{"username"},new String[]{username}))!= null && !users.isEmpty()){
            return "该帐号存在，可在后面加上相应后缀区分";
        }
        return null;
    }

    @Override
    public String checkUserIsExist(String username, String password) {
        List<User> users = null;
        if((users = findAll(new String[]{"username"},new String[]{username}))== null || users.isEmpty()){
            return "该用户帐号不存在！";
        }
        if(!(MD5Encryption.getMD5(password)).equals(users.get(0).getPassword())){
            return "密码错误！";
        }
        if(users.get(0).getStatus().equals(StatusType.CONGEAL)){
            return "该账户已被注销！";
        }
        User user = users.get(0);
        setSessionAttribute("user", user);
        List<Employee> employees = employeeDao.findAll(new String[]{"employeeCode"}, new String[]{user.getEmployeeCode()});
        if(employees != null && !employees.isEmpty()){
            setSessionAttribute("employee", employees.get(0));
            Department department = departmentDao.get( Integer.valueOf(employees.get(0).getDepartmentId()));
            if(department != null){
                setSessionAttribute("department",department);
            }
            Job job = jobDao.get(Integer.valueOf(employees.get(0).getJobId()));
            if(job != null){
                setSessionAttribute("job",job);
            }
        }
        return null;
    }

    @Override
    public void logout() {
        getHttpSession().removeAttribute("user");
    }



    @Override
    public Integer getManagerId() {
        User user = (User) getSessionAttribute("user");
        Employee employee = employeeDao.findAll(new String[]{"employeeCode"},new String[] {user.getEmployeeCode()}).get(0);
        Department department = departmentDao.get(Integer.valueOf(employee.getDepartmentId()));
        Employee manager = employeeDao.findAll(new String[]{"name"},new String[] {department.getManager()}).get(0);
        return findAll(new String[]{"employeeCode"},new String[] {manager.getEmployeeCode()}).get(0).getId();
    }

    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public JobDao getJobDao() {
        return jobDao;
    }

    public void setJobDao(JobDao jobDao) {
        this.jobDao = jobDao;
    }
}
