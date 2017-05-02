<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<link rel="stylesheet" href="../css/common/IconsExtension/IconExtension.css" charset="utf-8" />
<style>

</style>
<div class="search_view" id="resources_property_view">
<form class="search_form" action="<%=request.getContextPath()%>/resources/property/query.action">
    <table class="search_table">
        <tr>
            <td>
                <label class="description_inline">资源名称：
                    <input class="search_input" type="text" name="name"/>
                </label>
            </td>
            <td>
                <label class="description_inline">资源类型：
                    <input class="easyui-combobox" data-options="valueField:'id',textField:'text',height:26,width:200,url: '<%=request.getContextPath()%>/resource/data/resouces_property_type.json'"name="type"/>
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
<div id="resources_property"  class="show_win">
    <form class="show_form" action="<%=request.getContextPath()%>/resources/property" method="post">
        <input type="hidden" name="id"/>
        <input type="hidden" name="create_date"/>
        <input type="hidden" name="update_date"/>
        <input type="hidden" name="status"/>
        <table class="show_table">
            <tr><td>
                <label class="">资源名称：</label>
                <input class="easyui-validatebox" type="text" data-options="required:true" name="name"/>
            </td></tr>
            <tr><td>
                <label class="">资源类型：</label>
                <input class="easyui-combobox" data-options="valueField:'id',textField:'text',height:25,width:175,url: '<%=request.getContextPath()%>/resource/data/resouces_property_type.json'"name="type"/>
            </td></tr>
            <tr><td>
                <label class="">资源数量：</label>
                <input class="easyui-validatebox" type="text" data-options="required:true" name="num"/>
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
{iconCls:'icon-no', text:'关闭',handler:function(e){closeDivWindow('resources_property')}}
];
var dategrid_toolbar= [
{iconCls: '', handler: function(){}},
'-',
{iconCls: 'icon-add',text:'新增', handler: function(e){openDivWindow('新增资源信息',350,240,'icon-add',add_buttons,'resources_property');}},
'-',
{iconCls: 'icon-edit',text:'编辑', handler: function(e){loadShowForm(e,'resources_property',update_buttons,350,240,'icon-edit','编辑资源信息')}},
'-',
{iconCls: 'icon-remove',text:'删除', handler: function(e){operateData(e,'delete','删除');}},
'-',
{iconCls: 'icon-lock_add',text:'冻结', handler: function(e){operateData(e,'congeal','冻结');}},
'-',
{iconCls: 'icon-lock_open',text:'解冻', handler: function(e){operateData(e,'thaw','解冻');}}
];
var table_colums = [[
    {field:"id",title:"id",width:150,align:"center",hidden:false,checkbox:true,formatter:function(value,row,index){if(value){return value}}},
    {field:"type",title:"资源类型",width:150,align:"center",hidden:false,checkbox:false},
    {field:"name",title:"资源名称",width:150,align:"center",hidden:false,checkbox:false},
    {field:"num",title:"资源数量",width:150,align:"center",hidden:false,checkbox:false},
    {field:"create_date",title:"创建时间",width:150,align:"center",hidden:false,checkbox:false},
    {field:"update_date",title:"修改时间",width:150,align:"center",hidden:false,checkbox:false},
    {field:"status",title:"状态",width:100,align:"center",hidden:false,checkbox:false,formatter:function(value,row,index)    {if(value=='NORMAL'){return '正常';}if(value=='CONGEAL'){return '冻结';}}}
]];
initSearchView(id="resources_property_view",table_colums,"id",dategrid_toolbar);

})(jQuery);
</script>

