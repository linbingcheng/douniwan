package com.modelsystem.framework.enumtype;

/**
 * @project_name:     [ douniwan ]
 * @package_name:     [ com.modelsystem.framework.enumtype ]
 * @author:           [ LinBingcheng ]
 * @email:            [ 494410479@qq.com ]
 * @create_time       [ 2015-2-5下午8:53:58]
 * @description:      [ 前端JSUI框架类型 ]
 * @version           [ v1.0 ]
 * @last_update_user: [ LinBingcheng ]
 * @last_update_time: [ 2015-2-5下午8:53:58 ]
 */
public enum JsUIType {

	EXT(0), 		//ext
	EASYUI(1);		//easyui

	private int value;

	private JsUIType(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
	
	public static JsUIType fromTo(int value)
	{
		JsUIType[] values = JsUIType.values();
		for (JsUIType type : values)
		{
			if (type.getValue() == value)
			{
				return type;
			}
		}
		throw new IllegalArgumentException("类型不正确,不能识别为[" + value + "]的类型.");
	}

}
