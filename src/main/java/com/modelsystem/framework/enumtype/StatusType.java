package com.modelsystem.framework.enumtype;

/**
 * @project_name:     [ douniwan ]
 * @package_name:     [ com.modelsystem.framework.enumtype ]
 * @author:           [ LinBingcheng ]
 * @email:            [ 494410479@qq.com ]
 * @create_time       [ 2015-2-5下午8:55:14]
 * @description:      [ 实体状态 ]
 * @version           [ v1.0 ]
 * @last_update_user: [ LinBingcheng ]
 * @last_update_time: [ 2015-2-5下午8:55:14 ]
 */
public enum StatusType {

	NORMAL(0),		//正常
	CONGEAL(1);//,		//冻结
	//	DELETE(2);		//删除
	
	private int value;
	
	private StatusType(int value){
		 this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public static StatusType fromTo(int value)
	{
		StatusType[] values = StatusType.values();
		for (StatusType type : values)
		{
			if (type.getValue() == value)
			{
				return type;
			}
		}
		throw new IllegalArgumentException("类型不正确,不能识别为[" + value + "]的类型.");
	}
}
