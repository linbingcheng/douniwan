package com.modelsystem.framework.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.modelsystem.framework.finals.UtilFinals;

/**
 * @project_name:     [ douniwan ]
 * @package_name:     [ com.modelsystem.framework.util ]
 * @author:           [ LinBingcheng ]
 * @email:            [ 494410479@qq.com ]
 * @create_time       [ 2015-2-6上午10:50:13]
 * @description:      [ 格式化字符串工具 ]
 * @version           [ v1.0 ]
 * @last_update_user: [ LinBingcheng ]
 * @last_update_time: [ 2015-2-6上午10:50:13 ]
 */
public class FormatStringUtil {
	
	public static String getUUIDString() {
		String s = UUID.randomUUID().toString();
		return s.substring(0,8) + s.substring(9,13) + 
				s.substring(14,18) + s.substring(19,23) + s.substring(24); 
	}
	
	public static String getDateString(){
		SimpleDateFormat sdf = new SimpleDateFormat(UtilFinals.FORMATSTRING_DATE_STRING);   
        return sdf.format(new Date()); 
	}
	
	public static String getDateAddRandomIDString(){
		SimpleDateFormat sdf = new SimpleDateFormat(UtilFinals.FORMATSTRING_DATE_STRING);   
        return sdf.format(new Date()) + UUID.randomUUID().toString().substring(24); 
	}
	
	public static String getTimeString(){
		SimpleDateFormat sdf = new SimpleDateFormat(UtilFinals.FORMATSTRING_TIME_STRING);   
        return sdf.format(new Date()); 
	}
	
	public static String getTimeAddRandomIDString(){
		SimpleDateFormat sdf = new SimpleDateFormat(UtilFinals.FORMATSTRING_TIME_STRING);   
        return sdf.format(new Date()) + UUID.randomUUID().toString().substring(24); 
	}
	
}