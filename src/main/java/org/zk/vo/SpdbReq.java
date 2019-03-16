package org.zk.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 浦发银行请求体
 * Created by zhangkang on 2019/3/13.
 */
@Data
public class SpdbReq<T> {
    /**
     *
     */
    private Packet<T> packet;
    /**
     * 报文防篡改串，用SHA-256算法将1.1-1.10项内容摘要成64位字符串
     */
    private String checkSum;

    public SpdbReq() {

    }

    public SpdbReq(Packet<T> packet) {
        this.packet = packet;
        // TODO
        this.checkSum = "xxx";
    }
}
