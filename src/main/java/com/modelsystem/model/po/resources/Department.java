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
@Table(name = "recources_department")
public class Department extends BaseModel {

    private Integer id;

    private Integer parentId;
    @Query
    private String name;
    @Query
    private String manager;

    private String descri;
    ;

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", manager='" + manager + '\'' +
                ", descri='" + descri + '\'' +
                ", create_date=" + create_date +
                ", update_date=" + update_date +
                '}';
    }

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

    @Column(name = "parent_id", nullable = false)
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "manager")
    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Column(name = "descri")
    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
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
