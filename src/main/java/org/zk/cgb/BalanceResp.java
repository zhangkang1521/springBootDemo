package org.zk.cgb;


/**
 * Created by zhangkang on 2018/3/15.
 */
public class BalanceResp {
    private String account;
    private String balSign;
    private String balance;
    private String freeze;
    private String lastDate;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBalSign() {
        return balSign;
    }

    public void setBalSign(String balSign) {
        this.balSign = balSign;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getFreeze() {
        return freeze;
    }

    public void setFreeze(String freeze) {
        this.freeze = freeze;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }
}
