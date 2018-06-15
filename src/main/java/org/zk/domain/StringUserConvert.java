package org.zk.domain;

import org.springframework.core.convert.converter.Converter;

/**
 * Created by Administrator on 6/15/2018.
 */
public class StringUserConvert implements Converter<String, User> {

    public User convert(String source) {
        String[] arr = source.split(",");
        return new User(Integer.valueOf(arr[0]), arr[1]);
    }
}
