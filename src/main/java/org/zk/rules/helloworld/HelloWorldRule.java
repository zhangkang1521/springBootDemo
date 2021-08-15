package org.zk.rules.helloworld;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "helloWorldRule")
public class HelloWorldRule {

	@Condition
	public boolean when() {
		return true;
	}

	@Action
	public void then() throws Exception {
		System.out.println("hello world");
	}
}
