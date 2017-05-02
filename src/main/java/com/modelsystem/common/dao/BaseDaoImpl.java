package com.modelsystem.common.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.modelsystem.framework.annotation.Query;
import com.modelsystem.framework.annotation.OrderBy;
import com.modelsystem.framework.bean.PageRusult;
import com.modelsystem.framework.bean.QueryRule;
import com.modelsystem.framework.enumtype.OrderType;
import com.modelsystem.framework.exception.OrderAnnotationUsedIllegalException;
import com.modelsystem.framework.exception.QueryAnnotationUsedIllegalException;
import com.modelsystem.model.po.BaseModel;

public class BaseDaoImpl<T extends BaseModel> implements BaseDao<T> {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	protected HibernateTemplate hibernateTemplate;
	private Class<T> entityClass;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	// 保存
	public Serializable save(T entity) {
		try {
			Serializable id = hibernateTemplate.save(entity);
			return id;
		} catch (Exception e) {
			log.error(getEntityClass() + " [保存]异常信息  : ", e);
		}
		return null;
	}

	// 根据Id获取对象
	public T get(Serializable id) {
		try {
			return (T) hibernateTemplate.get(getEntityClass(), id);
		} catch (Exception e) {
			log.error(getEntityClass() + " [获取]异常信息 ： ", e);
		}
		return null;
	}

	// 根据Id获取对象
	public T load(Serializable id) {
		try {
			return (T) hibernateTemplate.load(getEntityClass(), id);
		} catch (Exception e) {
			log.error(getEntityClass() + " [获取]异常信息 ： ", e);
		}
		return null;
	}

	// 修改对象信息
	public boolean update(T entity) {
		try {
			hibernateTemplate.update(entity);
			return true;
		} catch (Exception e) {
			log.error(getEntityClass() + " [对象修改]异常信息 ： ", e);
		}
		return false;
	}

	// 删除对象
	public boolean delete(T entity) {
		try {
			hibernateTemplate.delete(entity);
			return true;
		} catch (Exception e) {
			log.error(getEntityClass() + " [删除]异常信息 ： ", e);
		}
		return false;
	}

	// 删除对象
	public boolean delete(Serializable id) {
		try {
			hibernateTemplate.delete(hibernateTemplate.load(getEntityClass(),
					id));
			return true;
		} catch (Exception e) {
			log.error(getEntityClass() + " [删除]异常信息 ： ", e);
		}
		return false;
	}

