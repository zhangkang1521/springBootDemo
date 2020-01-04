package org.zk.mybatis;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Import(MyMapperScanRegister.class)
public @interface MyMapperScan {
    String[] value() default {};
}
