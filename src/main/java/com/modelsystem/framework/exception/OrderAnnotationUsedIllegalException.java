package com.modelsystem.framework.exception;

/**
 * @project_name:     [ douniwan ]
 * @package_name:     [ com.modelsystem.framework.exception ]
 * @author:           [ LinBingcheng ]
 * @email:            [ 494410479@qq.com ]
 * @create_time       [ 2015-2-6下午7:55:27]
 * @description:      [ 定义OrderAnnotation异常，以便使用时发现错误 ]
 * @version           [ v1.0 ]
 * @last_update_user: [ LinBingcheng ]
 * @last_update_time: [ 2015-2-6下午7:55:27 ]
 */
public class OrderAnnotationUsedIllegalException extends RuntimeException {

	private static final long serialVersionUID = -7708918388020041712L;

	public OrderAnnotationUsedIllegalException() {
		super();
	}

	public OrderAnnotationUsedIllegalException(String message) {
		super(message);
	}

	public OrderAnnotationUsedIllegalException(Throwable throwable) {
		super(throwable);
	}

	public OrderAnnotationUsedIllegalException(String message,
			Throwable throwable) {
		super(message, throwable);
	}

}
