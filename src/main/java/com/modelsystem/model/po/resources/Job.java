package com.modelsystem.model.po.resources;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.modelsystem.framework.annotation.AddNowDate;
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
@Table(name = "recources_job")
public class Job extends BaseModel {

    private Integer id;
    @Query
    private String name;

    private String level;

    private Integer num;

    private Integer maxNum;

    private String desc;


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

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "job_level")
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Column(name = "job_desc")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Column(name = "num")
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Column(name = "maxNum")
    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
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

}
