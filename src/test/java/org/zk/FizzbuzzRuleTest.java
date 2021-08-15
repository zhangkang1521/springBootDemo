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

public class FizzbuzzRuleTest {


	// print "fizz" if the number is multiple of 5
	// print "buzz" if the number is multiple of 7
	// print "fizzbuzz" if the number is multiple of 5 and 7
	// print the number itself otherwise

	@Test
	public void fizzbuzzTest() {
		RulesEngineParameters parameters = new RulesEngineParameters().skipOnFirstAppliedRule(true);
		RulesEngine fizzBuzzEngine = new DefaultRulesEngine(parameters);

		// create rules
		Rules rules = new Rules();
		rules.register(new FizzRule()); // 5
		rules.register(new BuzzRule()); // 7
		rules.register(new FizzBuzzRule(new FizzRule(), new BuzzRule())); // 5 or 7
		rules.register(new NonFizzBuzzRule()); // not 5 7

		// fire rules
		Facts facts = new Facts();
		for (int i = 1; i <= 100; i++) {
			System.out.print(i + " => ");
			facts.put("number", i);
			fizzBuzzEngine.fire(rules, facts);
			System.out.println();
		}
	}
}
