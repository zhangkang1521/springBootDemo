package org.zk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by zhangkang on 2017/7/31.
 */
@RestController
@RequestMapping("/primeng")
public class PrimeNGController {

    private static final Logger logger = LoggerFactory.getLogger(PrimeNGController.class);

    @PostMapping("/calendar")
    public String calendar(@RequestBody String value){
        logger.info("data:{}", value);
        return "ok";
    }
}
