package com.modelsystem.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.modelsystem.framework.enumtype.FileType;
import com.modelsystem.framework.finals.AnnotationFinals;

/**
 * @project_name:     [ douniwan ]
 * @package_name:     [ com.modelsystem.framework.annotation ]
 * @author:           [ LinBingcheng ]
 * @email:            [ 494410479@qq.com ]
 * @create_time       [ 2015-2-5下午8:59:27]
 * @description:      [ 文件注解，作用于实体类 ，用于标识保存的文件路径的字段]
 * @version           [ v1.0 ]
 * @last_update_user: [ LinBingcheng ]
 * @last_update_time: [ 2015-2-5下午8:59:27 ]
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})  
public @interface FileUrl {
	
	public FileType type() default FileType.FILE; //设置文件类型，默认是文件，根据不同类型确定不同保存策略;
	
	public String uploadPath() default  AnnotationFinals.FILE_DEDAULT_UPLOAD_PATH;// 设置文件存储的位置;
	
	public String name() default AnnotationFinals.FILE_DEDAULT_NAME;//设置文件上传前端对应的参数名称，默认常用的'upload'
	
	public boolean keepFileName() default false;//保留文件名,默认不保留，以防止中文在后面程序运行产生BUG
	
	public boolean isManyFileUpload() default false;//该字段是否的保存多个文件链接，但文件只保存一个文件的地址，多文件保存多个文件连接的json数组
	
}
