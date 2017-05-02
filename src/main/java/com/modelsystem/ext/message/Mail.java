package com.modelsystem.ext.message;


import org.apache.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

/**
 * Created by bingchenglin on 2017/4/14.
 */
public abstract class Mail {

    private static final Logger log = Logger.getLogger(Mail.class);

    protected int serverPort;
    protected String serverHost;
    protected String from;
    protected List<String> to;
    protected String username;
    protected String password;
    protected String subject;
    protected String content;
    protected boolean validate;
    protected boolean usedSSL = true;
    protected List<String> attachmentList;
    public static final int SMTP_QQ_PORT = 25;
    public static final String SMTP_QQ_HOST = "smtp.qq.com";
    public static final int SMTP_126_PORT = 25;
    public static final String SMTP_126_HOST = "smtp.126.com";
    public static final int SMTP_163_PORT = 25;
    public static final String SMTP_163_HOST = "smtp.163.com";
    public static final int SMTP_YEAH_PORT = 25;
    public static final String SMTP_YEAH_HOST = "smtp.yeah.net";
    public static final int SMTP_SINA_PORT = 25;
    public static final String SMTP_SINA_HOST = "smtp.sina.com";
    public static final int SMTP_YAHOO_PORT = 25;
    public static final String SMTP_YAHOO_HOST = "smtp.mail.yahoo.cn";

    public static final String DEFAULT_SSL_CLASS = "javax.net.ssl.SSLSocketFactory";
    public static final int DEFAULT_SSL_PORT = 465;

    public Mail() {
    }

    public abstract void send();

    public void sendWithAttachment() {
        try {
            MimeMultipart mimeMultipart = new MimeMultipart();
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(this.content, "text/html; charset=gbk");
            mimeMultipart.addBodyPart(mimeBodyPart);
            this.putAttachments(mimeMultipart, this.attachmentList);
            Message mailMessage = this.buildMessage();
            mailMessage.setContent(mimeMultipart);
            Iterator iterator = this.to.iterator();
            while (iterator.hasNext()) {
                String toaddress = (String) iterator.next();
                this.addTo(mailMessage, toaddress);
                Transport.send(mailMessage);
            }
        } catch (Exception e) {
            this.log.error(e.getMessage(), e);
        }
    }

    protected Message buildMessage() throws Exception {
        MailAuthenticator authenticator = this.getMailAuthenticator(this.validate, this.username, this.password);
        Session mailSession = Session.getDefaultInstance(this.getMailProperties(), authenticator);
        MimeMessage mailMessage = new MimeMessage(mailSession);
        InternetAddress fromAddress = new InternetAddress(this.from);
        mailMessage.setFrom(fromAddress);
        mailMessage.setSubject(this.subject);
        mailMessage.setSentDate(new Date());
        return mailMessage;
    }

    protected void addTo(Message message, String to) throws Exception {
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
    }

    private void putAttachments(Multipart parts, List<String> filepath) throws Exception {
        if (filepath != null && filepath.size() != 0) {
            Iterator iterator = filepath.iterator();

            while (iterator.hasNext()) {
                String path = (String) iterator.next();
                MimeBodyPart part = new MimeBodyPart();
                FileDataSource fileSource = new FileDataSource(path);
                part.setDataHandler(new DataHandler(fileSource));
                part.setFileName(MimeUtility.encodeWord(fileSource.getName(), "GBK", (String) null));
                parts.addBodyPart(part);
            }

        } else {
            throw new Exception("没有可发送的有效附件");
        }
    }

    private MailAuthenticator getMailAuthenticator(boolean validate, String username, String password) {
        MailAuthenticator authenticator = null;
        if (validate) {
            authenticator = new MailAuthenticator(username, password);
        }

        return authenticator;
    }

    protected Properties getMailProperties() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", this.serverHost);
        prop.put("mail.smtp.port", String.valueOf(this.serverPort));
        prop.put("mail.smtp.auth", String.valueOf(this.validate));
        if(usedSSL){
            prop.put("mail.smtp.socketFactory.class", DEFAULT_SSL_CLASS);
            prop.put("mail.smtp.socketFactory.port", DEFAULT_SSL_PORT);
        }
        return prop;
    }

    public String getServerHost() {
        return this.serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public int getServerPort() {
        return this.serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValidate() {
        return this.validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getTo() {
        return this.to;
    }

    public void setTo(List<String> toList) {
        this.to = toList;
    }

    public void setTo(String[] toArray) {
        this.to = Arrays.asList(toArray);
    }

    public void setTo(String to) {
        this.to = Arrays.asList(new String[]{to});
    }

    public List<String> getAttachmentList() {
        return this.attachmentList;
    }

    public void setAttachment(List<String> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public void setAttachment(String[] attachmentList) {
        this.attachmentList = Arrays.asList(attachmentList);
    }

    public void setAttachment(String attachment) {
        this.attachmentList = Arrays.asList(new String[]{attachment});
    }

    public boolean isUsedSSL() {
        return usedSSL;
    }

    public void setUsedSSL(boolean usedSSL) {
        this.usedSSL = usedSSL;
    }

    private class MailAuthenticator extends Authenticator {
        private String username;
        private String password;

        public MailAuthenticator(String username, String password) {
            this.username = username;
            this.password = password;
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(this.username, this.password);
        }
    }


}
