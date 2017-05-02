package com.modelsystem.ext.message;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bingchenglin on 2017/4/14.
 */
public class MailTest {

    private static final String USERNAME = "13642308539@163.com";
    private static final String PASSWORD = "*****************";
    private static final String FROM = "13642308539@163.com";
    private static final String TO = "494410479@qq.com";


    public static void main(String[] args) {
        MailTest test = new MailTest();
        test.htmlMail();
    }

    //普通文本文件发送
    public void textMail(){
        TextMail mail = new TextMail();
        //选择发送邮箱 163的等各种常用邮箱的host和port
        //163邮箱开启客户端授权码作为密码，而不是邮箱密码
        mail.setServerHost(Mail.SMTP_163_HOST);
        mail.setServerPort(Mail.SMTP_163_PORT);
        //设置发送的邮箱的密码和帐号
        mail.setUsername(USERNAME);
        mail.setPassword(PASSWORD);
        mail.setValidate(true);
        mail.setFrom(FROM);
        mail.setSubject("邮件标题");
        mail.setContent("邮件内容");
        //目标邮箱
        mail.setTo(TO);
        //群发，以下同
        //mail.setTo(new String[]{TO, TO, TO});
        mail.send();
    }

    //文本邮件带附件
    public void textMailWithAttachment(){
        TextMail mail = new TextMail();
        mail.setServerHost(Mail.SMTP_163_HOST);
        mail.setServerPort(Mail.SMTP_163_PORT);
        mail.setUsername(USERNAME);
        mail.setPassword(PASSWORD);
        mail.setValidate(true);
        mail.setFrom(FROM);
        mail.setSubject("邮件标题");
        mail.setContent("邮件内容");
        mail.setTo(TO);
        //设置附件
        mail.setAttachment("src/main/java/com/modelsystem/ext/message/YHCXY.jpg");
        //发送
        mail.sendWithAttachment();
    }

    //普通的html邮件
    public void htmlMail(){
        HtmlMail mail = new HtmlMail();
        mail.setServerHost(Mail.SMTP_163_HOST);
        mail.setServerPort(Mail.SMTP_163_PORT);
        mail.setUsername(USERNAME);
        mail.setPassword(PASSWORD);
        mail.setValidate(true);
        mail.setFrom(FROM);
        mail.setSubject("看到这个标题没");
        mail.setContent("<a style='color:red' title='我喜欢你很久了！'>你看下内容</a>");
        mail.setTo(TO);
        mail.send();
    }

    //带附件的html邮件
    public void htmlMailWithAttachment(){
        HtmlMail mail = new HtmlMail();
        mail.setServerHost(Mail.SMTP_163_HOST);
        mail.setServerPort(Mail.SMTP_163_PORT);
        mail.setUsername(USERNAME);
        mail.setPassword(PASSWORD);
        mail.setValidate(true);
        mail.setFrom(FROM);
        mail.setSubject("看到这个标题没");
        mail.setContent("<span style='color:red'>这是老司机发车群</span>");
        mail.setTo(TO);
        mail.setAttachment("src/main/java/com/modelsystem/ext/message/YHCXY.jpg");
        mail.sendWithAttachment();
    }

    //具备模版的html邮件
    public void htmlTemplateMail(){
        HtmlMail mail = new HtmlMail();
        mail.setServerHost(Mail.SMTP_163_HOST);
        mail.setServerPort(Mail.SMTP_163_PORT);
        mail.setUsername(USERNAME);
        mail.setPassword(PASSWORD);
        mail.setValidate(true);
        mail.setFrom(FROM);
        mail.setSubject("邮件标题");
        Map<String, String> value = new HashMap<String, String>();
        value.put("articleLink", "http://www.baidu.com");
        value.put("articleTitle", "Javamail 邮件发送");
        value.put("articleSummary", "使用 javamail 发送 Email 的一个简单的例子，首先需要导入 javamail.jar 包...");
        mail.setTemplatePath("src/main/java/com/modelsystem/ext/message/mailTemplate.txt");
        mail.setTemplateMap(value);
        mail.setTo(TO);
        mail.sendTemplate();
    }

    //发送html模版加附件
    public void htmlTemplateMailWithAttachment(){
        HtmlMail mail = new HtmlMail();
        mail.setServerHost(Mail.SMTP_163_HOST);
        mail.setServerPort(Mail.SMTP_163_PORT);
        mail.setUsername(USERNAME);
        mail.setPassword(PASSWORD);
        mail.setValidate(true);
        mail.setFrom(FROM);
        mail.setSubject("邮件标题");
        Map<String, String> value = new HashMap<String, String>();
        value.put("articleLink", "http://www.baidu.com");
        value.put("articleTitle", "Javamail 邮件发送");
        value.put("articleSummary", "使用 javamail 发送 Email 的一个简单的例子，首先需要导入 javamail.jar 包...");
        mail.setTemplatePath("src/main/java/com/modelsystem/ext/message/mailTemplate.txt");
        mail.setTemplateMap(value);
        mail.setTo(TO);
        mail.setAttachment("src/main/java/com/modelsystem/ext/message/YHCXY.jpg");
        mail.sendTemplateWithAttachment();
    }

}
