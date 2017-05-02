<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<link rel="stylesheet" href="../css/common/IconsExtension/IconExtension.css" charset="utf-8" />
<style>

</style>
<div class="search_view">
<form class="search_form" action="<%=request.getContextPath()%>/demo/employee/query.action">
    <table class="search_table">
        <tr>
            <td>
                <label class="description_inline">Lable：
                    <input class="search_input" type="text" name=""/>
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
<div id="demo_employee"  class="show_win">
    <form class="show_form" action="<%=request.getContextPath()%>/demo/employee" method="post">
        <input type="hidden" name=""/>

        <table class="show_table">
            <tr><td>
                <label class="">Lable：<input class="easyui-validatebox" type="text" data-options="required:true" name=""/> </label>
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
{iconCls:'icon-no', text:'关闭',handler:function(e){closeDivWindow('demo_employee')}}
];
var dategrid_toolbar= [
{iconCls: '', handler: function(){}},
'-',
{iconCls: 'icon-add',text:'新增', handler: function(e){openDivWindow('新增demo_employee',350,340,'icon-add',add_buttons,'demo_employee');}},
'-',
{iconCls: 'icon-edit',text:'编辑', handler: function(e){loadShowForm(e,'demo_employee',update_buttons,350,340,'icon-edit','编辑demo_employee信息')}},
'-',
{iconCls: 'icon-remove',text:'删除', handler: function(e){operateData(e,'delete','删除');}},
'-',
{iconCls: 'icon-lock_add',text:'冻结', handler: function(e){operateData(e,'congeal','冻结');}},
'-',
{iconCls: 'icon-lock_open',text:'解冻', handler: function(e){operateData(e,'thaw','解冻');}}
];
var table_colums = [[
{field:"id",title:"id",width:150,align:"center",hidden:false,checkbox:false,formatter:function(value,row,index){if(value){return value}}
]];
initSearchView(table_colums,"id",dategrid_toolbar);

})(jQuery);
</script>

