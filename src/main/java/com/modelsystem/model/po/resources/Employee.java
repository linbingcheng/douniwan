package com.modelsystem.model.po.resources;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.modelsystem.framework.annotation.AddNowDate;
import com.modelsystem.framework.annotation.FileUrl;
import com.modelsystem.framework.annotation.Query;
import com.modelsystem.framework.annotation.UpdateDate;
import com.modelsystem.model.po.BaseModel;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by bingchenglin on 2017/4/12.
 */
@Entity
@Table(name = "resources_employee")
public class Employee extends BaseModel {

    private Integer id;
    @Query
    private String employeeCode;
    @Query
    private String name;
    private String sex;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date brithday;
    private String idcard;
    @Query
    private String phone;
    @Query
    private String e_mail;
    private Integer baseSalary;
    @FileUrl
    private String photo;
    private String departmentId;
    private String jobId;
    private String incumbency;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date joinDate;

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

    @Column(name = "employeeCode", nullable = false)
    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "sex", nullable = false)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Column(name = "brithday", nullable = false)
    public Date getBrithday() {
        return brithday;
    }

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }

    @Column(name = "idcard", nullable = false)
    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    @Column(name = "phone", nullable = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "e_mail", nullable = false)
    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    @Column(name = "baseSalary")
    public Integer getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Integer baseSalary) {
        this.baseSalary = baseSalary;
    }

    @Column(name = "photo")
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Column(name = "departmentId",nullable = false)
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Column(name = "jobId",nullable = false)
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Column(name = "joinDate")
    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
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

    @Column(name = "incumbency")
    public String getIncumbency() {
        return incumbency;
    }

    public void setIncumbency(String incumbency) {
        this.incumbency = incumbency;
    }
}
