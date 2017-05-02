package com.modelsystem.framework.exception;

/**
 * @project_name:     [ douniwan ]
 * @package_name:     [ com.modelsystem.framework.exception ]
 * @author:           [ LinBingcheng ]
 * @email:            [ 494410479@qq.com ]
 * @create_time       [ 2015-2-7下午8:26:10]
 * @description:      [  定义FileUrlAnnotation异常，以便使用时发现错误 ]
 * @version           [ v1.0 ]
 * @last_update_user: [ LinBingcheng ]
 * @last_update_time: [ 2015-2-7下午8:26:10 ]
 */
public class FileUrlAnnotationUsedIllegalException extends RuntimeException{

	private static final long serialVersionUID = -1232295892957583227L;
	
	public FileUrlAnnotationUsedIllegalException() {
		super();
	}

	public FileUrlAnnotationUsedIllegalException(String message) {
		super(message);
	}

	public FileUrlAnnotationUsedIllegalException(Throwable throwable) {
		super(throwable);
	}

	public FileUrlAnnotationUsedIllegalException(String message,
			Throwable throwable) {
		super(message, throwable);
	}

}
