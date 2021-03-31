package org.zk;

import org.junit.Test;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.zk.service.UserParam;
import org.zk.service.UserService;

import java.lang.reflect.Method;
import java.util.Arrays;

public class SpelTest {

	private static final LocalVariableTableParameterNameDiscoverer DISCOVERER = new LocalVariableTableParameterNameDiscoverer();

	private static final ExpressionParser PARSER = new SpelExpressionParser();

	@Test
	public void test1() throws Exception {
		UserParam userParam = new UserParam();
		userParam.setOrderIds(Arrays.asList("1", "2"));

		Method method = UserService.class.getMethod("sayHello", UserParam.class);

		EvaluationContext context = new StandardEvaluationContext();
		String[] parameterNames = DISCOVERER.getParameterNames(method);
		context.setVariable(parameterNames[0], userParam); //
		Object key = PARSER.parseExpression("#userParam.orderIds").getValue(context);
		System.out.println(key);
	}

	@Test
	public void test2() {
		UserParam userParam = new UserParam();
		userParam.setOrderIds(Arrays.asList("1", "2"));

		EvaluationContext context = new StandardEvaluationContext();
		context.setVariable("userParam", userParam);

		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("#userParam.orderIds");
		System.out.println(expression.getValue(context));
	}
}
