<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<link rel="stylesheet" href="../css/common/IconsExtension/IconExtension.css" charset="utf-8" />
<style>

</style>
<div class="search_view" id="workflow_public_file_view">
<form class="search_form" action="<%=request.getContextPath()%>/workflow/public_file/query.action">
    <table class="search_table">
        <tr>
            <td>
                <label class="description_inline">文件名：
                    <input class="search_input" type="text" name="file"/>
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
<div id="workflow_public_file"  class="show_win">
    <form class="show_form" action="<%=request.getContextPath()%>/workflow/public_file" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id"/>
        <input type="hidden" name="create_date"/>
        <input type="hidden" name="update_date"/>
        <input type="hidden" name="status"/>
        <table class="show_table">
            <tr><td>
                <label class="">上传文件：</label>
                <input class="easyui-filebox" type="text" data-options="required:true,width:175,buttonText:'上传文件'" name="upload"/>
            </td></tr>


        </table>
    </form>
</div>
</div>

<script>
(function($){
var add_buttons = [
{iconCls:'icon-20130406125647919_easyicon_net_16', text:'确认',handler:function(e){commitAddForm(e);$('.search_button').click()}},
{iconCls:'icon-reload', text:'重置',handler:function(e){resetCommitForm(e)}}
];
var update_buttons = [
{iconCls:'icon-edit', text:'修改',handler:function(e){commitUpdateForm(e)}},
{iconCls:'icon-no', text:'关闭',handler:function(e){closeDivWindow('workflow_public_file')}}
];
var dategrid_toolbar= [
    {iconCls: '', handler: function(){}},
    '-',
    {iconCls: 'icon-20130406125647919_easyicon_net_16',text:'上传', handler: function(e){openDivWindow('上传公共文件',380,180,'icon-20130406125647919_easyicon_net_16',add_buttons,'workflow_public_file');}},
];
var table_colums = [[
    {field:"file",title:"文件名",width:350,align:"center",hidden:false,checkbox:false,formatter:function(value,row,index){if(value){return value.substring(value.indexOf("upload")+7);}}},
    {field:"id",title:"下载",width:150,align:"center",hidden:false,checkbox:false,formatter:function(value){if(value){return '<a  href=\'<%=request.getContextPath()%>/workflow/public_file/download.action?id='+value+'\'>下载</a>';}}},
    {field:"create_date",title:"创建时间",width:150,align:"center",hidden:false,checkbox:false},
    {field:"update_date",title:"修改时间",width:150,align:"center",hidden:false,checkbox:false},
    {field:"status",title:"状态",width:100,align:"center",hidden:false,checkbox:false,formatter:function(value,row,index)    {if(value=='NORMAL'){return '正常';}if(value=='CONGEAL'){return '冻结';}}}
]];
initSearchView(id="workflow_public_file_view",table_colums,"id",dategrid_toolbar);

})(jQuery);
</script>

