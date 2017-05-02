<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<link rel="stylesheet" href="../css/common/IconsExtension/IconExtension.css" charset="utf-8" />
<style>

</style>
<div class="search_view" id="resources_department_view">
<form class="search_form" action="<%=request.getContextPath()%>/resources/department/query.action">
    <table class="search_table">
        <tr>
            <td>
                <label class="description_inline">部门名称：
                    <input class="search_input" type="text" name="name"/>
                </label>
            </td>
            <td>
                <label class="description_inline">部门经理：
                    <input class="search_input" type="text" name="manager"/>
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
<div id="resources_department"  class="show_win">
    <form class="show_form" action="<%=request.getContextPath()%>/resources/department" method="post">
        <input type="hidden" name="id"/>
        <input type="hidden" name="create_date"/>
        <input type="hidden" name="update_date"/>
        <input type="hidden" name="status"/>
        <table class="show_table">
            <tr><td>
                <label class="">部门名称：</label>
                <input class="easyui-validatebox" type="text" data-options="required:true,width:175,height:25" name="name"/>
            </td></tr>

            <tr><td>
                <label class="">上级部门：</label>
                <input  class="easyui-combotree" data-options="width:175,url:'<%=request.getContextPath()%>/resources/department/department_tree.action'" name="parentId" />
            </td></tr>

            <tr><td>
                <label class="">部门经理：</label>
                <input class="easyui-validatebox" type="text" data-options="width:175,height:25" name="manager"/>
            </td></tr>

            <tr><td>
                <label class="">部门描述：</label>
                <input class="easyui-textbox" type="text" data-options="width:175,height:50" name="descri"/>
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
{iconCls:'icon-no', text:'关闭',handler:function(e){closeDivWindow('resources_department')}}
];
var dategrid_toolbar= [
{iconCls: '', handler: function(){}},
'-',
{iconCls: 'icon-add',text:'新增', handler: function(e){openDivWindow('新增部门信息',350,340,'icon-add',add_buttons,'resources_department');}},
'-',
{iconCls: 'icon-edit',text:'编辑', handler: function(e){loadShowForm(e,'resources_department',update_buttons,350,340,'icon-edit','编辑部门信息')}},
'-',
{iconCls: 'icon-remove',text:'删除', handler: function(e){operateData(e,'delete','删除');}},
'-',
{iconCls: 'icon-lock_add',text:'冻结', handler: function(e){operateData(e,'congeal','冻结');}},
'-',
{iconCls: 'icon-lock_open',text:'解冻', handler: function(e){operateData(e,'thaw','解冻');}}
];
var table_colums = [[
    {field:"id",title:"部门编码",width:100,align:"center",hidden:false},
    {field:"name",title:"部门名称",width:150,align:"center",hidden:false,checkbox:false},
    {field:"manager",title:"部门经理",width:150,align:"center",hidden:false,checkbox:false},
    {field:"descri",title:"部门描述",width:150,align:"center",hidden:false,checkbox:false},
    {field:"parentId",title:"上级部门",width:150,align:"center",hidden:false,checkbox:false,formatter:function(value){var name = '公司';$.PostJson("<%=request.getContextPath()%>/resources/department/get.action",{id:value},function(isSuccess,json){if(isSuccess == 'success'){name = json.name;}else{name = '系统'}});return name;}},
    {field:"create_date",title:"创建时间",width:150,align:"center",hidden:false,checkbox:false},
    {field:"update_date",title:"修改时间",width:150,align:"center",hidden:false,checkbox:false},
    {field:"status",title:"状态",width:100,align:"center",hidden:false,checkbox:false,formatter:function(value,row,index)    {if(value=='NORMAL'){return '正常';}if(value=='CONGEAL'){return '冻结';}}}
]];
initSearchView(id="resources_department_view",table_colums,"id",dategrid_toolbar);

})(jQuery);
</script>

