package org.zk.comm;

import lombok.Data;

/**
 * @author zhangkang
 * @date 2024/1/16 16:34
 */
@Data
public class Response<T> {

    private String code;

    private String msg;

    private T body;

    public static <T> Response success(T body) {
        Response<T> response = new Response<>();
        response.setCode("200");
        response.setMsg("success");
        response.setBody(body);
        return response;
    }

    public static <T> Response fail(String code, String msg) {
        Response<T> response = new Response<>();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }
}
