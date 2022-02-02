package org.zk;

import groovy.lang.GroovyClassLoader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 8/5/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Test
    public void test1() throws Exception {
        String scriptContent = "class GroovyDemo { " +
               "String convertJson(source) {" +
                "        return source + \"xxx\" " +
                "    }" +
                "}";

        Class clazz = new GroovyClassLoader().parseClass(scriptContent);
        Object obj = clazz.newInstance();
        Method[] methods = clazz.getDeclaredMethods();
        Method method = clazz.getDeclaredMethod("convertJson", Object.class);
        Object result = method.invoke(obj, "{}");
        System.out.println(result);
    }
}
