package com.modelsystem.model.po;

import com.modelsystem.model.util.Finals;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;
import java.util.List;

/**
 * @project_name:     [ douniwan ]
 * @package_name:     [ com.modelsystem.model.po ]
 * @author:           [ LinBingcheng ]
 * @email:            [ 494410479@qq.com ]
 * @create_time       [ 2015-2-5下午8:45:42]
 * @description:      [ TODO ]
 * @version           [ v1.0 ]
 * @last_update_user: [ LinBingcheng ]
 * @last_update_time: [ 2015-2-5下午8:45:42 ]
 * @param <T> 叶子的泛型
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TreeModel<T> implements Serializable {

	private static final long serialVersionUID = -8123483052398418765L;
	
	private List<T> children;
	private String state;//easyui 树形
	private Boolean expanded = false;// ext 树形

	public List<T> getChildren() {
		return children;
	}

	public void setChildren(List<T> children) {
		this.children = children;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		if (state == null) {
			this.state = null;
		} else {
			if(state.equals(Finals.TREE_MODEL_ESAY_UI_OPEN)){
				this.state = Finals.TREE_MODEL_ESAY_UI_OPEN;
			}else{
				this.state = Finals.TREE_MODEL_ESAY_UI_CLOSED;
			}
		}
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	
}
