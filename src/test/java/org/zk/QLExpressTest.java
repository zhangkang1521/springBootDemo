package org.zk;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class QLExpressTest {

	@Test
	public void test() throws Exception {
		ExpressRunner runner = new ExpressRunner();
		DefaultContext<String, Object> context = new DefaultContext<String, Object>();
		context.put("a",1);
		context.put("b",2);
		context.put("c",3);
		String express = "a+b*c";
		Object r = runner.execute(express, context, null, true, false);
		System.out.println(r);
	}

	@Test
	public void test2() throws Exception {
		List<Rule> rules = new ArrayList<>();
		rules.add(new Rule("people.equals('driver')", "order.getAmount1()*0.1", 1));
		rules.add(new Rule("people.equals('bd') && 'fruit'.equals(category)", "order.getAmount2()*0.2", 1));

		ExpressRunner runner = new ExpressRunner();
		DefaultContext<String, Object> context = new DefaultContext<String, Object>();
		context.put("people", "driver");
		context.put("order", new Order(1L, 100L, 200L));

		for(Rule rule : rules) {
			System.out.println("判断条件：" + rule.getCondition());
			Object match = runner.execute(rule.getCondition(), context, null, true, false);
			System.out.println("是否匹配：" + match);
			if ((Boolean) match) {
				Object result = runner.execute(rule.getAction(), context, null, true, false);
				System.out.println("运算结果：" + result);
			}
		}
	}
}
