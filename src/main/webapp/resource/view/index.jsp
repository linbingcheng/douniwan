<%@ page import="com.modelsystem.model.po.userinfo.User" %>
<%@ page import="com.modelsystem.model.po.resources.Employee" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String name = "";
    Employee employee = (Employee) session.getAttribute("employee");
    User user = (User)session.getAttribute("user");
    if(employee!=null){
        name = employee.getName();
    }else {
        name = user.getUsername();
    }
%>
<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>人力资源管理系统</title>
    <link rel="stylesheet" href="../js/common/themes/bootstrap/easyui.css"charset="utf-8" />
    <link rel="stylesheet" href="../js/common/themes/icon.css" charset="utf-8" />
    <link rel="stylesheet" href="../css/style.css" charset="utf-8">
</head>
<body class="easyui-layout">
	<div class="header"
		data-options="region:'north',split:false,minHeight:120">
		<div class="logo">
			<h1>
                人力资源管理系统 <br /> <span> human resource management system</span>
			</h1>
		</div>

		<div class="topNav">
			<div class="topMenu">
				<ul id="top-menu-list">
					<li value="menu_index"><a href="#"><img id="index"
							src="../image/index.gif" alt="工作主页" title="工作主页"
							style="background-color: #CEE0F6" class="easyui-tooltip"></a></li>
                    <% if(user.getUserType().equals("HR")||user.getUserType().equals("systemadmin")||user.getUserType().equals("manager")){ %>
                    <li value="menu_work_order"><a href="#"><img id="work_order"
							src="../image/work_order.gif" alt="工单管理" title="工单管理" class="easyui-tooltip"></a></li>
                    <%}%>
                    <% if(user.getUserType().equals("HR")||user.getUserType().equals("systemadmin")){ %>
					<li value="menu_info"><a href="#"><img id="info"
							src="../image/info.gif" alt="信息管理" title="信息管理" class="easyui-tooltip"></a></li>
                    <li value="menu_system"><a href="#"><img id="system"
							src="../image/system.gif" alt="系统管理" title="系统管理" class="easyui-tooltip"></a></li>
                    <%}%>
				</ul>
			</div>
			<div class="line"></div>
			<div class="user_info">
				<div class="user_left">
					<span class="user_name"><%=name%></span><br />
					<div class="btn">
						<a href="#" onclick="logout();">注销</a>
					</div>
				</div>
			</div>

		</div>

	</div>
	<!--<div data-options="region:'south',split:false" style="height:50px;">
    	<p style="text-align:center">Copyright&copy; 2015 douniwan </p>
    </div> -->
	<div id="left_menu" class="left" title="首页" iconCls="menu_iconCls"
		headerCls="menu_title" data-options="region:'west',split:false"
		collapsible=false style="width: 200px;">
		<ul id="menu_index" class="navContent">
			<li value="工作首页"><a href="../view/home/bookList.jsp" onclick="return false;">工作首页</a></li>
            <li value="共享文件"><a href="../view/workflow/public_file.jsp" onclick="return false;">共享文件</a></li>
            <li value="请假申请"><a href="../view/workflow/apply_leave_request.jsp" onclick="return false;">请假申请</a></li>
			<li value="加班申请"><a href="../view/workflow/overtime_application_request.jsp" onclick="return false;">加班申请</a></li>
            <li value="招聘申请"><a href="../view/workflow/recruitment_request.jsp" onclick="return false;">招聘申请</a></li>
            <li value="离职申请"><a href="../view/workflow/abdicate_request.jsp" onclick="return false;">离职申请</a></li>
        </ul>
		<ul id="menu_work_order" class="navContent" style="display: none;">
            <li value="请假管理"><a href="../view/workorder/apply_leave_request.jsp" onclick="return false;">请假管理</a></li>
            <li value="加班管理"><a href="../view/workorder/overtime_application_request.jsp" onclick="return false;">加班管理</a></li>
            <li value="招聘管理"><a href="../view/workorder/recruitment_request.jsp" onclick="return false;">招聘管理</a></li>
            <li value="离职管理"><a href="../view/workorder/abdicate_request.jsp" onclick="return false;">离职管理</a></li>
            <li value="绩效管理"><a href="../view/workflow/exam_result.jsp" onclick="return false;">绩效管理</a></li>
            <% if(user.getUserType().equals("HR")||user.getUserType().equals("systemadmin")){ %>
            <li value="应聘信息"><a href="../view/workflow/applicant.jsp" onclick="return false;">应聘信息</a></li>
            <%}%>
        </ul>
		<ul id="menu_info" class="navContent" style="display: none;">
            <li value="员工管理"><a href="../view/resources/employee.jsp" onclick="return false;">员工管理</a></li>
            <li value="部门管理"><a href="../view/resources/department.jsp" onclick="return false;">部门管理</a></li>
            <li value="职务管理"><a href="../view/resources/job.jsp" onclick="return false;">职务管理</a></li>
            <li value="资源管理"><a href="../view/resources/property.jsp" onclick="return false;">资源管理</a></li>
        </ul>
		<ul id="menu_system" class="navContent" style="display: none;">
			<li value="用户管理"><a href="../view/userinfo/user.jsp" onclick="return false;">用户管理</a></li>
<%--
            <li value="权限管理"><a href="../view/userinfo/user.jsp" onclick="return false;">权限管理</a></li>
--%>
        </ul>
	</div>
	<div data-options="region:'center'">
		<div id="tabs" class="easyui-tabs" data-options="fit:true" style="overflow: hidden;">

			<div title="工作首页" iconCls="icon-mini-refresh" data-options="href:'../view/userinfo/index.jsp'" cls="main_body"></div>

		</div>
	</div>
    <div id="win"> </div>
    <script type="text/javascript" src="../js/common/jquery.min.js"  charset="utf-8"></script>
    <script type="text/javascript" src="../js/common/jquery.easyui.min.js"  charset="utf-8"></script>
    <script type="text/javascript" src="../js/common/easyloader.js" charset="utf-8"></script>
    <script type="text/javascript"src="../js/common/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
    <script type="text/javascript" src="../js/common/jquery.form.js"  charset="utf-8"></script>
    <script type="text/javascript" src="../js/index.js" charset="utf-8"></script>
    <script type="text/javascript" src="../js/common.js" charset="utf-8"></script>
    <script>
        (function($){

        })(jQuery);

        function logout(){
            $.PostJson("<%=request.getContextPath()%>/user/logout.action",null,function(isSuccess, json){
                if(isSuccess){
                    if(json.success){
                        window.location.href = "<%=request.getContextPath()%>/index.jsp";
                    }
                }else{
                    prompt(0,"退出失败");
                }
            });
        }
    </script>
</body>
</html>