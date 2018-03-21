package org.zk.cgb;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Created by zhangkang on 2018/3/14.
 */

public class Message<T> {
    private CommHead commHead;
    @JacksonXmlProperty(localName="Body")
    private T body;

    public CommHead getCommHead() {
        return commHead;
    }

    public void setCommHead(CommHead commHead) {
        this.commHead = commHead;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
