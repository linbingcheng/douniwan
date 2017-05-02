package com.modelsystem.framework.enumtype;

/**
 * @project_name:     [ douniwan ]
 * @package_name:     [ com.modelsystem.framework.enumtype ]
 * @author:           [ LinBingcheng ]
 * @email:            [ 494410479@qq.com ]
 * @create_time       [ 2015-2-5下午8:53:45]
 * @description:      [ 文件类型 ]
 * @version           [ v1.0 ]
 * @last_update_user: [ LinBingcheng ]
 * @last_update_time: [ 2015-2-5下午8:53:45 ]
 */
public enum FileType {
	
	IMG(0),		//图片
	FILE(1);	//文件有机会划分在划分出doc文件
	
	private int value;
	
	private FileType(int value){
		 this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public static FileType fromTo(int value)
	{
		FileType[] values = FileType.values();
		for (FileType fileType : values)
		{
			if (fileType.getValue() == value)
			{
				return fileType;
			}
		}
		throw new IllegalArgumentException("类型不正确,不能识别为[" + value + "]的类型.");
	}
	public static void main(String[] args) {
		System.out.println(FileType.FILE);
		System.out.println(FileType.FILE.value);
	}
}
