package com.modelsystem.controller.workflow;

import com.modelsystem.controller.BaseController;
import com.modelsystem.model.po.workflow.Applicant;
import com.modelsystem.model.po.workflow.Applicant;
import com.modelsystem.model.util.MD5Encryption;
import com.modelsystem.service.workflow.ApplicantService;
import com.modelsystem.service.workflow.ApplicantService;
import org.apache.log4j.Logger;
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
@RequestMapping("workflow/applicant")
public class ApplicantController extends BaseController<Applicant> {

    private Logger log = Logger.getLogger(this.getClass());

    @RequestMapping(value = "sendEmail.action")
    @ResponseBody
    public void sendEmail(String email_to,String email_title,String email_content,HttpServletResponse response) throws IOException {
        ApplicantService applicantService = (ApplicantService) baseService;
        applicantService.sendEmail(email_to,email_title,email_content);
        response.getWriter().print(
                responseAfterSubmit(true, "邮件发送完成！"));

    }

}
