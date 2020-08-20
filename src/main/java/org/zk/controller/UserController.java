package org.zk.controller;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.rpc.Protocol;
import com.joyuai.finance.distributor.api.IBillService;
import com.joyuai.finance.distributor.api.vo.Result;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhangkang on 2017/7/31.
 */
@RestController
public class UserController implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Reference
    IBillService billService;

    @RequestMapping("/")
    public String index(HttpServletResponse response) throws Exception{
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("content-disposition", "attachment;filename=bill.xlsx");
        Result<byte[]> result =  billService.exportBillItems(10301L);
        response.getOutputStream().write(result.getData());
        response.getOutputStream().flush();
        response.getOutputStream().close();
        return "hello";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
       // Object protocol = applicationContext.getBean("dubbo");
    }
}
