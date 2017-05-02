package com.modelsystem.common.util;

import java.util.Collection;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @project_name:     [ douniwan ]
 * @package_name:     [ com.modelsystem.common.util ]
 * @author:           [ LinBingcheng ]
 * @email:            [ 494410479@qq.com ]
 * @create_time       [ 2015-2-10下午5:29:52]
 * @description:      [ Json工具类 ]
 * @version           [ v1.0 ]
 * @last_update_user: [ LinBingcheng ]
 * @last_update_time: [ 2015-2-10下午5:29:52 ]
 */
public class JsonUtil {
	
	private static class JsonHolder{
		private static final ObjectMapper mapper = new ObjectMapper();
	}

	/**
	 * 返回成功执行的操作json
	 * @param info
	 * @return
	 */
	public  static String success(String info,boolean addStr){
		if(addStr){
			return "{success:true,info:'"+info.trim()+"'}";
		}else{
			return "{success:true,info:"+info.trim()+"}";
		}
	}
	
	/**
	 * 返回失败执行的操作json
	 * @param info
	 * @return
	 */
	public static String failure(String info,boolean addStr){
		if(addStr){
			return "{success:false,info:'"+info.trim()+"'}";
		}else{
			return "{success:false,info:"+info.trim()+"}";
		}
	}
	
	/**
	 * 将一个对象解析成Json数据格式
	 * @param object
	 * @return
	 */
	public static String toJson(Object object){
		try {
			return JsonHolder.mapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 构建对象json数据
	 * @param total 总数据量
	 * @param values 数据集合
	 * @param excludes 
	 * @return
	 */
	public static String buildList(int total,Collection<?> list,String excludes){
		StringBuilder sb = new StringBuilder();
		//StringBuffer sb = new StringBuffer();
		sb.append("{\"total\":\""+total+"\",\"rows\":[");
		JsonConfig cfg = new JsonConfig();
		String[] excluds = null;
		if(excludes!=null&&!excludes.equals("")){
			excluds = excludes.split(",");
			if(excludes.length()>0){
				cfg.setExcludes(excluds);
			}
		}
		cfg.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		for(Object object : list){
			//声明json配置对象
			//设置循环策略为忽略、避免死循环
			JSONObject jsonObject = JSONObject.fromObject(object,cfg);
			sb.append(jsonObject.toString()+",");
		}
		if(list.size()>0){
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append("]}");
		return sb.toString();
	}
}
