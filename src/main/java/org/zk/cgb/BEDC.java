package org.zk.cgb;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Created by zhangkang on 2018/3/14.
 */
public class BEDC<T> {
    @JacksonXmlProperty(localName="Message")
    private Message<T> message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
