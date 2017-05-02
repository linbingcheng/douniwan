package com.modelsystem.ext.message;

import org.apache.log4j.Logger;

import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by bingchenglin on 2017/4/14.
 */
public class HtmlMail extends Mail {

    private static final Logger log = Logger.getLogger(HtmlMail.class);

    private String templatePath;
    private Map<String, String> templateMap;
    private static final String CONVENTION_PREFIX = "\\$\\{";
    private static final String CONVENTION_SUFFIX = "\\}";

    public HtmlMail() {
    }

    /**
     * @param templatePath  html模版位置
     * @param templateMap  替换的值
     */
    public HtmlMail(String templatePath, Map<String, String> templateMap) {
        this.templatePath = templatePath;
        this.templateMap = templateMap;
    }

    /**
     * 基本的发送邮件
     */
    public void send() {
        try {
            Message message = this.buildMessage();
            MimeMultipart mimeMultipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();

            messageBodyPart.setContent(this.content, "text/html; charset=gbk");
            mimeMultipart.addBodyPart(messageBodyPart);
            message.setContent(mimeMultipart);
            message.saveChanges();

            Iterator iterator = this.to.iterator();
            while(iterator.hasNext()) {
                String toaddress = (String)iterator.next();
                this.addTo(message, toaddress);
                Transport.send(message);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("发送html邮件失败",e);
        }
    }

    /**
     * 发送邮件
     */
    public void sendTemplate() {
        try {
            this.content = this.parseTemplate(this.templateMap, this.templatePath);
        } catch (Exception e) {
            log.error("发送邮件失败",e);
        }
        this.send();
    }

    /**
     * 发送邮件（带附件的邮件）
     */
    public void sendTemplateWithAttachment() {
        try {
            this.content = this.parseTemplate(this.templateMap, this.templatePath);
        } catch (Exception e) {
            log.error("发送邮件和附件失败",e);
        }

        this.sendWithAttachment();
    }

    /**
     * 根据模版内容生产邮件
     * @param valueMap  替换模版中的值
     * @param filepath  模版路径
     * @return
     * @throws IOException
     */
    private String parseTemplate(Map<String, String> valueMap, String filepath) throws IOException {
        String content = this.loadResource(filepath);
        String key;
        Iterator iterator = valueMap.keySet().iterator();
        while (iterator.hasNext()){
            key = (String) iterator.next();
            content = content.replaceAll(CONVENTION_PREFIX + key + CONVENTION_SUFFIX, (String)valueMap.get(key));
        }
        return content;
    }

    private String loadResource(String filepath) throws IOException {
        byte[] bytes = new byte[65536];
        StringBuilder content = new StringBuilder();
        FileInputStream input = new FileInputStream(filepath);

        int read;
        while((read = input.read(bytes)) != -1) {
            content.append(new String(bytes, 0, read));
        }
        input.close();
        return content.toString();
    }

    public String getTemplatePath() {
        return this.templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public Map<String, String> getTemplateMap() {
        return this.templateMap;
    }

    public void setTemplateMap(Map<String, String> templateMap) {
        this.templateMap = templateMap;
    }
}
