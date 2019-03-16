package org.zk.vo;

import lombok.Data;

import java.util.List;

/**
 * Created by zhangkang on 2019/3/14.
 */
@Data
public class OpenAccountResp {
    private String rstCode;
    private String rstMess;
    private List<OpenAccountInfo> lists;

    @Data
    public static class OpenAccountInfo {
        private String vAcctName;
        private String vAcctNo;
    }
}


