package com.modelsystem.controller.workflow;

import com.modelsystem.controller.BaseController;
import com.modelsystem.model.po.workflow.RecruitmentRequest;
import com.modelsystem.model.po.workflow.RecruitmentRequest;
import com.modelsystem.model.util.MD5Encryption;
import com.modelsystem.service.userinfo.UserService;
import com.modelsystem.service.workflow.RecruitmentRequestService;
import com.modelsystem.service.workflow.RecruitmentRequestService;
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
@RequestMapping("workflow/recruitment_request")
public class RecruitmentRequestController extends BaseController<RecruitmentRequest> {

    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "save.action")
    @ResponseBody
    public void save(RecruitmentRequest recruitmentRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        RecruitmentRequestService RecruitmentRequestService = (RecruitmentRequestService) baseService;
        recruitmentRequest.setManagerId(userService.getManagerId());
        super.save(recruitmentRequest,request,response);
    }

    @RequestMapping(value = "agree.action")
    @ResponseBody
    public void agree(Integer id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        RecruitmentRequestService RecruitmentRequestService = (RecruitmentRequestService) baseService;
        RecruitmentRequest leaveRequest = RecruitmentRequestService.agree(id);
        super.update(leaveRequest,request,response);
    }

    @RequestMapping(value = "overrule.action")
    @ResponseBody
    public void overrule(Integer id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        RecruitmentRequestService RecruitmentRequestService = (RecruitmentRequestService) baseService;
        RecruitmentRequest leaveRequest = RecruitmentRequestService.overrule(id);
        super.update(leaveRequest,request,response);
    }

}
