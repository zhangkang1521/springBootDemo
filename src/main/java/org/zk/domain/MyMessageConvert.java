package org.zk.domain;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * RequestResponseBodyMethodProcessor
 * @RequestBody @ResponseBody
 * Created by Administrator on 4/24/2018.
 */
public class MyMessageConvert extends AbstractHttpMessageConverter<User> {

    public MyMessageConvert() {
        super(new MediaType("application", "user", Charset.forName("UTF-8")));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    protected User readInternal(Class<? extends User> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String tmp = StreamUtils.copyToString(inputMessage.getBody(), Charset.forName("UTF-8"));
        String[] arr = tmp.split(",");
        User demoObj = new User(Integer.valueOf(arr[0]), arr[1]);
        return demoObj;
    }

    @Override
    protected void writeInternal(User user, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        String str = user.getId() + "," + user.getUsername();
        outputMessage.getBody().write(str.getBytes(Charset.forName("UTF-8")));
    }
}
