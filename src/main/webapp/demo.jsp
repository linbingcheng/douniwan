<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">

	<script src="/douniwan/resource/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
 <script type="text/javascript">
		$(document).ready(function(){
			$("#add").click(function(){
				$.ajax({
					url:"/douniwan/test_user/save.action",
					type:"post",
					data: $("#form").serializeArray(),
					success:function(data){
						alert(data);
					}
				});
			});
		});
	</script>	
		-->
  </head>
  
  <body>
  <form action="/test_user/save.action" id="form" method="post" enctype="multipart/form-data">
  测试字符串数据：用户名：<input type="text" id="userName" name="userName" /><br/>
  测试日期：日期：<input type="text" id="date" name="date"><br/>
 测试整型数据：年龄：<input type="text" id="date" name="age"><br/>
 测试Boolean型：是：<input type="checkbox" id="isManager" value="true" name="isManager">
  否：<input type="checkbox" id="isManager" value="false"  name="isManager"><br/>
  <p>自定义文件注解，使用默认配置,文件输入表单name属性为upload，单文件上传，不保留文件名，使用默认文件保存地址</p>
 测试单文件上传1：<input type="file" name="upload"><br/>
  <p>自定义文件注解,文件输入表单name属性为newupload，保留文件名，不适用默认文件保存地址，自己定义一个(不过一定要有这个文件夹)</p>
 测试单文件上传2：<input type="file" name="newupload"><br/>
  <p>自定义文件注解,多文件上传，文件输入表单name属性为manyupload，保留文件名，使用默认文件保存地址</p>
测试多文件上传按照规范：
<input type="file" name="manyupload"><br/>
<input type="file" name="manyupload"><br/>
<input type="file" name="manyupload"><br/>
  <input type="submit"  value="提交">
  </form>
  <hr/>
  查询所有数据（附排序功能）<br/>
  <a href="/test_user/find.action">
  /test_user/find.action</a><br/>
  
 加条件查询功能（怎么使用在树上面就看能自己了。。。）<br/>
 <a href="/test_user/find.action?date=2014-04-05&age=16">
  /test_user/find.action?date=2014-04-05&age=16
 </a><br/>
 此接口加上自定位查询注解（可以自己设计规则还可以模糊查询）<br/>
 <a href="/test_user/find.action?userName=admi&age=16">
/test_user/find.action?userName=admi&age=16
</a><br/>
 <hr/>
 可分页查询<br/>
 <a href="/test_user/query.action?userName=admi&age=16&limit=2&start=1">
/test_user/query.action?userName=admi&age=16&limit=2&start=1
</a><br/>
  </body>
</html>
