package com.modelsystem.model.vo;

import com.modelsystem.model.po.resources.Job;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bingchenglin on 2017/4/17.
 */
public class JobVO implements Serializable{

    private Integer id;
    private String text;

    public JobVO(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public static List<JobVO> change2VO(List<Job> jobs){
        List<JobVO> jobVOs = new ArrayList<JobVO>();
        for (Job job:jobs){
            JobVO jobVO =  new JobVO(job.getId(),job.getName());
            jobVOs.add(jobVO);
        }
        return jobVOs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
