package org.zk.domain;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 6/16/2018.
 */
public class UserFormatter implements Formatter<User> {


    @Override
    public User parse(String text, Locale locale) throws ParseException {
        String[] arr = text.split(",");
        return new User(Integer.valueOf(arr[0]), arr[1]);
    }

    @Override
    public String print(User object, Locale locale) {
        return object.getId() + "," + object.getUsername();
    }
}
