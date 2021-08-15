package org.zk.rules.fizzbuzz;

import org.jeasy.rules.support.composite.UnitRuleGroup;

/**
 * 组合规则，所有条件都满足，执行所有操作
 */
public class FizzBuzzRule extends UnitRuleGroup {

	public FizzBuzzRule(Object... rules) {
		for (Object rule : rules) {
			addRule(rule);
		}
	}

	@Override
	public int getPriority() {
		return 0;
	}
}
