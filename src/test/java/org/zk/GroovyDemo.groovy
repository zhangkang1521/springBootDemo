package org.zk


/**
 * @author zhangkang
 * @create 2022/2/2 21:32
 */
class GroovyDemo {

    String convertJson(source) {
        return source + "xxx"
    }

    static void main(args) {
        def a = new GroovyDemo().convertJson("{}")
        println(a)
        println("hello, groovy!")
    }
}
