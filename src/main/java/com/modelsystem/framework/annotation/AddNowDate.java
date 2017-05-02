package com.modelsystem.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @project_name:     [ douniwan ]
 * @package_name:     [ com.modelsystem.framework.annotation ]
 * @author:           [ LinBingcheng ]
 * @email:            [ 494410479@qq.com ]
 * @create_time       [ 2015-2-11上午10:57:23]
 * @description:      [ 创建添加时间类型注解，作用于实体类 ，可用于在添加model时前台不传参数来自动注入当前系统 时间 ]
 * @version           [ v1.0 ]
 * @last_update_user: [ LinBingcheng ]
 * @last_update_time: [ 2015-2-11上午10:57:23 ]
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})  
public @interface AddNowDate {

}
