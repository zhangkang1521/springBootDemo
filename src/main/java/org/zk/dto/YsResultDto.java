package org.zk.dto;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class YsResultDto {

	private BigInteger vouchId;

	private BigInteger orderNo;

	private String fdbNo;

	private BigDecimal payAmount;

	private String subjectCode;

	private Date visitTime;

	private int errorType;

	private String voucherType;

	private BigInteger voucherNo;

	private String departmentCode;

	private String producer;

	private Date liquidationDate;

	private Date createTime;

	private Date updateTime;

	private String payChannel;

	private String compactName;

	private String orderStatusName;

	private String factSubjectCode;

	public BigInteger getVouchId() {
		return vouchId;
	}

	public void setVouchId(BigInteger vouchId) {
		this.vouchId = vouchId;
	}

	public BigInteger getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(BigInteger orderNo) {
		this.orderNo = orderNo;
	}

	public String getFdbNo() {
		return fdbNo;
	}

	public void setFdbNo(String fdbNo) {
		this.fdbNo = fdbNo;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public Date getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}

	public int getErrorType() {
		return errorType;
	}

	public void setErrorType(int errorType) {
		this.errorType = errorType;
	}

	public String getVoucherType() {
		return voucherType;
	}

	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}

	public BigInteger getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(BigInteger voucherNo) {
		this.voucherNo = voucherNo;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public Date getLiquidationDate() {
		return liquidationDate;
	}

	public void setLiquidationDate(Date liquidationDate) {
		this.liquidationDate = liquidationDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public String getCompactName() {
		return compactName;
	}

	public void setCompactName(String compactName) {
		this.compactName = compactName;
	}

	public String getOrderStatusName() {
		return orderStatusName;
	}

	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}

	public String getFactSubjectCode() {
		return factSubjectCode;
	}

	public void setFactSubjectCode(String factSubjectCode) {
		this.factSubjectCode = factSubjectCode;
	}
}
