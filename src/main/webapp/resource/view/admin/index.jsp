<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <title>通用后台管理系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" href="../../js/common/themes/bootstrap/easyui.css" />
	<link rel="stylesheet" href="../../js/common/themes/icon.css" />
	<link rel="stylesheet" href="../../css/admin/style.css" />
	<script type="text/javascript" src="../../js/common/jquery.min.js" charset="utf-8"></script>
	<script type="text/javascript" src="../../js/common/easyloader.js" charset="utf-8"></script>
	<script type="text/javascript" src="../../js/common/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
	<script type="text/javascript" src="../../js/admin/index.js" charset="utf-8"></script>

  </head>
	<body class="easyui-layout">
    <div class="header" data-options="region:'north',split:false,minHeight:200" style="height:200px;/*background-color:#66BE91;*/">
    	<div class="logo">
    		<h1>
    			通用后台管理系统
    			<br/>
    			<span>
    				General background management system
    			</span>
    		</h1>
    	</div>

    	<div class="topNav">
    		<div class="topMenu">
    			<ul>
    				<li><a href="#"><img src="../../image/admin/index/menu.png" alt="模块一" title="模块一"></a></li>
    				<li><a href="#"><img src="../../image/admin/index/menu.png" alt="模块一" title="模块一"></a></li>
    				<li><a href="#"><img src="../../image/admin/index/menu.png" alt="模块一" title="模块一"></a></li>
    				<li><a href="#"><img src="../../image/admin/index/menu.png" alt="模块一" title="模块一"></a></li>
    			</ul>
    		</div>
    		<div class="line"></div>
    		<div class="user_info">
					<div class="user_left">
						<span class="user_name">当前用户</span><br />
						<div class="btn">注销</div>
					</div>
			</div>

    	</div>

    </div>
<!--<div data-options="region:'south',split:false" style="height:50px;">
    	<p style="text-align:center">Copyright&copy; 2015 douniwan </p>
    </div> -->
    <div class="left" data-options="region:'west',split:false" style="width:200px">
		  <div id="left_accordion" class="easyui-accordion"   data-options="fit:'true'">
        <div title="Title1">
            <ul class="navContent ">
                <li><a href="#">功能一</a></li>
                <li><a href="#">功能二</a></li>
                <li><a href="#">功能三</a></li>
                <li><a href="#">功能四</a></li>
            </ul>
        </div>
        <div title="Title2">
        </div>
        <div title="Title3">
        </div>
    </div>
    </div>
    <div data-options="region:'center'">
    	<div id="tt" class="easyui-tabs" data-options="fit:true" >

    		<div title="Tab1" data-options="closable:true,href:'../../view/admin/login.jsp'"  style="padding:20px;display:block;">

  		   </div>
    	<div title="Tab2" data-options="closable:true" style="overflow:auto;padding:20px;display:none;">
        	tab2
    	</div>
   		 <div title="Tab3" data-options="iconCls:'icon-reload',closable:true" style="padding:20px;display:none;">
        	tab3
    	</div>

</div>

    </div>
</body>
</html>
