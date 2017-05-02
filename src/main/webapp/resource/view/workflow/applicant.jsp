<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<link rel="stylesheet" href="../css/common/IconsExtension/IconExtension.css" charset="utf-8" />
<style>
    .show_email{
        display: none;
        overflow-y:auto;
        text-align:center;
        vertical-align: middle;
    }
    .show_email .email_form{
        text-align:center;
        width:auto;
        padding: 10px 30px
    }
    .show_email  .email_form .email_table{
        height: auto;
    }
    #email_content{
        resize:none;
        overflow:scroll;
        overflow-x:hidden;
        overflow-y:hidden;
    }
</style>
<div class="search_view" id="workflow_applicant_view">
<form class="search_form" action="<%=request.getContextPath()%>/workflow/applicant/query.action">
    <table class="search_table">
        <tr>
            <td>
                <label class="">适配职位：</label>
                <input class="easyui-combobox" data-options="width:200,height:26,url:'<%=request.getContextPath()%>/resources/job/jobs.action',valueField:'id',textField:'text'" name="applyJobId"/>
            </td>
            <td>
                <label class="">人才姓名：</label>
                <input class="search_input" type="text" name="name"/>
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
<div id="workflow_applicant"  class="show_win">
    <form class="show_form" action="<%=request.getContextPath()%>/workflow/applicant" method="post">
        <input type="hidden" name="id"/>
        <input type="hidden" name="create_date"/>
        <input type="hidden" name="update_date"/>
        <input type="hidden" name="status"/>
        <table class="show_table">
            <tr><td>
                <label class="">适配职位：</label>
                <input class="easyui-combobox" data-options="width:175,height:25,url:'<%=request.getContextPath()%>/resources/job/jobs.action',valueField:'id',textField:'text'" name="applyJobId"/>
            </td></tr>
            <tr><td>
                <label class="">应聘者姓名：</label>
                <input class="easyui-validatebox" type="text" data-options="required:true" name="name"/>
            </td></tr>
            <tr><td>
                <label class="">电话号码：</label>
                <input class="easyui-validatebox" type="text" data-options="required:true" name="phone"/>
            </td></tr>
            <tr><td>
                <label class="">电子邮箱：</label>
                <input class="easyui-validatebox" type="text"  data-options="required:true,validType:'email'" name="e_mail"/>
            </td></tr>
        </table>
    </form>
</div>
</div>

<div id="email_win" class="show_email">
    <form class="email_form" action="<%=request.getContextPath()%>/workflow/applicant/sendEmail.action" method="post">
        <input id="email_to" name="email_to" type="hidden">
        <table class="email_table">
            <tr>
                <td><h4>邮件标题:</h4></td>
            </tr>
            <tr>
                <td>
                    <input id="email_title" class="easyui-textbox" data-options="width:610,height:28,required:true" name="email_title">
                </td>
            </tr>
            <tr>
                <td><h4>邮件内容:</h4></td>
            </tr>
            <tr>
                <td>
                    <textarea id="email_content" cols="85" rows="17" name="email_content"></textarea>
                </td>
            </tr>
        </table>
    </form>
</div>
<script>
(function($){
var add_buttons = [
{iconCls:'icon-add', text:'确认',handler:function(e){commitAddForm(e);$('.search_button').click()}},
{iconCls:'icon-reload', text:'重置',handler:function(e){resetCommitForm(e)}}
];
var update_buttons = [
{iconCls:'icon-edit', text:'修改',handler:function(e){commitUpdateForm(e)}},
{iconCls:'icon-no', text:'关闭',handler:function(e){closeDivWindow('workflow_applicant')}}
];
var dategrid_toolbar= [
    {iconCls: '', handler: function(){}},
    '-',
    {iconCls: 'icon-add',text:'新增', handler: function(e){openDivWindow('新增应聘者信息',380,340,'icon-add',add_buttons,'workflow_applicant');}},
    '-',
    {iconCls: 'icon-edit',text:'编辑', handler: function(e){loadShowForm(e,'workflow_applicant',update_buttons,380,340,'icon-edit','编辑应聘者信息');}},
    '-',
    {iconCls: 'icon-remove',text:'删除', handler: function(e){operateData(e,'delete','删除');}},
    '-',
    {iconCls: 'icon-lock_add',text:'冻结', handler: function(e){operateData(e,'congeal','冻结');}},
    '-',
    {iconCls: 'icon-lock_open',text:'解冻', handler: function(e){operateData(e,'thaw','解冻');}},
    '-',
    {iconCls: 'icon-email',text:'发邮件', handler: function(e){ openEmailWin(e);}}
];
var table_colums = [[
    {field:"id",title:"id",width:150,align:"center",hidden:false,checkbox:true,formatter:function(value,row,index){if(value){return value}}},
    {field:"applyJobId",title:"适配职位",width:100,align:"center",hidden:false,checkbox:false,formatter:function(value){var name = '未选择职位';$.PostJson("<%=request.getContextPath()%>/resources/job/get.action",{id:value},function(isSuccess,json){if(isSuccess == 'success'){name = json.name;}else{name = '查询不到数据'}});return name;}},
    {field:"name",title:"应聘者姓名",width:150,align:"center",hidden:false,checkbox:false},
    {field:"phone",title:"电话号码",width:150,align:"center",hidden:false,checkbox:false},
    {field:"e_mail",title:"电子邮箱",width:150,align:"center",hidden:false,checkbox:false},
    {field:"create_date",title:"创建时间",width:150,align:"center",hidden:false,checkbox:false},
    {field:"update_date",title:"修改时间",width:150,align:"center",hidden:false,checkbox:false},
    {field:"status",title:"状态",width:100,align:"center",hidden:false,checkbox:false,formatter:function(value,row,index)    {if(value=='NORMAL'){return '正常';}if(value=='CONGEAL'){return '冻结';}}}
]];
initSearchView(id="workflow_applicant_view",table_colums,"id",dategrid_toolbar);

})(jQuery);
    var email_buttons = [
        {iconCls:'icon-email_go', text:'发送邮件',handler:function(e){
            $('.email_form').form('submit', {
                onSubmit: function(){
                var isValid = $(this).form('validate');
                if (!isValid){
                    prompt(2,'数据校验不通过！');
                    return false;
                }
                    return true;
                },
                success: function(){
                    prompt(1,'发送完成！');
                    closeDivWindow('email_win');
                    ;$(".email_form").form('reset');
                }
            });
        }},
        {iconCls:'icon-no', text:'取消',handler:function(e){closeDivWindow('email_win');$(".email_form").form('reset');}}
    ];
    function openEmailWin(event){
        var event = event ? event : window.event;
        var button = event.srcElement ? event.srcElement : event.target;
        var $datagrid = $(button).parents('.search_view').find('.search_datagrid');
        var $searchform = $(button).parents('.search_view').find('.search_form');
        var datas = $datagrid.datagrid('getSelected');
        var url = $searchform[0].action;
        if(datas == null){
            prompt(2,'请选择接受邮件的应聘人员');
            return;
        }
        openDivWindow('发送邮件',700,500,'icon-email',email_buttons,'email_win');
        $("#email_to").val(datas['e_mail']);
    }
</script>

