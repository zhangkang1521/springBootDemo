package org.zk.controller.param;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Administrator on 12/17/2017.
 */
public class FileParam {
    private String username;
    private MultipartFile myFile;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MultipartFile getMyFile() {
        return myFile;
    }

    public void setMyFile(MultipartFile myFile) {
        this.myFile = myFile;
    }
}
