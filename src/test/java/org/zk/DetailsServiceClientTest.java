package org.zk;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.zk.entity.Details;
import org.zk.service.DetailsServiceClient;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Created by zhangkang on 2018/4/18.
 */
@RunWith(SpringRunner.class)
@RestClientTest(DetailsServiceClient.class)
public class DetailsServiceClientTest {

    @Autowired
    private DetailsServiceClient client;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        Details details = new Details();
        details.setId(100);
        String detailsString = objectMapper.writeValueAsString(details);

        this.server.expect(requestTo("/john/details"))
                .andRespond(withSuccess(detailsString, MediaType.APPLICATION_JSON));
    }


    @Test
    public void whenCallingGetUserDetails_thenClientMakesCorrectCall()
            throws Exception {

        Details details = this.client.getUserDetails("john");
        System.out.println(details.getId());

    }
}
