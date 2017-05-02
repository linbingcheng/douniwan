<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<link rel="stylesheet" href="../css/common/IconsExtension/IconExtension.css" charset="utf-8" />
<style>

</style>
<div class="search_view" id="resources_employee_view">
<form class="search_form" action="<%=request.getContextPath()%>/resources/employee/query.action">
    <table class="search_table">
        <tr>
            <td>
                <label class="description_inline">员工编号：
                    <input class="search_input" type="text" name="employeeCode"/>
                </label>
            </td>
            <td>
                <label class="description_inline">员工姓名：
                    <input class="search_input" type="text" name="name"/>
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
<div id="resources_employee"  class="show_win">
    <form class="show_form" action="<%=request.getContextPath()%>/resources/employee" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id"/>
        <input type="hidden" name="create_date"/>
        <input type="hidden" name="update_date"/>
        <input type="hidden" name="status"/>
        <input type="hidden" value="在职" name="incumbency"/>
        <table class="show_table">
            <tr><td>
                <label class="">员工编号：</label>
                <input class="easyui-validatebox" type="text" data-options="required:true" name="employeeCode"/>
            </td><td>
                    <label class="">员工姓名：</label>
                    <input class="easyui-validatebox" type="text" data-options="required:true" name="name"/>
            </td></tr>
            <tr><td>
                <label class="">所属部门：</label>
                <input class="easyui-combotree" data-options="width:175,url:'<%=request.getContextPath()%>/resources/department/department_tree.action'" name="departmentId" />
            </td><td>
                <label class="">职务名称：</label>
                <input class="easyui-combobox" data-options="width:175,url:'<%=request.getContextPath()%>/resources/job/jobs.action',valueField:'id',textField:'text'" name="jobId"/>
            </td></tr>
            <tr><td>
                <label class="">员工性别：</label>
                <input class="easyui-combobox" data-options="width:175,url:'<%=request.getContextPath()%>/resource/data/sex.json',valueField:'id',textField:'text'" name="sex"/>
            </td><td>
                <label class="">员工生日：</label>
                <input class="easyui-datebox" type="text" data-options="required:true,width:175" name="brithday"/>
            </td></tr>
            <tr><td>
                <label class="">证件号码：</label>
                <input class="easyui-validatebox" type="text" data-options="required:true" name="idcard"/>
            </td><td>
                <label class="">电话号码：</label>
                <input class="easyui-validatebox" type="text" data-options="required:true" name="phone"/>
            </td></tr>
            <tr><td>
                <label class="">电子邮箱：</label>
                <input class="easyui-validatebox" type="text" data-options="required:true" name="e_mail"/>
            </td><td>
                <label class="">员工工资：</label>
                <input class="easyui-validatebox" type="text" data-options="required:true" name="baseSalary"/>
            </td></tr>
            <tr><td>
                <label class="">入司时间：</label>
                <input class="easyui-datebox" type="text" data-options="required:true,width:175" name="joinDate"/>
            </td><td>
                <label class="">头像文件：</label>
                <input class="easyui-filebox" type="text" data-options="required:true,width:175,buttonText:'员工头像'" name="upload"/>
            </td></tr>
        </table>
    </form>
</div>
</div>

<script>
(function($){
var add_buttons = [
{iconCls:'icon-add', text:'确认',handler:function(e){commitAddForm(e);$('.search_button').click()}},
{iconCls:'icon-reload', text:'重置',handler:function(e){resetCommitForm(e)}}
];
var update_buttons = [
{iconCls:'icon-edit', text:'修改',handler:function(e){commitUpdateForm(e)}},
{iconCls:'icon-no', text:'关闭',handler:function(e){closeDivWindow('resources_employee')}}
];
var dategrid_toolbar= [
{iconCls: '', handler: function(){}},
'-',
{iconCls: 'icon-add',text:'新增', handler: function(e){openDivWindow('新增员工信息',630,380,'icon-add',add_buttons,'resources_employee');}},
'-',
{iconCls: 'icon-edit',text:'编辑', handler: function(e){loadShowForm(e,'resources_employee',update_buttons,650,440,'icon-edit','编辑员工信息')}},
'-',
{iconCls: 'icon-remove',text:'删除', handler: function(e){operateData(e,'delete','删除');}},
'-',
{iconCls: 'icon-lock_add',text:'冻结', handler: function(e){operateData(e,'congeal','冻结');}},
'-',
{iconCls: 'icon-lock_open',text:'解冻', handler: function(e){operateData(e,'thaw','解冻');}}
];
var table_colums = [[
    {field:"id",title:"id",width:150,align:"center",hidden:false,checkbox:true,formatter:function(value,row,index){if(value){return value}}},
    {field:"employeeCode",title:"员工编号",width:150,align:"center",hidden:false,checkbox:false},
    {field:"name",title:"员工姓名",width:150,align:"center",hidden:false,checkbox:false},
    {field:"jobId",title:"员工职位",width:150,align:"center",hidden:false,checkbox:false,formatter:function(value){var name = '未选择部门';$.PostJson("<%=request.getContextPath()%>/resources/job/get.action",{id:value},function(isSuccess,json){if(json){name = json.name;}});return name;}},
    {field:"departmentId",title:"所属部门",width:150,align:"center",hidden:false,checkbox:false,formatter:function(value){var name = '未选择部门';$.PostJson("<%=request.getContextPath()%>/resources/department/get.action",{id:value},function(isSuccess,json){if(json){name = json.name;}});return name;}},
    {field:"phone",title:"电话号码",width:150,align:"center",hidden:false,checkbox:false},
    {field:"e_mail",title:"电子邮箱",width:150,align:"center",hidden:false,checkbox:false},
    {field:"create_date",title:"创建时间",width:150,align:"center",hidden:false,checkbox:false},
    {field:"update_date",title:"修改时间",width:150,align:"center",hidden:false,checkbox:false},
    {field:"incumbency",title:"在职状态",width:150,align:"center",hidden:false,checkbox:false},
    {field:"status",title:"状态",width:100,align:"center",hidden:false,checkbox:false,formatter:function(value,row,index)    {if(value=='NORMAL'){return '正常';}if(value=='CONGEAL'){return '冻结';}}}
]];
initSearchView(id="resources_employee_view",table_colums,"id",dategrid_toolbar);

})(jQuery);
</script>

