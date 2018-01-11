package org.zk.commons;

/**
 * 返回消息体
 * Created by zhangkang on 2017/6/11.
 */
public class Result<T> {
    private boolean success;
    private String message;
    private T body;

    /**
     * 成功消息
     */
    public static Result SUCCESS = new Result(true, "成功");

    public Result() {}

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Result(boolean success, String message, T body) {
        this(success, message);
        this.body = body;
    }

    /**
     * 失败消息
     * @param message
     * @return
     */
    public static Result fail(String message) {
        return new Result(false, message);
    }

    /**
     * 成功消息
     * @param data
     * @param <T>
     * @return
     */
    public static <T>Result success(T data) {
        return new Result<T>(true, "操作成功", data);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
