package com.modelsystem.service.workflow.impl;

import com.modelsystem.common.util.PropertiesUtil;
import com.modelsystem.ext.message.HtmlMail;
import com.modelsystem.ext.message.Mail;
import com.modelsystem.ext.message.TextMail;
import com.modelsystem.model.po.workflow.Applicant;
import com.modelsystem.service.BaseServiceImpl;
import com.modelsystem.service.workflow.ApplicantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * Created by bingchenglin.
 */
@Service
public class ApplicantServiceImpl extends BaseServiceImpl<Applicant> implements ApplicantService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String username = PropertiesUtil.getProperty("/mail.properties", "username");
    private String password = PropertiesUtil.getProperty("/mail.properties", "password");


    @Override
    public void sendEmail(String email_to, String email_title, String email_content) {
        HtmlMail mail = new HtmlMail();
        mail.setServerHost(Mail.SMTP_QQ_HOST);
        mail.setServerPort(Mail.SMTP_QQ_PORT);
        mail.setUsername(username);
        mail.setPassword(password);
        mail.setValidate(true);
        mail.setFrom(username);
        mail.setSubject(email_title);
        mail.setContent("<div>" + email_content +"</div>");
        mail.setTo(email_to);
        mail.send();
    }

    public static void main(String[] args) {
        ApplicantServiceImpl service = new ApplicantServiceImpl();
        service.sendEmail("1808778612@qq.com","标题党","ddddddd");
    }
}
