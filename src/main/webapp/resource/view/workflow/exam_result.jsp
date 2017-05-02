<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<link rel="stylesheet" href="../css/common/IconsExtension/IconExtension.css" charset="utf-8" />
<style>

</style>
<div class="search_view" id="workflow_exam_result_view">
<form class="search_form" action="<%=request.getContextPath()%>/workflow/exam_result/query.action">
    <table class="search_table">
        <tr>
            <td>
                <label class="description_inline">员工编码：
                    <input class="search_input" type="text" name="employeeCode"/>
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
<div id="workflow_exam_result"  class="show_win">
    <form class="show_form" action="<%=request.getContextPath()%>/workflow/exam_result" method="post">
        <input type="hidden" name="id"/>
        <input type="hidden" name="create_date"/>
        <input type="hidden" name="update_date"/>
        <input type="hidden" name="status"/>
        <table class="show_table">
            <tr><td>
                <label class="">员工编号：</label>
                <input class="easyui-validatebox" type="text" data-options="required:true" name="employeeCode"/>
            </td></tr>
            <tr><td>
                <label class="">开始时间：</label>
                <input class="easyui-datebox" type="text" data-options="required:true,width:175" name="startDate"/>
            </td></tr>
            <tr><td>
                <label class="">结束时间：</label>
                <input class="easyui-datebox" type="text" data-options="required:true,width:175" name="endDate"/>
            </td></tr>
            <tr><td>
                <label class="">考核成绩：</label>
                <input class="easyui-validatebox" type="text" data-options="required:true" name="result"/>
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
{iconCls:'icon-no', text:'关闭',handler:function(e){closeDivWindow('workflow_exam_result')}}
];
var dategrid_toolbar= [
{iconCls: '', handler: function(){}},
'-',
{iconCls: 'icon-add',text:'新增', handler: function(e){openDivWindow('新增考核成绩',350,320,'icon-add',add_buttons,'workflow_exam_result');}},
'-',
{iconCls: 'icon-edit',text:'编辑', handler: function(e){loadShowForm(e,'workflow_exam_result',update_buttons,350,320,'icon-edit','编辑考核成绩')}},
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
    {field:"startDate",title:"开始时间",width:150,align:"center",hidden:false,checkbox:false},
    {field:"endDate",title:"结束时间",width:150,align:"center",hidden:false,checkbox:false},
    {field:"result",title:"考核成绩",width:150,align:"center",hidden:false,checkbox:false},
    {field:"create_date",title:"创建时间",width:150,align:"center",hidden:false,checkbox:false},
    {field:"update_date",title:"修改时间",width:150,align:"center",hidden:false,checkbox:false},
    {field:"status",title:"状态",width:100,align:"center",hidden:false,checkbox:false,formatter:function(value,row,index)    {if(value=='NORMAL'){return '正常';}if(value=='CONGEAL'){return '冻结';}}}
]];
initSearchView(id="workflow_exam_result_view",table_colums,"id",dategrid_toolbar);

})(jQuery);
</script>

