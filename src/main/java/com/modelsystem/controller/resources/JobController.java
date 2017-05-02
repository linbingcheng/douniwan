package com.modelsystem.controller.resources;

import com.modelsystem.controller.BaseController;
import com.modelsystem.model.po.resources.Job;
import com.modelsystem.model.vo.JobVO;
import com.modelsystem.service.resources.JobService;
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
@RequestMapping("resources/job")
public class JobController extends BaseController<Job> {

    private Logger log = Logger.getLogger(this.getClass());

    @RequestMapping(value = "jobs.action")
    @ResponseBody
    public Object[] geJobs(HttpServletRequest request, HttpServletResponse response) {
        JobService jobService = (JobService) baseService;
        return  JobVO.change2VO(jobService.findAll()).toArray();
    }

}
