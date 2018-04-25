package org.zk.domain;

/**
 * 服务器向客户端发送
 * Created by Administrator on 4/24/2018.
 */
public class MyResponse {
    private String responseMessage;

    public MyResponse() {
    }

    public MyResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
