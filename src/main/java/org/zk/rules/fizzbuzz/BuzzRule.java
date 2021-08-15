package org.zk.rules.fizzbuzz;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "BuzzRule", description = "BuzzRule description", priority = 2)
public class BuzzRule {

	@Condition
	public boolean isBuzz(@Fact("number") Integer number) {
		return number % 7 == 0;
	}

	@Action
	public void printBuzz(@Fact("number") Integer number) {
		System.out.print("buzz");
	}
}
