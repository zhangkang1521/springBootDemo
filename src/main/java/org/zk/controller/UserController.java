package org.zk.controller;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.file.remote.InputStreamCallback;
import org.springframework.integration.ftp.session.FtpRemoteFileTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhangkang on 2017/7/31.
 */
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    FtpRemoteFileTemplate ftpRemoteFileTemplate;

    @RequestMapping("/")
    public void index(HttpServletResponse response){
        ftpRemoteFileTemplate.get("/20180620165604076267_16637.pdf", new InputStreamCallback() {
            @Override
            public void doWithInputStream(InputStream inputStream) throws IOException {
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=aa.pdf");
                IOUtils.copy(inputStream, response.getOutputStream());
            }
        });
    }
}
