package com.modelsystem.ext.message;

import org.apache.log4j.Logger;

import javax.mail.Message;
import javax.mail.Transport;
import java.util.Iterator;

/**
 * Created by bingchenglin on 2017/4/14.
 */

public class TextMail  extends Mail {

    private static final Logger log = Logger.getLogger(TextMail.class);

    public TextMail() {
    }

    /**
     * 发送文本邮件
     */
    public void send() {
        try {
            Message message = this.buildMessage();
            message.setText(this.content);
            message.saveChanges();

            Iterator iterator = this.to.iterator();
            while(iterator.hasNext()) {
                String to = (String)iterator.next();
                this.addTo(message, to);
                Transport.send(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("发送文本邮件失败",e);
        }
    }
}
