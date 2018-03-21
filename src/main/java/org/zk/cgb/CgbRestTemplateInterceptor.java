package org.zk.cgb;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by zhangkang on 2018/3/21.
 */
@Component
public class CgbRestTemplateInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        HttpHeaders headers = request.getHeaders();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        byte[] prefix = "cgb_data=<?xml version=\"1.0\" encoding = \"GBK\"?>".getBytes();
        byte[] newBody = new byte[body.length + prefix.length];
        int index = 0;
        for (int i=0; i < prefix.length; i++) {
            newBody[index++] = prefix[i];
        }
        for (int i=0; i < body.length; i++) {
            newBody[index++] = body[i];
        }
        // 保证请求继续被执行
        ClientHttpResponse response = execution.execute(request, newBody);
        response.getHeaders().setContentType(MediaType.APPLICATION_XML);
        return response;
    }
}
