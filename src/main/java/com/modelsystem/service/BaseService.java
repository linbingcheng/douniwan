package com.modelsystem.service;

import java.io.Serializable;
import java.util.List;

import com.modelsystem.framework.bean.PageRusult;
import com.modelsystem.model.po.BaseModel;

import javax.servlet.http.HttpSession;

public interface BaseService<T extends BaseModel> {
	
	/**
	 * 保存
	 * @param entity 需要持久化的对象
	 * @return	保存成功 ： 对象的id;		<br>
	 * 			保存失败 ： null
	 */
	public Serializable save(T entity);

	/**
	 * 修改对象信息<br>
	 * @param entity 需要修改信息的对象	
	 * @return 修改成功 ：true 		<br>
	 * 	                   修改失败 ：false		
	 */
	public boolean update(T entity);
	
	/**
	 * 删除对象
	 * @param entity 需要删除的对象
	 * @return 删除成功 ： true;		<br>
	 * 		        删除失败 ： false
	 */
	public boolean delete(T entity);
	
	/**
	 * 根据Id删除对象
	 * @param id 需要删除的对象的Id
	 * @return   删除成功 ： true;	<br>
	 * 		             删除失败 ： false
	 */
	public boolean delete(Serializable id);
	
	/**
	 * 根据Id获取对象
	 * @param id 对象的Id
	 * @return	获取成功 ： 根据Id获取的对象;  <br>
	 *          获取失败 ：  null
	 */
	public T get(Serializable id);
	

	/**
	 * 冻结数据
	 * @param entity 需要冻结的对象
	 * @return 冻结成功 ： true;	<br>
	 * 		        冻结失败 ： false
	 */
	public boolean congeal(T entity);
	
	
	/**
	 * 解冻数据
	 * @param entity 需要解冻的对象
	 * @return 解冻成功 ： true;	<br>
	 * 		        解冻失败 ： false
	 */
	public boolean thaw(T entity);
	
	/**
	 * 参照规范可提供分页、查找、模糊查找，排序功能<br>
	 * 根据属性名数组和属性值数组获取实体对象集合<br>
//	 * @param entity 需要查询的对象，里面封装了要进行查询的数据
	 * @return PageRusult对象
	 */
	public PageRusult query(T entity);
	
	/**
	 * 查询所有数据
	 * @param entity 需要查询的对象，里面封装了要进行查询的数据
	 * @param isorder 是否排序
	 * @return 查询到对象的结果集
	 */
	public List<T> query(T entity,boolean isOrder);
	
	/**
	 * 查询所有记录
	 * @return 查询成功 ： 对象数据库里的所有对象集合<br>
	 *         查询失败 ： null
	 */
	public List<T> findAll();

	/**
	 * 根据属性名数组和属性值数组获取实体对象集合<br>
	 * @param propertyName 属性名称数组
	 * @param valueArray   每个属性名称对应的属性值的数组
	 * @return 查询成功 ： 对象数据库里的所有对象集合;	<br>
	 *         查询失败 ： null
	 */
	public List<T> findAll(String[] propertyNameArray, Object[] valueArray);

	/**
	 * 根据属性名集合和属性值集合获取实体对象集合<br>
	 * @param propertyNameList 属性名称集合
	 * @param valueList 	        每个属性名称对应的属性值的集合
	 * @return 查询成功 ： 对象数据库里的所有对象集合;	<br>
	 *         查询失败 ： null
	 */
	public List<T> findAll(List<String> propertyNameList, List<Object> valueList);
	
	
	/**
	 * 根据属性名和属性值集合获取实体对象集合<br>
	 * 查询的是某属性值在一定范围内的对象的集合
	 * @param propertyNameArray 属性名称
	 * @param valueArray	           属性值集合
	 * @return 查询成功 ： 对象数据库里的所有对象集合;	<br>
	 *         查询失败 ： null
	 */
	public List<T> findAll(String propertyName, List<Object> valueList);

	/**
	 * 根据属性名和属性值集合获取实体对象集合<br>
	 * 查询的是某属性值在一定范围内的对象的集合
	 * @param propertyNameArray 属性名称
	 * @param valueArray	           属性值集合
	 * @param isOrder		           是否排序
	 * @return 查询成功 ： 对象数据库里的所有对象集合;	<br>
	 *         查询失败 ： null
	 */
	public List<T> query(String[] propertyNameArray, Object[] valueArray,
			boolean isOrder);
	
	/**
	 * 参照规范可提供查找、模糊查找、排序<br>
	 * 根据属性名集合和属性值集合获取实体对象集合<br>
	 * @param propertyNameList 属性名称集合
	 * @param valueList 	        每个属性名称对应的属性值的集合
	 * @param isOrder		        是否排序
	 * @return 查询成功 ： 对象数据库里的所有对象集合;	<br>
	 *         查询失败 ： null
	 */
	public List<T> query(List<String> propertyNameList,
			List<Object> valueList, boolean isOrder);
	
	/**
	 * 参照规范可提供分页、查找、模糊查找，排序功能<br>
	 * 根据属性名数组和属性值数组获取实体对象集合<br>
	 * @param propertyName 属性名称数组
	 * @param valueArray   每个属性名称对应的属性值的数组
	 * @param firstResult  第几条数据开始读取 
	 * @param maxResults   最多读取几条数据
	 * @return PageRusult对象
	 */
	public PageRusult query(String[] propertyNameArray, Object[] valueArray,
			int firstResult, int maxResults);	
	
	/**
	 * 参照规范可提供分页、查找、模糊查找，排序功能<br>
	 * 根据属性名集合和属性值集合获取实体对象集合<br>
	 * @param propertyNameList 属性名称集合
	 * @param valueList 	        每个属性名称对应的属性值的集合
	 * @param firstResult  第几条数据开始读取 
	 * @param maxResults   最多读取几条数据
	 * @return PageRusult对象
	 */
	public PageRusult query(List<String> propertyNameList, List<Object> valueList,
			int firstResult, int maxResults);	
	
	/**
	 * 获取数据库表中所有记录的计数
	 * @return 查找结果总条数
	 */
	public Integer getAllCounts();
	
	
	/**
	 * 根据条件返回记录数
	 * @param params 属性名称数组
	 * @param values 属性值条件数组
	 * @return  返回记录数
	 */
	public Integer getCounts(String[] params, String[] values);


    HttpSession getHttpSession();

}
