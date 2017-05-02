package com.modelsystem.framework.exception;

/**
 * @project_name:     [ douniwan ]
 * @package_name:     [ com.modelsystem.framework.exception ]
 * @author:           [ LinBingcheng ]
 * @email:            [ 494410479@qq.com ]
 * @create_time       [ 2015-2-8上午10:51:05]
 * @description:      [ 定义QueryAnnotation异常，以便使用时发现错误 ]
 * @version           [ v1.0 ]
 * @last_update_user: [ LinBingcheng ]
 * @last_update_time: [ 2015-2-8上午10:51:05 ]
 */
public class QueryAnnotationUsedIllegalException extends RuntimeException {

	private static final long serialVersionUID = 3447250990633287822L;

	public QueryAnnotationUsedIllegalException() {
		super();
	}

	public QueryAnnotationUsedIllegalException(String message) {
		super(message);
	}

	public QueryAnnotationUsedIllegalException(Throwable throwable) {
		super(throwable);
	}

	public QueryAnnotationUsedIllegalException(String message,
			Throwable throwable) {
		super(message, throwable);
	}

}
