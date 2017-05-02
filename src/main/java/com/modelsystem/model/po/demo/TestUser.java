package com.modelsystem.model.po.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.modelsystem.framework.annotation.FileUrl;
import com.modelsystem.framework.annotation.OrderBy;
import com.modelsystem.framework.annotation.Query;
import com.modelsystem.framework.enumtype.OrderType;
import com.modelsystem.model.po.BaseModel;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 
 * @author LinBingcheng
 *
 */
@Entity
@Table(name = "test_user")
public class TestUser extends BaseModel {

	private static final long serialVersionUID = 3904559547952504691L;

	// 测试Id
	private String id;
	
	// 测试字符串
	// 测试自定义查询，使用默认配置，字符串模糊查询
	@Query
	private String userName;
	
	// 测试时间类型，依赖次注解springMVC自动转换，前端传过来的String转化为将Date类型
	@DateTimeFormat(pattern="yyyy-MM-dd")
	// 测试自定义排序方式，第二个排序字段,倒叙排序
	@OrderBy(precedence=2,type=OrderType.DESC)
	private Date date;
	
	// 测试int类型，spring会自动叫基本类型转化注入的
	// 基本类型使用封装框架原有的封装的话要使用其包装类，即int使用Integer boolean使用Boolean
	// 测试自定义排序方式，第一个排序字段,使用默认
	@OrderBy
	private Integer age;
	
	// 测试Boolean类型，spring同时会叫String类型true和false转化为Boolean型数据注入
	private Boolean isManager;
	
	// 测试单文件上传
	@FileUrl//自定义文件注解，使用默认配置,文件输入表单name属性为upload，单文件上传，不保留文件名，使用默认文件保存地址
	private String oneFileUrl1;
	
	//自定义文件注解,文件输入表单name属性为newupload，保留文件名，不适用默认文件保存地址，自己定义一个(不过一定要有这个文件夹)
	@FileUrl(name="newupload",keepFileName=true,uploadPath="E:/testupload")
	private String oneFileUrl2;
	
	//自定义文件注解,多文件上传，文件输入表单name属性为manyupload，保留文件名
	@FileUrl(name="manyupload",isManyFileUpload=true,keepFileName=true,uploadPath="E:/testupload")
	private String ManyFileUrl;
	
	
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", nullable = false, length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "userName", nullable = false, length = 32)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//利用Jackson注释，格式化时间格式
	@Column(name = "date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "age")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "isManager")
	public Boolean getIsManager() {
		return isManager;
	}

	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}

	@Column(name = "oneFileUrl1")
	public String getOneFileUrl1() {
		return oneFileUrl1;
	}


	public void setOneFileUrl1(String oneFileUrl1) {
		this.oneFileUrl1 = oneFileUrl1;
	}

	@Column(name = "oneFileUrl2")
	public String getOneFileUrl2() {
		return oneFileUrl2;
	}


	public void setOneFileUrl2(String oneFileUrl2) {
		this.oneFileUrl2 = oneFileUrl2;
	}


	@Column(name = "ManyFileUrl")
	public String getManyFileUrl() {
		return ManyFileUrl;
	}


	public void setManyFileUrl(String manyFileUrl) {
		ManyFileUrl = manyFileUrl;
	}
	

}
