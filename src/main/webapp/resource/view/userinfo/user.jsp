<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<link rel="stylesheet" href="../css/common/IconsExtension/IconExtension.css" charset="utf-8" />
<style>

</style>
<div class="search_view" id="user_view">
    <form class="search_form" action="<%=request.getContextPath()%>/user/query.action">
        <table class="search_table">
            <tr>
                <td>
                    <label class="description_inline">员工编号：
                        <input class="search_input" type="text" name="employeeCode"/>
                    </label>
                </td>
                <td>
                    <label class="description_inline">用户帐号：
                        <input class="search_input" type="text" name="username"/>
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
    <div id="user"  class="show_win">
        <form class="show_form" action="<%=request.getContextPath()%>/user" method="post">
            <input type="hidden" name="id"/>
            <input type="hidden" name="create_date"/>
            <input type="hidden" name="update_date"/>
            <input type="hidden" name="status"/>
            <table class="show_table">
                <tr><td>
                    <label>员工编号：<input class="easyui-validatebox" type="text" data-options="required:true" name="employeeCode"/> </label>
                </td></tr>
                <tr><td>
                    <label>用户帐号：<input class="easyui-validatebox" type="text" data-options="required:true" name="username"/></label>
                </td></tr>
                <tr><td>
                    <label>用户身份：
                        <input class="easyui-combobox search_input" data-options="
                    valueField: 'id',
                    textField: 'text',
                    width:'175',
                    height:'25',
                    url: '<%=request.getContextPath()%>/resource/data/user_type.json'"name="userType"/>
                    </label>
                </td></tr>
                <tr><td>
                    <label>密码：<input class="easyui-validatebox" type="password" data-options="required:true" name="password"/></label>
                </td></tr>
                <tr><td>
                    <label>邮箱：<input class="easyui-validatebox" type="text"  data-options="required:true,validType:'email'" name="e_mail"/></label>
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
            {iconCls:'icon-no', text:'关闭',handler:function(e){closeDivWindow('user')}}
        ];
        var dategrid_toolbar= [
            {iconCls: '', handler: function(){}},
            '-',
            {iconCls: 'icon-add',text:'新增', handler: function(e){openDivWindow('新增用户',350,340,'icon-add',add_buttons,'user');}},
            '-',
            {iconCls: 'icon-edit',text:'编辑', handler: function(e){loadShowForm(e,'user',update_buttons,350,340,'icon-edit','编辑信息')}},
            '-',
            {iconCls: 'icon-remove',text:'删除', handler: function(e){operateData(e,'delete','删除');}},
            '-',
            {iconCls: 'icon-lock_add',text:'冻结', handler: function(e){operateData(e,'congeal','冻结');}},
            '-',
            {iconCls: 'icon-lock_open',text:'解冻', handler: function(e){operateData(e,'thaw','解冻');}}
        ];
        var table_colums = [[
            {field:"id",align:"center",hidden:false,checkbox:true},
            {field:"employeeCode",title:"员工编号",width:150,align:"center",hidden:false,checkbox:false},
            {field:"username",title:"用户帐号",width:150,align:"center",hidden:false,checkbox:false},
            {field:"userType",title:"用户身份",width:150,align:"center",hidden:false,checkbox:false,formatter:function(value,row,index){if(value == 'systemadmin'){return '系统管理员';}else if(value == 'HR'){return '人事管理员';}else if(value == 'manager'){return '部门经理';}else{return '普通用户'}}},
            {field:"e_mail",title:"邮箱",width:200,align:"center",hidden:false,checkbox:false},
            {field:"create_date",title:"创建时间",width:150,align:"center",hidden:false,checkbox:false},
            {field:"update_date",title:"修改时间",width:150,align:"center",hidden:false,checkbox:false},
            {field:"status",title:"帐号状态",width:100,align:"center",hidden:false,checkbox:false,formatter:function(value,row,index)    {if(value=='NORMAL'){return '正常';}if(value=='CONGEAL'){return '冻结';}}}
        ]];
        initSearchView("user_view",table_colums,"id",dategrid_toolbar);

    })(jQuery);
</script>

