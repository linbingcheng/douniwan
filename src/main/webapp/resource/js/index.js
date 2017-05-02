/**
 * Created by bingchenglin on 2017/4/6.
 */
(function($){
    $("#top-menu-list").children().each(function(index, domEle){
        $(domEle).children("a").children("img").click(function(){
            $("#top-menu-list").children().each(function (i, dom){
                $(dom).children("a").children("img").css("background-color","#E2EAF5");
                $("#" + $(dom).attr("value")).css("display","none");
            });
            $(domEle).children("a").children("img").css("background-color","#CEE0F6");
            $("#left_menu").panel("setTitle",$(domEle).children("a").children("img").attr("title"));
            $("#" + $(domEle).attr("value")).css("display","block");
        });
    });
    $("#left_menu").children().each(function(index, domEle) {
        $(domEle).children("li").each(function (li_index, li_domEle) {
            var href = $(li_domEle).children("a").attr("href");
            var title = $(li_domEle).attr("value");
            $(li_domEle).click(function () {
                if( $("#tabs").tabs("exists",title)){
                    $("#tabs").tabs("select",title);
                }else{
                    $("#tabs").tabs("add", {
                        title: title,
                        cls: "main_body",
                        href: href,
                        closable: true
                    });
                }
            });
        });
    });
})(jQuery);