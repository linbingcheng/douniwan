package com.modelsystem.controller.userinfo;

import com.modelsystem.controller.BaseController;
import com.modelsystem.model.po.userinfo.User;
import com.modelsystem.model.util.MD5Encryption;
import com.modelsystem.service.userinfo.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by bingchenglin on 2017/4/5.
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController<User> {

    private Logger log = Logger.getLogger(this.getClass());


    @Override
    @RequestMapping(value = "save.action")
    @ResponseBody
    public void save(User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserService userService = (UserService) baseService;
        String result = userService.checkUserMessage(user.getEmployeeCode(),user.getUsername());
        if(result != null){
                response.getWriter().print(
                        responseAfterSubmit(false, result));
        }else{
            user.setPassword(MD5Encryption.getMD5(user.getPassword()));
            super.save(user, request, response);
        }
    }

    @RequestMapping(value = "login.action")
    @ResponseBody
    public void login(User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserService userService = (UserService) baseService;
        String result = userService.checkUserIsExist(user.getUsername(),user.getPassword());
        if(result != null){
            response.getWriter().print(
                    responseAfterSubmit(false, result));
        }else {
            response.getWriter().print(
                    responseAfterSubmit(true, "登录成功"));
        }
    }


    @RequestMapping(value = "updatePassword.action")
    @ResponseBody
    public void updatePassword(String password,String newpassword,HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserService userService = (UserService) baseService;
        User user = (User) baseService.getHttpSession().getAttribute("user");
        if(!user.getPassword().equals(MD5Encryption.getMD5(password))){
            response.getWriter().print(
                    responseAfterSubmit(false, "原密码输入错误！"));
            return;
        }else {
            user.setPassword(MD5Encryption.getMD5(newpassword));
            super.update(user,request,response);
        }

    }

    @RequestMapping(value = "logout.action")
    @ResponseBody
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserService userService = (UserService) baseService;
        userService.logout();
        response.getWriter().print(
                responseAfterSubmit(true, "注销成功"));
    }
}
