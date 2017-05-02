package com.modelsystem.controller.workflow;

import com.modelsystem.controller.BaseController;
import com.modelsystem.model.po.workflow.ExamResult;
import com.modelsystem.model.util.MD5Encryption;
import com.modelsystem.service.workflow.ExamResultService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by bingchenglin.
 */
@Controller
@RequestMapping("workflow/exam_result")
public class ExamResultController extends BaseController<ExamResult> {

    private Logger log = Logger.getLogger(this.getClass());

}
