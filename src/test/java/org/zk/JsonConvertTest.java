package org.zk;

import org.junit.Test;
import org.zk.jsonconvert.JsonConvert;

/**
 * @author zhangkang
 * @date 2022/8/18 11:06
 */
public class JsonConvertTest {

    @Test
    public void convert() throws Exception {
        String source = "{\n" +
                "    \"tableName\":\"trade\",\n" +
                "    \"after\":{\n" +
                "        \"trade_id\":\"123\",\n" +
                "        \"pay_action\":1\n" +
                "    }\n" +
                "}";
        String target = new JsonConvert().convert(source);
        System.out.println(target);
    }

}
