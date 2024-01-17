package org.zk.comm;

import lombok.Data;

/**
 * @author zhangkang
 * @date 2024/1/16 16:44
 */
@Data
public class BusinessException extends RuntimeException {
    private String code;
    private String msg;

    public BusinessException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
