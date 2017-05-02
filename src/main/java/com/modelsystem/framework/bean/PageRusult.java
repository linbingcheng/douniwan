package com.modelsystem.framework.bean;

import java.util.List;

/**
 * @project_name:     [ douniwan ]
 * @package_name:     [ com.modelsystem.model.vo ]
 * @author:           [ LinBingcheng ]
 * @email:            [ 494410479@qq.com ]
 * @create_time       [ 2015-2-5下午8:38:18]
 * @description:      [ 分页查询结果集 ]
 * @version           [ v1.0 ]
 * @last_update_user: [ LinBingcheng ]
 * @last_update_time: [ 2015-2-5下午8:38:18 ]
 */
public class PageRusult {

	private long total;			//查询到的数据的总条数
	private List<?> rows; //查询到的末一页的数据

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
