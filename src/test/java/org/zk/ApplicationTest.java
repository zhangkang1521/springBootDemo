package org.zk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ObjectUtils;
import org.zk.enums.Modes;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by Administrator on 8/5/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    ConversionService conversionService;

    @Test
    public void testConvertFactory() {
        // StringToEnumConverterFactory
        Modes mode = conversionService.convert("ALPHA", Modes.class);
        Modes mode2 = conversionService.convert("ALPHA1", Modes.class);
    }

    @Test
    public void testGenericConvert() {
        BigDecimal a = conversionService.convert("123", BigDecimal.class);
    }

    @Test
    public void test() {
        Integer a = null;
        Integer b = null;
        System.out.println(ObjectUtils.nullSafeEquals(a, b));
        System.out.println(Objects.equals(a ,b));
    }
}
