package com.modelsystem.service.resources;

import com.modelsystem.model.po.resources.Department;
import com.modelsystem.model.vo.DepartmentTreeVO;
import com.modelsystem.service.BaseService;

/**
 * Created by bingchenglin.
 */
public interface DepartmentService  extends BaseService<Department> {

    public DepartmentTreeVO getDepartmentTree(Integer id);

}
