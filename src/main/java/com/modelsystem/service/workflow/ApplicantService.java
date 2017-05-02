package com.modelsystem.service.workflow;

import com.modelsystem.model.po.workflow.Applicant;
import com.modelsystem.service.BaseService;

/**
 * Created by bingchenglin.
 */
public interface ApplicantService  extends BaseService<Applicant> {


    void sendEmail(String email_to, String email_title, String email_content);
}
