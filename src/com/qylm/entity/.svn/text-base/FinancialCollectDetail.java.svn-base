package com.qylm.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 财务收详细持久化类
 * @author smj
 */
@Entity
@Table(uniqueConstraints = {}, name = "financialcollect_detail")
public class FinancialCollectDetail extends BaseEntity {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5660220077302222418L;
	
	/**
	 * customerFinancial
	 */
	public static final String CUSTOMER_FINANCIAL = "customerFinancial";
	
	/**
	 * employee
	 */
	public static final String EMPLOYEE = "employee";
	
	/**
	 * state
	 */
	public static final String STATE = "state";
	
	/**
	 * money
	 */
	public static final String MONEY = "money";

	/**
	 * 客户收支详细
	 */
	@ManyToOne(targetEntity = CustomerFinancial.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "customerFinancialId")
	private CustomerFinancial customerFinancial;
	
	/**
	 * 收取人
	 */
	@ManyToOne(targetEntity = Employee.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "employeeId")
	private Employee employee;
	
	/**
	 * 收取日期
	 */
	private Date collectDate;

	/**
	 * 收取金额
	 */
	private BigDecimal money;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 收取状态
	 * true，确认收取，反之无效数据
	 */
	private boolean state;
	
	/**
	 * @return the customerFinancial
	 */
	public CustomerFinancial getCustomerFinancial() {
		return customerFinancial;
	}

	/**
	 * @param customerFinancial the customerFinancial to set
	 */
	public void setCustomerFinancial(CustomerFinancial customerFinancial) {
		this.customerFinancial = customerFinancial;
	}

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @return the collectDate
	 */
	public Date getCollectDate() {
		return collectDate;
	}

	/**
	 * @param collectDate the collectDate to set
	 */
	public void setCollectDate(Date collectDate) {
		this.collectDate = collectDate;
	}

	/**
	 * @return the money
	 */
	public BigDecimal getMoney() {
		return money;
	}

	/**
	 * @param money the money to set
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the state
	 */
	public boolean isState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(boolean state) {
		this.state = state;
	}
	
}
