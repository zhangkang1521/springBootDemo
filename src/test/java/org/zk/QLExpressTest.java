package org.zk;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import org.junit.Test;

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



		ExpressRunner runner = new ExpressRunner();
		DefaultContext<String, Object> context = new DefaultContext<String, Object>();
		context.put("n",10);
//		String express =
//				"for(sum=0,i=0;i<n;i++){" +
//				"sum=sum+i;" +
//				"}" +
//				"return sum;";
		Object r = runner.execute("n>11", context, null, true, false);
		System.out.println(r);
	}
}
