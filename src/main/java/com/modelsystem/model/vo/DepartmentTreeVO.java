package com.modelsystem.model.vo;

import com.modelsystem.model.po.TreeModel;
import com.modelsystem.model.po.resources.Department;

/**
 * Created by bingchenglin on 2017/4/17.
 */
public class DepartmentTreeVO extends TreeModel{

    private Integer id;
    private String text;

    public static DepartmentTreeVO changeDepartment2TreeVO(Department  department){
        return new DepartmentTreeVO(department.getId(),department.getName());
    }

    public DepartmentTreeVO(Integer id, String text) {
        this.id = id;
        this.text = text;
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
