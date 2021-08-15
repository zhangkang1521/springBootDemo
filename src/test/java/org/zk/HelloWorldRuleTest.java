package org.zk;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.api.RulesEngineParameters;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.junit.Test;
import org.zk.rules.fizzbuzz.BuzzRule;
import org.zk.rules.fizzbuzz.FizzBuzzRule;
import org.zk.rules.fizzbuzz.FizzRule;
import org.zk.rules.fizzbuzz.NonFizzBuzzRule;
import org.zk.rules.helloworld.HelloWorldRule;

public class HelloWorldRuleTest {

	@Test
	public void test() {
		// create facts
		Facts facts = new Facts();

		// create rules
		Rules rules = new Rules();
		rules.register(new HelloWorldRule());

		// create a rules engine and fire rules on known facts
		RulesEngine rulesEngine = new DefaultRulesEngine();
		rulesEngine.fire(rules, facts);
	}
}
