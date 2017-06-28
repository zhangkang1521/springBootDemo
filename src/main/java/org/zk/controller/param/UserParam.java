package org.zk.controller.param;

import java.util.Date;

/**
 * Created by zhangkang on 2017/6/19.
 */
public class UserParam {

    private String username;
    private Date birthdayBegin;
    private Date birthdayEnd;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthdayBegin() {
        return birthdayBegin;
    }

    public void setBirthdayBegin(Date birthdayBegin) {
        this.birthdayBegin = birthdayBegin;
    }

    public Date getBirthdayEnd() {
        return birthdayEnd;
    }

    public void setBirthdayEnd(Date birthdayEnd) {
        this.birthdayEnd = birthdayEnd;
    }
}
