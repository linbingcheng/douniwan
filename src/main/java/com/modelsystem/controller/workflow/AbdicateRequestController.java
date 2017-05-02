package com.modelsystem.controller.workflow;

import com.modelsystem.controller.BaseController;
import com.modelsystem.model.po.workflow.AbdicateRequest;
import com.modelsystem.model.po.workflow.AbdicateRequest;
import com.modelsystem.service.userinfo.UserService;
import com.modelsystem.service.workflow.AbdicateRequestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by bingchenglin.
 */
@Controller
@RequestMapping("workflow/abdicate_request")
public class AbdicateRequestController extends BaseController<AbdicateRequest> {

    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "save.action")
    @ResponseBody
    public void save(AbdicateRequest abdicateRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        abdicateRequest.setManagerId(userService.getManagerId());
        super.save(abdicateRequest,request,response);
    }

    @RequestMapping(value = "agree.action")
    @ResponseBody
    public void agree(Integer id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        AbdicateRequestService AbdicateRequestService = (AbdicateRequestService) baseService;
        AbdicateRequest leaveRequest = AbdicateRequestService.agree(id);
        super.update(leaveRequest,request,response);
    }

    @RequestMapping(value = "overrule.action")
    @ResponseBody
    public void overrule(Integer id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        AbdicateRequestService AbdicateRequestService = (AbdicateRequestService) baseService;
        AbdicateRequest leaveRequest = AbdicateRequestService.overrule(id);
        super.update(leaveRequest,request,response);
    }


}
