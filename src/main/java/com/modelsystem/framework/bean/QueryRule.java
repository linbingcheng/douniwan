package com.modelsystem.framework.bean;

import org.hibernate.Criteria;

/**
 * @project_name:     [ douniwan ]
 * @package_name:     [ com.modelsystem.framework.bean ]
 * @author:           [ LinBingcheng ]
 * @email:            [ 494410479@qq.com ]
 * @create_time       [ 2015-2-6下午1:51:54]
 * @description:      [ 添加查询规则接口 ]
 * @version           [ v1.0 ]
 * @last_update_user: [ LinBingcheng ]
 * @last_update_time: [ 2015-2-6下午1:51:54 ]
 */
public abstract class QueryRule {
	
	/**
	 * 定义字段查询规则必须实现此接口
	 * @param criteria Criteria对象
	 * @param property 查询的字段
	 * @param keyword  查询的参数，可以是未处理的json字符串以便自己实现查询规则
	 * @return 处理过的Criteria对象
	 */
	public abstract Criteria addRule(Criteria criteria, String property,
			Object value);
	
}
