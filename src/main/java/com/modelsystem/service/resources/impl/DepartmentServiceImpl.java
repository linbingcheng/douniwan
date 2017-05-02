package com.modelsystem.service.resources.impl;

import com.modelsystem.model.po.resources.Department;
import com.modelsystem.model.vo.DepartmentTreeVO;
import com.modelsystem.service.BaseServiceImpl;
import com.modelsystem.service.resources.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by bingchenglin.
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public DepartmentTreeVO getDepartmentTree(Integer id) {
        Department department = get(id);
        DepartmentTreeVO tree = DepartmentTreeVO.changeDepartment2TreeVO(department);
        List<DepartmentTreeVO> children = new ArrayList<DepartmentTreeVO>();
        List<Department> departments = findAll(new String[]{"parentId"},new Integer[]{id});
        if(departments!=null){
            for (Department d : departments){
                children.add(getDepartmentTree(d.getId()));
            }
        }
        tree.setChildren(children);
        return tree;
    }
}
