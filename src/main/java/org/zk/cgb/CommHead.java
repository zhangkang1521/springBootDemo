package org.zk.cgb;



import java.util.Date;

/**
 * 接口公共报文头
 * Created by zhangkang on 2018/3/14.
 */
public class CommHead {
    private String tranCode;
    private String cifMaster;
    private String entSeqNo;
    private String tranDate;
    private String tranTime;
    private String retCode;
    private String entUserId;
    private String password;

    public static CommHead buildRequestCommHead(String tranCode) {
        Date now = new Date();
        CommHead commHead = new CommHead();
        commHead.setTranCode(tranCode);
        commHead.setCifMaster("1000194691");
        commHead.setEntUserId("100001");
        commHead.setPassword("j4r6x7p3y7");
        commHead.setEntSeqNo(generateEntSeqNo(now));
        commHead.setTranDate("20180321");
        commHead.setTranTime("145800");
        return commHead;
    }

    private static String generateEntSeqNo(Date now) {
        return "201803210001";
    }

    public String getTranCode() {
        return tranCode;
    }

    public void setTranCode(String tranCode) {
        this.tranCode = tranCode;
    }

    public String getCifMaster() {
        return cifMaster;
    }

    public void setCifMaster(String cifMaster) {
        this.cifMaster = cifMaster;
    }

    public String getEntSeqNo() {
        return entSeqNo;
    }

    public void setEntSeqNo(String entSeqNo) {
        this.entSeqNo = entSeqNo;
    }

    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }

    public String getTranTime() {
        return tranTime;
    }

    public void setTranTime(String tranTime) {
        this.tranTime = tranTime;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getEntUserId() {
        return entUserId;
    }

    public void setEntUserId(String entUserId) {
        this.entUserId = entUserId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
