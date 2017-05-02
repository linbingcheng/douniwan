package com.modelsystem.framework.bean;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 * @project_name:     [ douniwan ]
 * @package_name:     [ com.modelsystem.framework.bean ]
 * @author:           [ LinBingcheng ]
 * @email:            [ 494410479@qq.com ]
 * @create_time       [ 2015-2-6下午1:57:54]
 * @description:      [ 字符串模糊查询规则 ]
 * @version           [ v1.0 ]
 * @last_update_user: [ LinBingcheng ]
 * @last_update_time: [ 2015-2-6下午1:57:54 ]
 */
public class StringQueryRule extends QueryRule{

	public Criteria addRule(Criteria criteria, String property,
			Object value) {
		String keyword = String.valueOf(value);
			criteria.add(Restrictions.like(property, "%" + keyword + "%"));
		return criteria;
	}

}
