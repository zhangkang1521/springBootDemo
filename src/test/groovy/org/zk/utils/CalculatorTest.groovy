package org.zk.utils

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

/**
 * where: 以表格的形式提供测试数据集合
 * when: 触发行为，比如调用指定方法或函数
 * then: 做出断言表达式
 * expect: 期望的行为，when-then的精简版
 * given: mock单测中指定mock数据
 * thrown: 如果在when方法中抛出了异常，则在这个子句中会捕获到异常并返回
 * def setup() {} ：每个测试运行前的启动方法
 * def cleanup() {} : 每个测试运行后的清理方法
 * def setupSpec() {} : 第一个测试运行前的启动方法
 * def cleanupSpec() {} : 最后一个测试运行后的清理方法
 * @author zhangkang
 * @create 2021/11/18 16:36
 */
@Title("测试计算器类")
@Subject(Calculator)
class CalculatorTest extends Specification {

    @Shared
    def calculator

    def a = Mock(Person.class)

    def setupSpec() {
        println("setupSpec")
        calculator = new Calculator();
    }

    def cleanupSpec() {
        println("cleanupSpec")
    }

    def setup() {
        println("setup")
    }

    def cleanup() {
        print(a.getAge())
        println("cleanup")
    }

    def "expect"() {
        expect: // expect: 期望的行为，when-then的精简版
        1 < 2
    }

    // given when then
    def "given-when-then"() {
        given:
        def a = 1
        def b = 2

        when: // 触发行为，比如调用指定方法或函数
        def c = calculator.sum(a, b)

        then: // then: 做出断言表达式
        c == 3
    }

    def "测试expect-where"() {
        expect:
        z == calculator.sum(x, y)

        where:
        x | y || z
        1 | 0 || 1
        2 | 3 || 5
    }

    def "test4"() {
        expect:
        name.size() == len

        where:
        name  | len
        "aa"  | 2
        "aaa" | 3
    }

    def "test5"() {
        expect:
        name.size() == len

        where:
        name << ["aa", "aaa"]
        len << [2, 3]
    }

    def "verifyAll"() {
        when:
        int a = calculator.sum(1, 1)
        int b = calculator.sum(1, 2)
        int c = calculator.sum(1, 3)

        then:
        verifyAll {
            a == 2
            b == 3
            c == 4
        }
    }

    def "测试对象"() {
        given:
        Date now = new Date()
        Person person = new Person(name: "zk", age: 18, birthday: now)

        expect:
        with(person) {
            name == "zk"
            age < 20
        }
    }
}
