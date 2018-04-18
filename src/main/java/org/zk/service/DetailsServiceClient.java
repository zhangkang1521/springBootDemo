package org.zk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.zk.entity.Details;

/**
 * Created by zhangkang on 2018/4/18.
 */
@Service
public class DetailsServiceClient {
    @Autowired
    private  RestTemplate restTemplate;

    public Details getUserDetails(String name) {
        return restTemplate.getForObject("/{name}/details",
                Details.class, name);
    }
}
