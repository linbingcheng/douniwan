package com.modelsystem.model.po;


import com.modelsystem.framework.enumtype.JsUIType;
import com.modelsystem.framework.enumtype.StatusType;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @project_name: [ douniwan ]
 * @package_name: [ com.modelsystem.model.po ]
 * @author: [ LinBingcheng ]
 * @email: [ 494410479@qq.com ]
 * @create_time [ 2015-2-4下午11:23:05]
 * @description: [ TODO ]
 * @version [ v1.0 ]
 * @last_update_user: [ LinBingcheng ]
 * @last_update_time: [ 2015-2-4下午11:23:05 ]
 */
@SuppressWarnings("all")
@MappedSuperclass//此注解说明他的子类能够基础他的注解
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BaseModel implements Serializable {


	private int offSet = 0;

	private int page = 1;

	private int rows = 10;// easyui表格

	private int start = 0;

	private int limit = 10;// ext表格上传

	private StatusType status;

	private JsUIType jsUIType = JsUIType.EASYUI;//前端JS框架类型


	@Transient//将忽略这些字段和属性,不用持久化到数据库.
	public int getOffSet() {
		return offSet;
	}

	public void setOffSet(int offSet) {
		this.offSet = offSet;
	}

	@Transient 
	public int getPage() {
		return page > 0 ? page : 0;
	}

	public void setPage(int page) {
		this.page = page;
		if (page > 0)
			this.offSet = (page - 1) * rows;
	}


	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	public StatusType getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}

	@Transient 
	public int getRows() {
		return rows;
	}

	@Transient 
	public JsUIType getJsUIType() {
		return jsUIType;
	}

	public void setJsUIType(JsUIType jsUIType) {
		this.jsUIType = jsUIType;
	}

	public void setRows(int rows) {
		this.rows = rows;
		if (page > 0) {
			this.offSet = (page - 1) * rows;
		}
	}

	@Transient 
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	@Transient 
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