	// 查询所有记录
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		List<T> list = null;
		try {
			list = (List<T>) hibernateTemplate.find("from "
					+ getEntityClass().getName());
		} catch (Exception e) {
			log.error(getEntityClass() + " [查询所有记录]异常信息 ： ", e);
		}
		return list;
	}

	// 根据属性名数组和属性值数组获取实体对象集合
	@SuppressWarnings("unchecked")
	public List<T> findAll(String[] propertyNameArray, Object[] valueArray) {
		List<T> list = null;
		String hql = "from " + getEntityClass().getName()
				+ " as model where model." + propertyNameArray[0] + " = ?";

		for (int i = 1; i < propertyNameArray.length; i++) {
			hql = hql + " and  model." + propertyNameArray[i] + " = ?";
		}

		try {
			list = (List<T>) hibernateTemplate.find(hql, valueArray);
		} catch (Exception e) {
			log.error(getEntityClass() + " [根据属性名数组和属性值数组查询所有记录]异常信息 ： ", e);
		}
		return list;
	}

	// 根据属性名集合和属性值集合获取实体对象集合
	public List<T> findAll(List<String> propertyNameList, List<Object> valueList) {
		String[] propertyNameArray = propertyNameList
				.toArray(new String[propertyNameList.size()]);
		Object[] valueArray = valueList.toArray();
		return findAll(propertyNameArray, valueArray);
	}

	// 根据属性名和属性值集合获取实体对象集合
	// 查询的是某属性值在一定范围内的对象的集合
	@SuppressWarnings("unchecked")
	public List<T> findAll(String propertyName, List<Object> valueList) {
		List<T> list = null;
		String hql = "from " + getEntityClass().getName()
				+ " as model where model." + propertyName + " in (:valueList)";
		try {
			list = getSession().createQuery(hql)
					.setParameterList("valueList", valueList).list();
		} catch (Exception e) {
			log.error(getEntityClass() + " [查询某属性值在一定范围内的记录]异常信息 ： ", e);
		}
		return list;
	}

	// 参照规范可提供查找、模糊查找、排序
	// 根据属性名数组和属性值数组获取实体对象集合
	@SuppressWarnings("unchecked")
	public List<T> query(String[] propertyNameArray, Object[] valueArray,
			boolean isOrder) {
		Criteria criteria = getSession().createCriteria(getEntityClass());
		try {
			for (int i = 0; i < propertyNameArray.length; i++) {
				String property = propertyNameArray[i];
				Object value = valueArray[i];
				
				if (StringUtils.isNotEmpty(property) && value != null) {
					String propertyString = "";
					if (property.contains(".")) {
						String propertyPrefix = StringUtils.substringBefore(
								property, ".");
						String propertySuffix = StringUtils.substringAfter(
								property, ".");
						criteria.createAlias(propertyPrefix, "model");
						propertyString = "model." + propertySuffix;
						property = propertyString;
					}
					
					Field field = getEntityClass().getDeclaredField(property);
					if (field.isAnnotationPresent(Query.class)) {
						Query query = field.getAnnotation(Query.class);
						if (query == null) {
							throw new QueryAnnotationUsedIllegalException(
									getEntityClass().getName() + "."
											+ field.getName()
											+ "属性使用@Query注解时，rule不能设置为空");
						}
						
						QueryRule queryRule = field.getAnnotation(Query.class)
								.rule().newInstance();
						criteria = queryRule.addRule(criteria, property, value);
						if(criteria == null){
							throw new QueryAnnotationUsedIllegalException(
									getEntityClass().getName() + "."
											+ field.getName()
											+ "定义的rule未将Criteria返回，返回的是null");
						}
					} else {
						criteria.add(Restrictions.eq(property, value));
					}
				}
			}
			
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
			if (isOrder) {
				Field[] fields = getEntityClass().getDeclaredFields();
				List<String> orderPropers = new ArrayList<String>();
				List<OrderType> orderTypes = new ArrayList<OrderType>();
				List<Integer> precedences = new ArrayList<Integer>();
				
				for (int i = 0; i < fields.length; i++) {
					if (fields[i].isAnnotationPresent(OrderBy.class)) {
						OrderBy order = fields[i].getAnnotation(OrderBy.class);
						orderPropers.add(fields[i].getName());
						orderTypes.add(order.type());
						precedences.add(order.precedence());
					}
				}
				

				for (int i = 1; i <= precedences.size(); i++) {
					if (precedences.contains(i)) {
						int j = precedences.indexOf(i);
						OrderType orderType = orderTypes.get(j);
						String orderProper = orderPropers.get(j);

						if (orderType != null) {
							if (orderType == OrderType.ASC) {
								criteria.addOrder(Order.asc(orderProper));
							} else {
								criteria.addOrder(Order.desc(orderProper));
							}

						} else {
							throw new OrderAnnotationUsedIllegalException(
									getEntityClass().getName() + "."
											+ fields[i - 1].getName()
											+ "属性使用@Order注解时，type不能设置为空");
						}
					} else {
						throw new OrderAnnotationUsedIllegalException(
								getEntityClass().getName() + "."
										+ fields[i - 1].getName()
										+ "属性使用@Order注解时,"
										+ "precedence必须从1开始设置，依次递增 1");
					}

				}
			}
			return criteria.list();
		} catch (Exception e) {
			log.error(getEntityClass() + " [根据属性名数组和属性值数组获取实体对象集合]异常信息 ： ", e);
		}
		return null;
	}

	// 参照规范可提供查找、模糊查找、排序
	// 根据属性名集合和属性值集合获取实体对象集合
	public List<T> query(List<String> propertyNameList, List<Object> valueList,
			boolean isOrder) {
		String[] propertyNameArray = propertyNameList
				.toArray(new String[propertyNameList.size()]);
		Object[] valueArray = valueList.toArray();
		return query(propertyNameArray, valueArray, isOrder);
	}

	// 参照规范可提供分页、查找、模糊查找，排序功能
	// 根据属性名集合和属性值集合获取实体对象集合
	public PageRusult query(String[] propertyNameArray, Object[] valueArray,
			int firstResult, int maxResults) {
		PageRusult pageRusult = new PageRusult();
		Criteria criteria = getSession().createCriteria(getEntityClass());
		
		try {
			for (int i = 0; i < propertyNameArray.length; i++) {
				String property = propertyNameArray[i];
				Object value = valueArray[i];
				if (StringUtils.isNotEmpty(property) && value != null) {
					String propertyString = "";
					
					if (property.contains(".")) {
						String propertyPrefix = StringUtils.substringBefore(
								property, ".");
						String propertySuffix = StringUtils.substringAfter(
								property, ".");
						criteria.createAlias(propertyPrefix, "model");
						propertyString = "model." + propertySuffix;
						property = propertyString;
					}
					
					Field field = getEntityClass().getDeclaredField(property);
					if (field.isAnnotationPresent(Query.class)) {
						Query query = field.getAnnotation(Query.class);
						if (query == null) {
							throw new QueryAnnotationUsedIllegalException(
									getEntityClass().getName() + "."
											+ field.getName()
											+ "属性使用@Query注解时，rule不能设置为空");
						}
						
						QueryRule queryRule = field.getAnnotation(Query.class)
								.rule().newInstance();
						criteria = queryRule.addRule(criteria, property, value);
						if(criteria == null){
							throw new QueryAnnotationUsedIllegalException(
									getEntityClass().getName() + "."
											+ field.getName()
											+ "属性使用@Query注解时，定义的rule未将Criteria返回，返回的是null");
						}
					} else {
						criteria.add(Restrictions.eq(property, value));
					}
					
				}
			}
			
			Long totalCount =  (Long) criteria.setProjection(
					Projections.rowCount()).uniqueResult(); // 获取总记录数
			criteria.setProjection(null);
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
			criteria.setFirstResult(firstResult); // 这是每页第一个记录的序号
			criteria.setMaxResults(maxResults); // 设置每页显示最大数
			
			Field[] fields = getEntityClass().getDeclaredFields();
			List<String> orderPropers = new ArrayList<String>();
			List<OrderType> orderTypes = new ArrayList<OrderType>();
			List<Integer> precedences = new ArrayList<Integer>();
			
			for (int i = 0; i < fields.length; i++) {
				if (fields[i].isAnnotationPresent(OrderBy.class)) {
					OrderBy order = fields[i].getAnnotation(OrderBy.class);
					orderPropers.add(fields[i].getName());
					orderTypes.add(order.type());
					precedences.add(order.precedence());
				}
			}

			for (int i = 1; i <= precedences.size(); i++) {
				if (precedences.contains(i)) {
					int j = precedences.indexOf(i);
					OrderType orderType = orderTypes.get(j);
					String orderProper = orderPropers.get(j);

					if (orderType != null) {
						if (orderType == OrderType.ASC) {
							criteria.addOrder(Order.asc(orderProper));
						} else {
							criteria.addOrder(Order.desc(orderProper));
						}

					} else {
						throw new OrderAnnotationUsedIllegalException(
								getEntityClass().getName() + "."
										+ fields[i - 1].getName()
										+ "属性使用@Order注解时，type不能设置为空");
					}
				} else {
					throw new OrderAnnotationUsedIllegalException(
							getEntityClass().getName() + "."
									+ fields[i - 1].getName()
									+ "属性使用@Order注解时,"
									+ "precedence必须从1开始设置，依次递增 1");
				}

			}
			pageRusult.setTotal(totalCount);
			pageRusult.setRows(criteria.list());
		} catch (Exception e) {
			log.error(getEntityClass() + " [根据属性名集合和属性值集合获取实体对象集合]异常信息 ： ", e);
		}
		return pageRusult;
	}

	// 参照规范可提供分页、查找、模糊查找，排序功能
	// 根据属性名集合和属性值集合获取实体对象集合
	public PageRusult query(List<String> propertyNameList,
			List<Object> valueList, int firstResult, int maxResults) {
		String[] propertyNameArray = propertyNameList
				.toArray(new String[propertyNameList.size()]);
		Object[] valueArray = valueList.toArray();
		return query(propertyNameArray, valueArray, firstResult, maxResults);
	}

	// 根据条件返回记录数
	@Deprecated
	public Integer getCounts(String[] params, String[] values) {
		int count = 0;
		String hql = "select count (*) from " + getEntityClass().getName()
				+ " as model where model." + params[0] + " = '" + values[0]
				+ "'";
		
		for (int i = 1; i < params.length; i++) {
			hql = hql + " and  model." + params[i] + " = '" + values[i] + "'";
		}
		String countString = getSession().createQuery(hql).uniqueResult()
				.toString();
		count = Integer.parseInt(countString);
		return count;
	}

	// 获取数据库表中所有记录的计数
	public Integer getAllCounts() {
		int count = 0;
		try {
			String countString = getSession()
					.createQuery(
							"select count(*) from "
									+ getEntityClass().getName())
					.uniqueResult().toString();
			count = Integer.parseInt(countString);
		} catch (Exception e) {
			log.error(getEntityClass() + " [获取数据库表中所有记录的计数]异常信息 ： ", e);
		}
		return count;

	}

	/**
	 * 获取Hibernate当前所用的Session
	 * 
	 * @return Hibernate当前所用的Session
	 */
	protected Session getSession() {
		return hibernateTemplate.getSessionFactory().getCurrentSession();
	}

	/**
	 * 获取泛型的实际类型
	 * @return 泛型的实际类型class
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> getEntityClass() {
		if (this.entityClass == null) {
			this.entityClass = (Class<T>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return this.entityClass;
	}

}
