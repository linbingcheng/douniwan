package com.modelsystem.model.po.workflow;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.modelsystem.framework.annotation.AddNowDate;
import com.modelsystem.framework.annotation.UpdateDate;
import com.modelsystem.model.po.BaseModel;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by bingchenglin on 2017/4/18.
 */
@Entity
@Table(name = "workfolw_overtime_application_request")
public class OvertimeApplicationRequest extends BaseModel {

    private Integer id;
    private String type;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;
    private String reason;
    private Integer requestId;
    private Integer managerId;
    private Integer hrId;
    private String isManagerPass;
    private String isHrPass;

    @AddNowDate
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date create_date;
    @UpdateDate
    @AddNowDate
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date update_date;

    @GenericGenerator(name = "generator", strategy = "increment")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonFormat(pattern = "yyyy-MM-dd  hh:mm:ss", timezone = "GMT+8")
    @Column(name = "create_date")
    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    @JsonFormat(pattern = "yyyy-MM-dd  hh:mm:ss", timezone = "GMT+8")
    @Column(name = "update_date")
    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }


    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Column(name = "startDate")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Column(name = "endDate")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Column(name = "requestId")
    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    @Column(name = "managerId")
    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    @Column(name = "hrId")
    public Integer getHrId() {
        return hrId;
    }

    public void setHrId(Integer hrId) {
        this.hrId = hrId;
    }

    @Column(name = "isManagerPass")
    public String getIsManagerPass() {
        return isManagerPass;
    }

    public void setIsManagerPass(String isManagerPass) {
        this.isManagerPass = isManagerPass;
    }

    @Column(name = "isHrPass")
    public String getIsHrPass() {
        return isHrPass;
    }

    public void setIsHrPass(String isHrPass) {
        this.isHrPass = isHrPass;
    }
}
