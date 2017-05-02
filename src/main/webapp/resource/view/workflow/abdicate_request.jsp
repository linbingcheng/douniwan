<%@ page import="com.modelsystem.model.po.userinfo.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<link rel="stylesheet" href="../css/common/IconsExtension/IconExtension.css" charset="utf-8" />
<style>

</style>
<div class="search_view" id="workflow_abdicate_request_view">
<form class="search_form" action="<%=request.getContextPath()%>/workflow/abdicate_request/query.action?requestId=<%=((User)session.getAttribute("user")).getId()%>">
    <table class="search_table">
        <tr>
            <td>
                <label class="description_inline">上级同意：
                    <input class="easyui-combobox search_input" data-options="
                    valueField: 'id',
                    textField: 'text',
                    width:'200px',
                    height:'26px',
                    url: '<%=request.getContextPath()%>/resource/data/approveStates.json'"name="level"/>
                </label>
            </td>
            <td>
                <label class="description_inline">人事同意：
                    <input class="easyui-combobox search_input" data-options="
                    valueField: 'id',
                    textField: 'text',
                    width:'200px',
                    height:'26px',
                    url: '<%=request.getContextPath()%>/resource/data/approveStates.json'"name="level"/>
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
<div id="workflow_abdicate_request"  class="show_win">
    <form class="show_form" action="<%=request.getContextPath()%>/workflow/abdicate_request" method="post">
        <input type="hidden" name="id"/>
        <input type="hidden" name="create_date"/>
        <input type="hidden" name="update_date"/>
        <input type="hidden" name="status"/>
        <input type="hidden" name="requestId" value="<%=((User)session.getAttribute("user")).getId()%>"/>
        <input type="hidden" name="isManagerPass" value="待审批"/>
        <input type="hidden" name="isHrPass" value="待审批"/>
        <table class="show_table">
            <tr><td>
                <label class="">离职原因：</label>
                <input class="easyui-textbox" type="text" data-options="width:175,height:75"  name="reason"/>
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
{iconCls:'icon-no', text:'关闭',handler:function(e){closeDivWindow('workflow_abdicate_request')}}
];
var dategrid_toolbar= [
{iconCls: '', handler: function(){}},
'-',
{iconCls: 'icon-add',text:'新增', handler: function(e){openDivWindow('离职申请',350,240,'icon-add',add_buttons,'workflow_abdicate_request');}},
'-',
{iconCls: 'icon-remove',text:'删除', handler: function(e){operateData(e,'delete','删除');}},
'-',
];
var table_colums = [[
{field:"id",title:"id",width:150,align:"center",hidden:false,checkbox:true,formatter:function(value,row,index){if(value){return value}}},
{field:"reason",title:"原因",width:150,align:"center",hidden:false,checkbox:false},
{field:"isManagerPass",title:"经理审批",width:100,align:"center",hidden:false,checkbox:false},
{field:"isHrPass",title:"人事审批",width:100,align:"center",hidden:false,checkbox:false},
{field:"create_date",title:"创建时间",width:150,align:"center",hidden:false,checkbox:false},
{field:"update_date",title:"修改时间",width:150,align:"center",hidden:false,checkbox:false},
{field:"status",title:"状态",width:100,align:"center",hidden:false,checkbox:false,formatter:function(value,row,index)    {if(value=='NORMAL'){return '正常';}if(value=='CONGEAL'){return '冻结';}}}
]];
initSearchView(id="workflow_abdicate_request_view",table_colums,"id",dategrid_toolbar);

})(jQuery);
</script>

