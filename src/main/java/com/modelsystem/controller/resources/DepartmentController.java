package com.modelsystem.controller.resources;

import com.modelsystem.controller.BaseController;
import com.modelsystem.model.po.resources.Department;
import com.modelsystem.model.vo.DepartmentTreeVO;
import com.modelsystem.service.resources.DepartmentService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by bingchenglin.
 */
@Controller
@RequestMapping("resources/department")
public class DepartmentController extends BaseController<Department> {

    private Logger log = Logger.getLogger(this.getClass());

    @RequestMapping(value = "department_tree.action")
    @ResponseBody
    public DepartmentTreeVO[] getDepartmentTree(HttpServletRequest request,
                       HttpServletResponse response) {
        DepartmentService departmentService = (DepartmentService) baseService;
        DepartmentTreeVO tree = departmentService.getDepartmentTree(1);
        return new DepartmentTreeVO[]{tree};
    }


}
