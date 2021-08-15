package org.zk.rules.fizzbuzz;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "FizzRule", description = "FizzRule description", priority = 1)
public class FizzRule {

	@Condition
	public boolean isFizz(@Fact("number") Integer number) {
		return number % 5 == 0;
	}

	@Action
	public void printFizz() {
		System.out.print("fizz");
	}
}
