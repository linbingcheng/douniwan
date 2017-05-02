<%@ page import="com.modelsystem.model.po.userinfo.User" %>
<%@ page import="com.modelsystem.model.po.resources.Employee" %>
<%@ page import="com.modelsystem.model.po.resources.Department" %>
<%@ page import="com.modelsystem.model.po.resources.Job" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String name = "";
    String sex = "男";
    String departmentName = "未选择部门";
    String jobName = "未选择职位";
    Employee employee = (Employee) session.getAttribute("employee");
    User user = (User)session.getAttribute("user");
    Department department =  (Department)session.getAttribute("department");
    Job job = (Job)session.getAttribute("job");
    if(employee!=null){
        name = employee.getName();
    }else {
        name = user.getUsername();
    }
    if(department != null){
        departmentName = department.getName();
    }
    if(job != null){
        jobName = job.getName();
    }
    if(employee.getSex().equals("1")||employee.getSex().equals("女")){
        sex = "女";
    }
%>
<h1>欢迎使用本系统！尊敬的<%=name%></h1>
<hr/>
<h2>基本信息</h2>
    <table class="show_table">
       <tr>
           <td>
               员工编号：
           </td>
           <td>
                <%=employee.getEmployeeCode()%>
           </td>
       </tr>
        <tr>
            <td>
                员工姓名：
            </td>
            <td>
                <%=employee.getName()%>
            </td>
        </tr>
        <tr>
            <td>
                个人照片:
            </td>
            <td><%
                    if(employee.getPhoto()!=null){
                %>
                <img src="<%=request.getContextPath()+"/"+employee.getPhoto().substring(employee.getPhoto().indexOf("upload"))%>">
                <%
                    }
                %>
            </td>
        </tr>
        <tr>
            <td>
                所属部门：
            </td>
            <td>
                <%=departmentName%>
            </td>
        </tr>
        <tr>
            <td>
                职务名称：
            </td>
            <td>
                <%=jobName%>
            </td>
        </tr>
        <tr>
            <td>
                员工性别：
            </td>
            <td>
                <%=sex%>
            </td>
        </tr>
        <tr>
            <td>
                员工生日：
            </td>
            <td>
                <%=employee.getBrithday()%>
            </td>
        </tr>
        <tr>
            <td>
                证件号码：
            </td>
            <td>
                <%=employee.getIdcard()%>
            </td>
        </tr>
        <tr>
            <td>
                电话号码：
            </td>
            <td>
                <%=employee.getPhone()%>
            </td>
        </tr>
        <tr>
            <td>
                电子邮箱：
            </td>
            <td>
                <%=employee.getE_mail()%>
            </td>
        </tr>
        <tr>
            <td>
                员工工资:
            </td>
            <td>
                <%=employee.getBaseSalary()%>
            </td>
        </tr>
        <tr>
            <td>
                入司时间:
            </td>
            <td>
                <%=employee.getJoinDate()%>
            </td>
        </tr>
        <tr>
            <td>
                修改密码:
            </td>
            <td>
                <a id="update_password_button" href="#" class="easyui-linkbutton" onclick="javascript:;">修改密码</a>
            </td>
        </tr>
    </table>
</form>

<div id="update_password"  class="show_win">
    <form id="update_password_form" class="show_form" action="<%=request.getContextPath()%>/user/updatePassword.action" method="post">
        <table class="show_table">
            <tr><td>
                <label class="">旧密码：</label>
                <input class="easyui-validatebox" type="password" data-options="required:true" id="password" name="password"/>
            </td></tr>
            <tr><td>
                <label class="">新密码：</label>
                <input class="easyui-validatebox" type="password" data-options="required:true" id="newpassword" name="newpassword"/>
            </td></tr>
            <tr><td>
                <label class="">重新输入新密码：</label>
                <input class="easyui-validatebox" type="password"  data-options="required:true" id="renewpassword" name="renewpassword"/>
            </td></tr>
        </table>
    </form>
</div>
</div>

<script>
    (function($){
        var update_buttons = [
            {iconCls:'icon-edit', text:'修改',handler:function(e){updatePassword();}},
            {iconCls:'icon-no', text:'关闭',handler:function(e){closeDivWindow('update_password');$("#update_password_form").form('reset')}}
        ];
        $("#update_password_button").click(function(){
            openDivWindow('修改密码',440,250,'icon-edit',update_buttons,'update_password');
        });
    })(jQuery);

    function updatePassword(){
        $('#update_password_form').form('submit', {
            onSubmit: function(){
                var isValid = $(this).form('validate');
                if($("#newpassword").val() != $("#renewpassword").val() ){
                    prompt(2,'两次输入的新密码不相等！');
                }
                return isValid;
            },
            success:function(data){
                var json = JSON.parse(data);
                if(json.success){
                    prompt(1,'修改密码成功！');
                    $("#update_password_form").form('reset')
                    closeDivWindow('update_password');
                }else{
                    prompt(2,'原密码输入错误！');
                }
            }
        });
    }
</script>