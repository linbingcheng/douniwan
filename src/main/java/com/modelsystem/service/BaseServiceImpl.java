package com.modelsystem.service;

import com.modelsystem.common.dao.BaseDao;
import com.modelsystem.framework.bean.PageRusult;
import com.modelsystem.framework.enumtype.JsUIType;
import com.modelsystem.framework.enumtype.StatusType;
import com.modelsystem.model.po.BaseModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class BaseServiceImpl<T extends BaseModel> implements BaseService<T> {

	@Autowired
	private BaseDao<T> baseDao;

	// 获取HttpSession
	public HttpSession getHttpSession() {
		return getRequest().getSession();
	}

	// 获取Request
	protected HttpServletRequest getRequest() {
		return getServletRequestAttributes().getRequest();
	}

	/**
	 * 设置 Session属性
	 * @param name 属性名
	 * @param value 属性值
	 */
	public void setSessionAttribute(String name, Object value) {
		getHttpSession().setAttribute(name, value);
	}

	/**
	 * 获取session 属性
	 * @param name 属性名
	 * @return 返回Session中名字为[name]的对象
	 */
	public Object getSessionAttribute(String name) {
		return getHttpSession().getAttribute(name);
	}

	//保存
	@Transactional
	public Serializable save(T entity) {
        System.out.println(entity.toString());
        entity.setStatus(StatusType.NORMAL);
		return baseDao.save(entity);
	}

	//修改对象信息
	@Transactional
	public boolean update(T entity) {
		return baseDao.update(entity);
	}

	//删除对象信息
	@Transactional
	public boolean delete(T entity) {
		return baseDao.delete(entity);
	}

	//删除对象信息
	@Transactional
	public boolean delete(Serializable id) {
		return baseDao.delete(id);
	}

	//根据Id获取对象信息
	@Transactional(readOnly = true)
	public T get(Serializable id) {
		return baseDao.get(id);
	}

	//冻结数据
	@Transactional
	public boolean congeal(T entity) {
		entity.setStatus(StatusType.CONGEAL);
		return baseDao.update(entity);
	}

	//解冻数据
	@Transactional
	public boolean thaw(T entity) {
		entity.setStatus(StatusType.NORMAL);
		return  baseDao.update(entity);
	}

	//分页查询数据
	@Transactional(readOnly = true)
	public PageRusult query(T entity) {
		List<Field> fields = getDBFields();
		List<String> propertyNameList = new ArrayList<String>();
		List<Object> valueList = new ArrayList<Object>();
		int firstResult = 0;
		int maxResults = 0;
		
		if(entity.getJsUIType()== JsUIType.EASYUI){
			firstResult = entity.getOffSet();
			maxResults = entity.getRows();
		}
		if(entity.getJsUIType()==JsUIType.EXT){
			firstResult = entity.getStart();
			maxResults = entity.getLimit();
		}
		
		for (Field field : fields) {
			try {
				Object value = null;
				if ((value = field.get(entity)) != null) {
					if (!value.getClass().equals(String.class)) {
						propertyNameList.add(field.getName());
						valueList.add(value);
					} else {
                        if (StringUtils.isNotBlank((String)value)) {
                            propertyNameList.add(field.getName());
							valueList.add(value);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return baseDao.query(propertyNameList, valueList, firstResult, maxResults);
	}
	
	// 查询要求的所有数据
	@Transactional(readOnly = true)
	public List<T> query(T entity,boolean isorder){
		List<Field> fields = getDBFields();
		List<String> propertyNameList = new ArrayList<String>();
		List<Object> valueList = new ArrayList<Object>();
		for (Field field : fields) {
			try {
				Object value = null;
				if ((value = field.get(entity)) != null) {
					if (!field.getClass().equals(String.class)) {
						propertyNameList.add(field.getName());
						valueList.add(value);
						
					} else {
                        if (StringUtils.isNotBlank((String)value)) {
							propertyNameList.add(field.getName());
							valueList.add(value);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return baseDao.query(propertyNameList, valueList, isorder);
	}

	//查询所有记录
	@Transactional(readOnly = true)
	public List<T> findAll() {
		return baseDao.findAll();
	}

	//根据属性名数组和属性值数组获取实体对象集合
	@Transactional(readOnly = true)
	public List<T> findAll(String[] propertyNameArray, Object[] valueArray) {
		return baseDao.findAll(propertyNameArray, valueArray);
	}

	//根据属性名集合和属性值集合获取实体对象集合
	@Transactional(readOnly = true)
	public List<T> findAll(List<String> propertyNameList, List<Object> valueList) {
		return baseDao.findAll(propertyNameList, valueList);
	}

	//根据属性名和属性值集合获取实体对象集合
	//查询的是某属性值在一定范围内的对象的集合
	@Transactional(readOnly = true)
	public List<T> findAll(String propertyName, List<Object> valueList) {
		return baseDao.findAll(propertyName, valueList);
	}

	//根据属性名数组和属性值数组获取实体对象集合
	@Transactional(readOnly = true)
	public List<T> query(String[] propertyNameArray, Object[] valueArray,
			boolean isOrder) {
		return baseDao.query(propertyNameArray, valueArray, isOrder);
	}

	//根据属性名集合和属性值集合获取实体对象集合
	@Transactional(readOnly = true)
	public List<T> query(List<String> propertyNameList, List<Object> valueList,
			boolean isOrder) {
		return baseDao.query(propertyNameList, valueList, isOrder);
	}

	//根据属性名数组和属性值数组获取实体对象集合
	@Transactional(readOnly = true)
	public PageRusult query(String[] propertyNameArray, Object[] valueArray,
			int firstResult, int maxResults) {
		return baseDao.query(propertyNameArray, valueArray, firstResult,
				maxResults);
	}

	// 根据属性名集合和属性值集合获取实体对象集合
	@Transactional(readOnly = true)
	public PageRusult query(List<String> propertyNameList,
			List<Object> valueList, int firstResult, int maxResults) {
		return baseDao.query(propertyNameList, valueList, firstResult,
				maxResults);
	}

	//获取数据库表中所有记录的计数
	@Transactional(readOnly = true)
	public Integer getAllCounts() {
		return baseDao.getAllCounts();
	}

	//根据条件返回记录数
	@SuppressWarnings("deprecation")
	@Transactional(readOnly = true)
	public Integer getCounts(String[] params, String[] values) {
		return baseDao.getCounts(params, values);
	}
	
	// 获取ServletRequestAttributes
	private ServletRequestAttributes getServletRequestAttributes() {
		return (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
	}

	/**
	 * 获取在数据库中有映射关系的属性 
	 * @return 所有在数据库中有映射关系的成员属性
	 */
	private List<Field> getDBFields() {
		List<Field> fields = new ArrayList<Field>();
		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		try {
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
				if (method.isAnnotationPresent(Column.class)
						|| method.isAnnotationPresent(OneToOne.class)
						|| method.isAnnotationPresent(OneToMany.class)
						|| method.isAnnotationPresent(ManyToOne.class)
						|| method.isAnnotationPresent(ManyToMany.class)) {
					StringBuffer s = new StringBuffer(method.getName()
							.toString());
					String fieldsName = s.substring(3, 4).toLowerCase()
							+ s.substring(4);
					Field field = clazz.getDeclaredField(fieldsName);
					field.setAccessible(true);
					fields.add(field);
				}
			}
		} catch (Exception e) {
		}
		return fields;
	}
}
