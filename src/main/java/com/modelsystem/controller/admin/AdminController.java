package com.modelsystem.controller.admin;

import com.modelsystem.framework.enumtype.StatusType;
import com.modelsystem.model.po.resources.Department;
import com.modelsystem.model.po.userinfo.User;
import com.modelsystem.model.util.GenerateCodeByTemplate;
import com.modelsystem.model.util.MD5Encryption;
import com.modelsystem.service.resources.DepartmentService;
import com.modelsystem.service.userinfo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@Controller
@RequestMapping("admin")
public class AdminController{
	
	private static final String AdminIndexUrl = "resource/view/admin/index";

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

	@RequestMapping(value = "index.action", method = RequestMethod.GET)
	public String AdminIndex() {
		System.out.println("dddddddddddddddddddd");
		System.out.println("cdadasdadsada");
		System.out.println("++++++++++++++");
		return AdminIndexUrl;
	}

	@RequestMapping(value = "test.action")
	public void test(HttpServletResponse response) throws IOException {
        try {
            System.out.println("处理开始时间:"+System.currentTimeMillis());
            Thread.sleep(60000);
            System.out.println("处理完成时间:"+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        response.getWriter().print("bye");
	}

    @RequestMapping(value = "initManager.action")
    public void initManager(HttpServletResponse response) throws IOException {
        User user = new User();
        user.setEmployeeCode("0");
        user.setUsername("systemadmin");
        user.setPassword(MD5Encryption.getMD5("123456"));
        user.setUserType("systemadmin");
        user.setE_mail("494410479@qq.com");
        user.setStatus(StatusType.CONGEAL.NORMAL);
        user.setCreate_date(new Date());
        user.setUpdate_date(new Date());
        userService.save(user);
        response.getWriter().print("bye");
    }

    @RequestMapping(value = "initDepartment.action")
    public void initDepartment(HttpServletResponse response) throws IOException {
        Department department = new Department();
        department.setId(1);
        department.setParentId(-1);
        department.setManager("systemadmin");
        department.setName("+");
        department.setDescri("");
        department.setStatus(StatusType.CONGEAL.NORMAL);
        department.setCreate_date(new Date());
        department.setUpdate_date(new Date());
        departmentService.save(department);
        response.getWriter().print("bye");
    }

    @RequestMapping(value = "generateCodeByTemplale.action")
    public void generateCodeByTemplale(HttpServletResponse response) throws IOException {
        GenerateCodeByTemplate.generateDaoCode();
        GenerateCodeByTemplate.generateDaoImplCode();
        GenerateCodeByTemplate.generateServiceCode();
        GenerateCodeByTemplate.generateServiceImplCode();
        GenerateCodeByTemplate.generateControllerCode();
        GenerateCodeByTemplate.generateJspCode();
        response.getWriter().print("bye!");
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
}
