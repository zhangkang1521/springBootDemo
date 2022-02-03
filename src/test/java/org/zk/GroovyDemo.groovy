package org.zk

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject


/**
 * @author zhangkang
 * @create 2022/2/2 21:32
 */
class GroovyDemo {

    String convertJson(source) {
        JSONObject jsonObject = JSON.parse(source)
        Object aVal = jsonObject.get("a")

        JSONObject result = new JSONObject()

        JSONObject result1 = new JSONObject()
        result1.put("AA", aVal)
        result.put("A", result1)
        return result.toJSONString()
    }

    static void main(args) {
        def a = new GroovyDemo().convertJson("{\"a\": 123}")
        println(a)
        println("hello, groovy!")
    }
}
