package com.modelsystem.ext.message;


import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Properties;

/**
 * Created by bingchenglin on 2017/4/21.
 */
public class MainUtil {

    public static final Logger logger = Logger.getLogger(MainUtil.class);

    public static final String MAIL_HOST = "smtp.qq.com";//邮箱的IP地址，填自己服务器的IP
    public static final int MAIL_PORT = 25;//邮箱开放的端口
    public static final String MAIL_USER = "494410479@qq.com"; //发送邮箱，自己填写
    public static final String MAIL_PASSWORD = "utbsxybpwbgrbibe"; //邮箱密码，自己填写
    public static final String MAIL_FROM = "data_services"; //邮件发送的别名，填英文就行，随便填
    public static final String DEFAULT_SSL_CLASS = "javax.net.ssl.SSLSocketFactory";
    public static final int DEFAULT_SSL_PORT = 465;

    public static void sendMail(String subject,String content,String[] tos,String fileName)  {
        try {
            Properties props = new Properties();
            // 指定SMTP服务器
            props.put("mail.smtp.host", MAIL_HOST);
            // 指定SMTP服务器端口
            props.put("mail.smtp.port", MAIL_PORT);
            // 指定是否需要SMTP验证
            props.put("mail.smtp.auth", Boolean.TRUE);
            Session mailSession = Session.getDefaultInstance(props);
            mailSession.setDebug(true);
            Message message = new MimeMessage(mailSession);
            // 发件人
            message.setFrom(new InternetAddress(MAIL_FROM));
            // 收件人
            InternetAddress[] toArray = new InternetAddress[tos.length];
            int index = 0;
            for (String to : tos) {
                toArray[index++] = new InternetAddress(to);
            }
            message.addRecipients(Message.RecipientType.TO, toArray);
            // 邮件主题
            message.setSubject(subject);
            // 邮件正文
            Multipart mp = new MimeMultipart();
            MimeBodyPart mbpText = new MimeBodyPart();
            mbpText.setText(content);
            mp.addBodyPart(mbpText);
            // 邮件附件
            if(StringUtils.isNotBlank(fileName)){
                MimeBodyPart mbp = new MimeBodyPart();
                FileDataSource fds = new FileDataSource(fileName);
                mbp.setDataHandler(new DataHandler(fds));
                mbp.setFileName(MimeUtility.encodeWord(fds.getName(), "UTF-8", null));
                mp.addBodyPart(mbp);
            }
            // Multipart加入到信件
            message.setContent(mp);
            // 设置信件头的发送日期
            message.setSentDate(new Date());
            message.saveChanges();
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(MAIL_HOST, MAIL_USER, MAIL_PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            logger.error("send email error:"+e.getMessage());
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        sendMail("资源来的，保存好","哈哈哈，被骗了吧！",new String[]{"978594789@qq.com"},null);
    }
}
