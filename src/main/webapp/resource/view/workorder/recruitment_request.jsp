<%@ page import="com.modelsystem.model.po.userinfo.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<link rel="stylesheet" href="../css/common/IconsExtension/IconExtension.css" charset="utf-8" />
<style>

</style>
<div class="search_view" id="workorder_recruitment_request_view">
    <%
        if(((User)session.getAttribute("user")).getUserType().equals("HR")){
    %>
<form class="search_form" action="<%=request.getContextPath()%>/workflow/recruitment_request/query.action">
    <%
        }
        else{
    %>
<form class="search_form" action="<%=request.getContextPath()%>/workflow/recruitment_request/query.action?managerId=<%=((User)session.getAttribute("user")).getId()%>">
    <%
        }
    %>
    <table class="search_table">
        <tr>
            <td>
                <label class="description_inline">上级同意：
                    <input class="easyui-combobox search_input" data-options="
                    valueField: 'id',
                    textField: 'text',
                    width:'200px',
                    height:'26px',
                    url: '<%=request.getContextPath()%>/resource/data/approveStates.json'"name="isManagerPass"/>
                </label>
            </td>
            <td>
                <label class="description_inline">人事同意：
                    <input class="easyui-combobox search_input" data-options="
                    valueField: 'id',
                    textField: 'text',
                    width:'200px',
                    height:'26px',
                    url: '<%=request.getContextPath()%>/resource/data/approveStates.json'"name="isHrPass"/>
                </label>
            </td>
            <td>
                <a href="#" class="easyui-linkbutton search_button" data-options="iconCls:'icon-search'" onclick="doSearch()">查询</a>
                <a href="#" class="easyui-linkbutton reset_button" data-options="iconCls:'icon-reload'" onclick="resetSearchForm()">重置</a>
            </td>
        </tr>
    </table>
</form>
<div class="search_datagrid">

</div>
</div>

<script>
(function($){
    var dategrid_toolbar= [
        {iconCls: '', handler: function(){}},
        '-',
        {iconCls: 'icon-accept',text:'同意', handler: function(e){operateData(e,'agree','同意');}},
        '-',
        {iconCls: 'icon-cancel',text:'驳回', handler: function(e){operateData(e,'overrule','驳回');}}
    ];
    var table_colums = [[
        {field:"id",title:"id",width:150,align:"center",hidden:false,checkbox:true,formatter:function(value,row,index){if(value){return value}}},
        {field:"jobId",title:"招聘职位",width:100,align:"center",hidden:false,checkbox:false,formatter:function(value){var name = '未选择职位';$.PostJson("<%=request.getContextPath()%>/resources/job/get.action",{id:value},function(isSuccess,json){if(isSuccess == 'success'){name = json.name;}else{name = '查询不到数据'}});return name;}},
        {field:"requestId",title:"员工编号",width:100,align:"center",hidden:false,checkbox:false,formatter:function(value){var employeeCode = "";$.PostJson("<%=request.getContextPath()%>/user/get.action",{id:value},function(isSuccess,json){employeeCode = json.employeeCode;});return employeeCode}},
        {field:"num",title:"数量",width:100,align:"center",hidden:false,checkbox:false},
        {field:"demand",title:"招聘要求",width:100,align:"center",hidden:false,checkbox:false},
        {field:"reason",title:"申请原因",width:150,align:"center",hidden:false,checkbox:false},
        {field:"isManagerPass",title:"经理审批",width:100,align:"center",hidden:false,checkbox:false},
        {field:"isHrPass",title:"人事审批",width:100,align:"center",hidden:false,checkbox:false},
        {field:"create_date",title:"创建时间",width:150,align:"center",hidden:false,checkbox:false},
        {field:"update_date",title:"修改时间",width:150,align:"center",hidden:false,checkbox:false},
    ]];
    initSearchView(id="workorder_recruitment_request_view",table_colums,"id",dategrid_toolbar);

})(jQuery);
</script>

