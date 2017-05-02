/**
 * Created by bingchenglin on 2017/4/5.
 */

(function($){
    $.extend({
        PostJson: function(url, datas , callback) {
            $.ajax({
                url: url,
                type: "POST",
                data : datas,//+"&_=" + (new Date()).getTime(),
                cache: false,
                dataType: "json",
                async:false,//False，才可将值传递到外部
                beforeSend: function (xhr) {
                    xhr.overrideMimeType("text/plain; charset=utf-8");
                },
                success: function(json) {
                    callback("success", json);
                },
                error: function(e) {
                    if(e.responseText && e.responseText == '{state:timeout}'){
                        prompt(0,"连接超时！");
                    }else{
                        callback("error", null);
                    }
                }
            });
        },
        PostXml: function(url, datas , callback) {
            $.ajax({
                url: url,
                type: "POST",
                data : datas,//+"&_=" + (new Date()).getTime(),
                cache: false,
                dataType: "xml",
                beforeSend: function (xhr) {
                    xhr.overrideMimeType("text/plain; charset=utf-8");
                },
                success: function(xml) {
                    callback("success", xml);
                },
                error: function(e) {
                    callback("error", null);
                }
            });
        },
        getUrlVars: function(){
            var vars = [], hash;
            var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
            for(var i = 0; i < hashes.length; i++)
            {
                hash = hashes[i].split('=');
                vars.push(hash[0]);
                vars[hash[0]] = hash[1];
            }
            return vars;
        },
        getUrlVar: function(name){
            return $.getUrlVars()[name];
        },
        loadForm: function(form, url, param, preLoad, afterLoad) {
        if (preLoad) {
            preLoad(form)
        }
        $.ajax({
            type: "post",
            dataType: "json",
            data: param,
            url: url,
            success: function(data) {
                if (data) {
                    var dataKey = new Date().getTime() + Math.floor(Math.random() * 1000);
                    $(form).attr("dataKey", dataKey);
                    $("input,select,textarea,div[type=txt]", $(form)).each(function(i, ele) {
                        var type = $(ele).attr("type");
                        var name = $(ele).attr("name");
                        var names=(name+"").split(".");
                        var value=data;
                        for(var i=0;i<names.length;i++){
                            value=value[names[i]];
                        }
                        if (!value || !name) {
                            if (value == undefined || ("" + value).length == 0) {
                                return
                            }
                        }
                        if (type == "radio" || type == "checkbox") {
                            $(form).find("input[type='radio'][name='" + name + "'][value=" + value + "]").attr("checked", true);
                            return
                        }
                        if (type == "txt") {
                            $(ele).text(value);
                        }
                        if ($(ele).hasClass("combo-value")) {
                            var target = $(form).find("input[comboname='" + name + "']");
                            if (target.hasClass("combobox-f")) {
                                target.combobox("setValue", value);
                                var nametext = target.parent().attr("nametext");
                                if (nametext && data[nametext]) {
                                    target.combobox("setText", data[nametext])
                                }
                            } else {
                                if (target.hasClass("jquery-datebox")) {
                                    target.datebox("setValue", value)
                                } else {
                                    if (target.hasClass("combotree-f")) {
                                            if (value.indexOf(",") > 0) {
                                                target.combotree("setValues", value.split(","))
                                            } else {
                                                target.combotree("setValue", value)
                                            }
                                        }
                                    }
                                }
                                return
                            }
                            $(ele).val(value)
                        });
                    }
                    if (afterLoad) {
                        afterLoad(data)
                    }
                }
            });
        },
        clearForm: function(form) {
            $(form).find("input:visible,textarea:visible,select:visible").each(function(i, ele) {
                $(ele).val(null)
            });
        },
        serializeObject:function($form) {
            var o = {};
            var a = $form.serializeArray();
            $.each(a, function() {
                if (o[this.name]) {
                    if (!o[this.name].push) {
                        o[this.name] = [o[this.name]];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            });
            return o;
        }
    });
})(jQuery);

function stopEvent(e){
    var e=arguments.callee.caller.arguments[0]||event; //兼容火狐必须加上这一句
    if(e && e.stopPropagation ){
        e.stopPropagation();
    }else{
        window.event.cancelBubble = true;
    }
}

function openWindow(title, url, width, height,iconCls, winId){
    var $win = $(winId ? "#" + winId : "#win");
    width = width ? width : 650;
    height = height ? height : 400;
    $win.window({
        title: title,
        href: url,
        width: width,
        height: height,
        iconCls:iconCls,
        modal: true,
        shadow: true,
        collapsible:true
    }).window('close');
    $win.show();
    $win.window('open');

}

function openDivWindow(title,width,height,iconCls,buttons,winId,callback){
    var $win = $(winId ? "#" + winId : "#win");
    var $body = $(document.body);
    width = width ? width : 600;
    height = height ? height : 400;
    $win.dialog({
        title: title,
        width: width,
        height: height,
        iconCls:iconCls,
        buttons:buttons,
        modal: true,
        shadow: true,
        top:$body.height()* 0.5 - height * 0.5,
        left:$body.width() * 0.5 - width * 0.5,
        minimizable:true,
        maximizable:true,
        collapsible:true
    }).dialog('close');
    $win.show();
    $win.dialog({
        onBeforeClose:function(){
            if (typeof callback == 'function') {
                callback();
                return true;
            }
        }
    });
    $win.dialog('open');

}

function closeWindow(id){
    $(id ? "#" + id : "#win").window("close");
}

function closeDivWindow(id){
    $(id ? "#" + id : "#win").dialog("close");
}

/*
 等待，成功，失败，警告等提示方法
 state：-1代表loading ， 0代表失败，1代表成功，2代表警告，3代表问题。
 //loading 需要手动调用loadEnd()关闭
 msg：提示信息。
 */
function prompt(state,msg,callback1){
    if(state==-1){loading();}
    var stateIcon = 'info';
    if(state==0){stateIcon = 'error';}
    else if(state==2){stateIcon = 'warning';}
    else if(state==3){stateIcon = 'question';}
    if(typeof callback1 == 'function'){
        return $.messager.alert('消息提示框',msg,stateIcon,callback1);
    }
    return $.messager.alert('消息提示框',msg,stateIcon);
}

/*
 * 弹出确定框，第一个参数为提示信息，第二个参数为确定之后调用的函数，第二个参数为取消按钮调用的函数，不需要使用回调函数的时候可以不传
 * */
function comfirm(msg,cbSure,cbRefuse){
    var stateMsg = msg ;
    stateIcon = 'warning';
    return $.messager.confirm('确认对话框', msg, function(r){
        if (r){
            if(typeof cbSure == 'function'){
                cbSure();
            }
        }else{
            if(typeof cbRefuse == 'function'){
                cbRefuse();
            }
        }
    });
}

function loading(){
    $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
}

function loadEnd(){
    $(".datagrid-mask").remove();
    $(".datagrid-mask-msg").remove();
}

function addTab(title,cls,url,closable,iconCls,id){
    $(id ? "#" + id : "#tabs").tabs("add", {
        title: title,
        cls: cls,
        href: url,
        iconCls:iconCls,
        closable: closable
    });
}

function createFrame(url) {
    var iframeName = new Date().getTime();
    return '<iframe scrolling="auto" name="' + iframeName + '"  frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>'
}

function createAjaxURL(url,$form) {
    var params = $form.formSerialize().split("&");
    var result = ""
    for(var i = 0;i < params.length;i++){
        if(params[i].charAt(params[i].length - 1) != "="){
            result += params[i] + "&"
        }
    }
    return url +"?" + result +"_=" + (new Date()).getTime();
}

function createDataGrid(url,queryParams,columns,idField,emptyMsg,pageSize,toolbar,$datagrid){
    var myview = $.extend({},$.fn.datagrid.defaults.view,{
        onAfterRender:function(target){
            $.fn.datagrid.defaults.view.onAfterRender.call(this,target);
            var opts = $(target).datagrid('options');
            var vc = $(target).datagrid('getPanel').children('div.datagrid-view');
            vc.children('div.datagrid-empty').remove();
            if (!$(target).datagrid('getRows').length){
                var d = $("<div class='datagrid-empty' style='width: "+opts.width+"px;background-color: gruy;' ></div>").html(opts.emptyMsg || '暂无数据，请更改搜索条件！').appendTo(vc);
                d.css({
                    position:'absolute',
                    left:0,
                    top:50,
                    width:'100%',
                    textAlign:'center'
                });
            }
        }
    });
    $datagrid.datagrid({
        url : url,
        queryParams : queryParams,
        rownumbers : true,
        loadMsg : "加载信息中，请稍候...",
        pagination : true,
        singleSelect:true,
        striped:true,
        idField : idField,
        view : myview,
        emptyMsg : emptyMsg,
        columns:columns,
        pageSize:pageSize,
        toolbar: toolbar
    });
}
function initSearchView(showView,columns,idField,toolbar){
    var $showView = $("#"+showView)
    var $search_form = $showView.find(".search_form");
    var $datagrid = $search_form.next(".search_datagrid");
    var shearchUrl = $search_form[0].action;
    createDataGrid(shearchUrl,null,columns,idField,null,20,toolbar,$datagrid);
}

function doSearch(){
    var event = event ? event : window.event;
    var search_button = event.srcElement ? event.srcElement : event.target;
    var $search_form = $(search_button).parents(".search_form");
    var $datagrid = $(search_button).parents('.search_view').find('.search_datagrid');
    var queryParams = $.serializeObject($search_form);
    $datagrid.datagrid('load',queryParams);
}

function resetSearchForm(){
    var event = event ? event : window.event;
    var reset_button = event.srcElement ? event.srcElement : event.target;
    var $search_form = $(reset_button).parents(".search_form");
    $.clearForm($search_form[0]);
    doSearch();
}

function commitAddForm(event){
    var event = event ? event : window.event;
    var confirm_button = event.srcElement ? event.srcElement : event.target;
    var $form= $(confirm_button).parents('.window').find('.show_form');
    var action = $form[0].action;
    var $search_form = $form.parents('.search_view').find(".search_form");
    var $datagrid = $form.parents('.search_view').find('.search_datagrid');
    var queryParams = $.serializeObject($search_form);
    if($form.form('validate')){
        $.messager.progress();
        $form.form('submit', {
            url:action +'/save.action',
            success: function(data){
                var json = JSON.parse(data);
                $.messager.progress('close');
                if(json.success){
                    prompt(1,'保存成功！');
                    $.clearForm($form[0]);
                    $form.parent('.show_win').dialog("close");
                    $datagrid.datagrid('load',queryParams);
                }else{
                    prompt(2,json.message);
                }
                $form[0].action = action;
            }
        });
    }else{
        prompt(0,'数据校验失败！请检查');
    }
}

function resetCommitForm(event){
    var event = event ? event : window.event;
    var reset_button = event.srcElement ? event.srcElement : event.target;
    var $form= $(reset_button).parents('.window').find('.show_form');
    $.clearForm($form[0]);
}

function loadShowForm(event,showWin,buttons,width,hegith,iconCls,title){
    var event = event ? event : window.event;
    var button = event.srcElement ? event.srcElement : event.target;
    var $datagrid = $(button).parents('.search_view').find('.search_datagrid');
    var $searchform = $(button).parents('.search_view').find('.search_form');
    var idField = $datagrid.datagrid('options').idField;
    var $showWin = $(showWin?'#'+showWin:'.show_win');
    if($datagrid.datagrid('getSelected') == null){
        prompt(2,"请选择要查看的数据");
        return;
    }
    openDivWindow(title,width,hegith,iconCls,buttons,showWin);
    var searchId = $datagrid.datagrid('getSelected')[idField];
    var url = $searchform[0].action.replace('query','get')+'?'+idField+'='+searchId;
    var $form = $showWin.find('.show_form');
    $form.form('disableValidation');
    $.loadForm($form[0],url);
    $form.form('load',url);
    $showWin.dialog({
        onBeforeClose:function(){
            $.clearForm($form[0]);
            $form.form('enableValidation');
        }
    })
}


function commitUpdateForm(event){
    var event = event ? event : window.event;
    var confirm_button = event.srcElement ? event.srcElement : event.target;
    var $form= $(confirm_button).parents('.window').find('.show_form');
    var action = $form[0].action;
    var $search_form = $form.parents('.search_view').find(".search_form");
    var $datagrid = $form.parents('.search_view').find('.search_datagrid');
    var queryParams = $.serializeObject($search_form);
    if($form.form('validate')){
        $.messager.progress();
        $form.form('submit', {
            url:action +'/update.action',
            success: function(data){
                $.messager.progress('close');
                if(JSON.parse(data).success){
                    prompt(1,'修改成功！');
                    $.clearForm($form[0]);
                    $form.parent('.show_win').dialog("close");
                    $datagrid.datagrid('load',queryParams);
                }else{
                    prompt(2,'修改失败！');
                }
                $form[0].action = action;
            }
        });
    }else{
        prompt(0,'数据校验失败！请检查');
    }
}

function operateData(event,operate,description){
    var event = event ? event : window.event;
    var button = event.srcElement ? event.srcElement : event.target;
    var $datagrid = $(button).parents('.search_view').find('.search_datagrid');
    var $searchform = $(button).parents('.search_view').find('.search_form');
    var datas = $datagrid.datagrid('getSelected');
    var url = $searchform[0].action.replace('query',operate);
    if(datas == null){
        prompt(2,'请选择要'+description+'的数据');
        return;
    }
    comfirm('确认要'+description+'这条数据?',function(){
        $.PostJson(url,datas,function(isSuccess,data){
            if(isSuccess == 'success'){
                if(data.success){
                    prompt(1,description+'成功！');
                }else{
                    prompt(0,description+'失败！');
                }
            }else{
                prompt(0,description+'失败！');
            }
        });
    })
}

//function deleteData(event){
//    var event = event ? event : window.event;
//    var button = event.srcElement ? event.srcElement : event.target;
//    var $datagrid = $(button).parents('.search_view').find('.search_datagrid');
//    var $searchform = $(button).parents('.search_view').find('.search_form');
//    var idField = $datagrid.datagrid('options').idField;
//    if($datagrid.datagrid('getSelected') == null){
//        prompt(2,'请选择要删除的数据');
//        return;
//    }
//    var deleteId = $datagrid.datagrid('getSelected')[idField];
//    var url = $searchform[0].action.replace('query','delete');
//    comfirm('确认要删除这条数据?',function(){
//        $.PostJson(url,idField+'='+deleteId,function(isSuccess,data){
//            if(isSuccess == 'success'){
//                if(data.success){
//                    prompt(1,"删除成功！");
//                }else{
//                    prompt(0,"删除失败！");
//                }
//            }else{
//                prompt(0,"删除失败！");
//            }
//        });
//    })
//}
