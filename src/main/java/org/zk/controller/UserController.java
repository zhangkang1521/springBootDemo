//package org.zk.controller;
//
//import com.ctrip.framework.apollo.spring.annotation.SpringValueProcessor;
//import com.ctrip.framework.apollo.spring.property.AutoUpdateConfigChangeListener;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.zk.config.DemoConfig;
//
///**
// * Created by zhangkang on 2017/7/31.
// */
//@RestController
//public class UserController {
//
//    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
//
//    /**
//     * 自动更新配置，@Value注解的才更新
//     * @see SpringValueProcessor#postProcessBeforeInitialization(java.lang.Object, java.lang.String)
//     * @see AutoUpdateConfigChangeListener#onChange(com.ctrip.framework.apollo.model.ConfigChangeEvent)
//     */
//    @Value("${timeout}")
//    private Long timeout;
//
//    @Autowired
//    private DemoConfig demoConfig;
//
//    @RequestMapping("/")
//    public String index(){
//        return timeout + ":" + demoConfig.getAa();
//    }
//}
