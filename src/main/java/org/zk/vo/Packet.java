package org.zk.vo;

import lombok.Data;

/**
 *
 * @param <T>
 */
@Data
public class Packet<T> {
    /**
     * 交易代码
     */
    private String tranCode;
    /**
     * 接入系统的编码，由浦发清结算平台发放。
     */
    private String systemCode;
    /**
     * 参与者代码，12位，由浦发清结算平台发放。
     */
    private String actorCode;
    /**
     * 参与者使用浦发清结算平台的虚拟用户名，由浦发清结算平台发放。
     */
    private String userCode;
    /**
     * 参与者使用浦发清结算平台的虚拟用户密码，由浦发清结算平台发放。
     */
    private String userPass;
    /**
     * [actorCode][yyyymmdd][12位顺序号],顺序号参与者当天不重复
     */
    private String packetNo;
    /**
     * 原报文编号
     */
    private String orionNo;
    /**
     * 版本号
     */
    private String verNo;
    /**
     * 创建时间
     */
    private String createdDate;
    /**
     * 报文体
     */
    private T body;

}
