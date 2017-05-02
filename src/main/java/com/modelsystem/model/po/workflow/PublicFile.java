package com.modelsystem.model.po.workflow;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.modelsystem.framework.annotation.AddNowDate;
import com.modelsystem.framework.annotation.FileUrl;
import com.modelsystem.framework.annotation.UpdateDate;
import com.modelsystem.framework.annotation.Query;

import com.modelsystem.model.po.BaseModel;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by linbingchneg on 2017/5/2.
 */
@Entity
@Table(name = "workflow_publicfile")
public class PublicFile  extends BaseModel {

    private Integer id;
    @AddNowDate
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date create_date;
    @UpdateDate
    @AddNowDate
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date update_date;
    @FileUrl(keepFileName=true)
    @Query
    private String file;

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

    @Column(name = "file")
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
