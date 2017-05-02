package com.modelsystem.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.modelsystem.framework.bean.QueryRule;
import com.modelsystem.framework.bean.StringQueryRule;

/**
 * @project_name:     [ douniwan ]
 * @package_name:     [ com.modelsystem.framework.annotation ]
 * @author:           [ LinBingcheng ]
 * @email:            [ 494410479@qq.com ]
 * @create_time       [ 2015-2-6上午11:41:20]
 * @description:      [ 模糊查询注解 ，作用于实体类，标识后查询进行模糊查询]
 * @version           [ v1.0 ]
 * @last_update_user: [ LinBingcheng ]
 * @last_update_time: [ 2015-2-6上午11:41:20 ]
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})  
public @interface Query {
	
	//设置查询规则，默认字符串的模糊查询，可通过继承QueryRule自定义查询规则
	public Class<? extends QueryRule> rule() default StringQueryRule.class;

}
