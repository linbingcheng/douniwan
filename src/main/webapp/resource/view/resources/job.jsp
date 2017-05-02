<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<link rel="stylesheet" href="../css/common/IconsExtension/IconExtension.css" charset="utf-8" />
<style>

</style>
<div class="search_view" id="resources_job_view">
<form class="search_form" action="<%=request.getContextPath()%>/resources/job/query.action">
    <table class="search_table">
        <tr>
            <td>
                <label class="description_inline">职务：
                    <input class="search_input" type="text" name="name"/>
                </label>
            </td>
            <td>
                <label class="description_inline">级别：
                    <input class="easyui-combobox search_input" data-options="
                    valueField: 'id',
                    textField: 'text',
                    width:'200px',
                    height:'26px',
                    url: '<%=request.getContextPath()%>/resource/data/level.json'"name="level"/>
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
<div id="resources_job"  class="show_win">
    <form class="show_form" action="<%=request.getContextPath()%>/resources/job" method="post">
        <input type="hidden" name="id"/>
        <input type="hidden" name="create_date"/>
        <input type="hidden" name="update_date"/>
        <input type="hidden" name="status"/>
        <table class="show_table">
            <tr><td>
                <label>职务： </label>
                <input class="easyui-textbox" type="text" data-options="width:175,height:25" name="name"/>
            </td></tr>
            <tr><td>
                <label>级别：</label>
                <input class="easyui-combobox" data-options="valueField:'id',textField:'text',height:25,width:175,url: '<%=request.getContextPath()%>/resource/data/level.json'"name="level"/>
            </td></tr>
            <tr><td>
                <label>数量：</label>
                <input class="easyui-textbox" type="text" data-options="width:175,height:25" name="maxNum"/>
            </td></tr>
            <tr><td>
                <label>描述：</label>
                <input class="easyui-textbox" type="text" data-options="width:175,height:25"  name="desc"/>
                </input>
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
{iconCls:'icon-no', text:'关闭',handler:function(e){closeDivWindow('resources_job')}}
];
var dategrid_toolbar= [
{iconCls: '', handler: function(){}},
'-',
{iconCls: 'icon-add',text:'新增', handler: function(e){openDivWindow('新增职务信息',330,300,'icon-add',add_buttons,'resources_job');}},
'-',
{iconCls: 'icon-edit',text:'编辑', handler: function(e){loadShowForm(e,'resources_job',update_buttons,350,340,'icon-edit','编辑职务信息')}},
'-',
{iconCls: 'icon-remove',text:'删除', handler: function(e){operateData(e,'delete','删除');}},
'-',
{iconCls: 'icon-lock_add',text:'冻结', handler: function(e){operateData(e,'congeal','冻结');}},
'-',
{iconCls: 'icon-lock_open',text:'解冻', handler: function(e){operateData(e,'thaw','解冻');}}
];
var table_colums = [[
    {field:"id",title:"id",width:150,align:"center",hidden:false,checkbox:true,formatter:function(value,row,index){if(value){return value}}},
    {field:"name",title:"职务名称",width:150,align:"center",hidden:false,checkbox:false},
    {field:"level",title:"职务级别",width:150,align:"center",hidden:false,checkbox:false},
    {field:"desc",title:"职务描述",width:150,align:"center",hidden:false,checkbox:false},
    {field:"create_date",title:"创建时间",width:150,align:"center",hidden:false,checkbox:false},
    {field:"update_date",title:"修改时间",width:150,align:"center",hidden:false,checkbox:false},
    {field:"status",title:"状态",width:100,align:"center",hidden:false,checkbox:false,formatter:function(value,row,index)    {if(value=='NORMAL'){return '正常';}if(value=='CONGEAL'){return '冻结';}}}
]];
initSearchView("resources_job_view",table_colums,"id",dategrid_toolbar);

})(jQuery);
</script>

