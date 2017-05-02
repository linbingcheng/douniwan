<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <title>人力资源管理系统登录</title>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
      <link rel="stylesheet" href="../../js/common/themes/bootstrap/easyui.css"charset="utf-8" />
      <link rel="stylesheet" href="../../js/common/themes/icon.css" charset="utf-8" />
  <style>
	.fontStyle{
		font: 12px/1.5 tahoma,arial,'Hiragino Sans GB',\5b8b\4f53,sans-serif;
	}
</style>
</head>

<body style="background:rgb(1,106,169);overflow:hidden;">
<form method="post" id="login_form">
<table width="100%" height="100%" border="0" cellpadding="0" style="margin-top:10%;" cellspacing="0">
  <tr>
    <td><table width="962" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="235" background="../../image/admin/login/login_01.gif">&nbsp;</td>
      </tr>
      <tr>
        <td height="53"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="394" height="53" background="../../image/admin/login/login_02.gif" style="color:#fff;">&nbsp;<div style="position:relative;left:432px;top:28px;font: 12px/1.5 tahoma,arial,'Hiragino Sans GB',\5b8b\4f53,sans-serif;"></div></td>
            <td width="206" background="../../image/admin/login/login_03.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="16%" height="25"><div align="right"><span class="fontStyle">用户</span></div></td>
                <td width="57%" height="25"><div align="center">
                  <input type="text" name="username" style="width:105px; height:17px; background-color:#292929; border:solid 1px #7dbad7;text-indent:4px;font-size:12px; color:#6cd0ff">
                </div></td>
                <td width="27%" height="25">&nbsp;</td>
              </tr>
              <tr>
                <td height="25"><div align="right"><span class="fontStyle">密码</span></div></td>
                <td height="25"><div align="center">
                  <input type="password" name="password" style="width:105px; height:17px; background-color:#292929; border:solid 1px #7dbad7;text-indent:4px;font-size:12px; color:#6cd0ff">
                </div></td>
                <td height="25">
                    <div align="left"><input type="button" onclick="login()"  style="border:0;cursor:pointer;background:url(../../image/admin/login/login_04.gif);width:49px;height:18px;" /></div></td>
              </tr>
            </table></td>
            <td width="362" background="../../image/admin/login/login_05.gif">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="213" background="../../image/admin/login/login_06.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
<script type="text/javascript" src="../../js/common/jquery.min.js"  charset="utf-8"></script>
<script type="text/javascript" src="../../js/common/jquery.easyui.min.js"  charset="utf-8"></script>
<script type="text/javascript" src="../../js/common/easyloader.js" charset="utf-8"></script>
<script type="text/javascript"src="../../js/common/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
<script type="text/javascript" src="../../js/common/jquery.form.js"  charset="utf-8"></script>
<script type="text/javascript" src="../../js/common.js" charset="utf-8"></script>
<script>
    function login(){
        $.PostJson("<%=request.getContextPath()%>/user/login.action",$("#login_form").formSerialize(),function(isSuccess, json){
            if(isSuccess){
                if(json.success){
                    window.location.href = "<%=request.getContextPath()%>/resource/view/index.jsp";
                }else{
                    prompt(2,json.message);
                }
            }else{
                prompt(0,"登录失败");
            }
        });
    }
</script>
</body>
</html>
  