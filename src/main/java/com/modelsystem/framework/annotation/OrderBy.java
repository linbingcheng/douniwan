package com.modelsystem.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.modelsystem.framework.enumtype.OrderType;

/**
 * @project_name:     [ douniwan ]
 * @package_name:     [ com.modelsystem.framework.annotation ]
 * @author:           [ LinBingcheng ]
 * @email:            [ 494410479@qq.com ]
 * @create_time       [ 2015-2-5下午9:01:22]
 * @description:      [ 排序注解，作用于实体类，查询排序类型 ]
 * @version           [ v1.0 ]
 * @last_update_user: [ LinBingcheng ]
 * @last_update_time: [ 2015-2-5下午9:01:22 ]
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})  
public @interface OrderBy {
	
	public OrderType type() default OrderType.ASC; //设置排序方式，默认是正序排序
	
	public int precedence() default 1;//排序优先级

}
