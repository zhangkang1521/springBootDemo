package org.zk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by Administrator on 8/5/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidatorTest {

    // LocalValidatorFactoryBean
    @Autowired
    private Validator validator;

    // ValidatorImpl
    private static final Validator validator2 = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void test1() {
        Foo foo = new Foo();
        Set<ConstraintViolation<Object>> validate = validator.validate(foo);
        validator2.validate(foo);
    }
}
