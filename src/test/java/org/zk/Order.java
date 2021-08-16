package org.zk;

public class Order {

	private Long id;

	private Long amount1;

	private Long amount2;

	public Order(Long id, Long amount1, Long amount2) {
		this.id = id;
		this.amount1 = amount1;
		this.amount2 = amount2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAmount1() {
		return amount1;
	}

	public void setAmount1(Long amount1) {
		this.amount1 = amount1;
	}

	public Long getAmount2() {
		return amount2;
	}

	public void setAmount2(Long amount2) {
		this.amount2 = amount2;
	}
}
