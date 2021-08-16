package org.zk;

public class Rule {
	private String condition;
	private String action;
	private int priority;

	public Rule(String condition, String action, int priority) {
		this.condition = condition;
		this.action = action;
		this.priority = priority;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
}
