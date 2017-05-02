package com.modelsystem.framework.enumtype;

/**
 * @project_name:     [ douniwan ]
 * @package_name:     [ com.modelsystem.framework.enumtype ]
 * @author:           [ LinBingcheng ]
 * @email:            [ 494410479@qq.com ]
 * @create_time       [ 2015-2-5下午8:54:52]
 * @description:      [ 排序类型 ]
 * @version           [ v1.0 ]
 * @last_update_user: [ LinBingcheng ]
 * @last_update_time: [ 2015-2-5下午8:54:52 ]
 */
public enum OrderType {

	ASC(0), 		//正序排序
	DESC(1);		//逆序排序

	private int value;

	private OrderType(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public static OrderType fromTo(int value) {
		OrderType[] values = OrderType.values();
		for (OrderType type : values) {
			if (type.getValue() == value) {
				return type;
			}
		}
		throw new IllegalArgumentException("类型不正确,不能识别为[" + value + "]的类型.");
	}

}
