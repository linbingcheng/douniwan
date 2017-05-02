package com.modelsystem.service.userinfo;

import com.modelsystem.model.po.userinfo.User;
import com.modelsystem.service.BaseService;

/**
 * Created by bingchenglin on 2017/4/5.
 */
public interface UserService  extends BaseService<User> {

    String checkUserMessage(String employeeCode,String username);

    String checkUserIsExist(String username,String password);

    void logout();

    //此处有隐藏bug，就是系统设置的部门经理如果不存在的话会出错
    Integer getManagerId();

}
