package org.zk.vo;

import lombok.Data;

/**
 * 开户
 * Created by zhangkang on 2019/3/14.
 */
@Data
public class OpenAccount {
    /**
     * 业务编号
     */
    private String busiSerial;
    /**
     * 实账户户名
     */
    private String accName;
    /**
     * 实账户账号
     */
    private String accCode;
    /**
     * 开户数量
     */
    private String openNum;
    private String reserver1;
    private String reserver2;
    private String reserver3;
    private String reserver4;
    private String reserver5;
    /**
     * 业务时间戳
     */
    private String busiTime;
}
