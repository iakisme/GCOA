package com.qylm.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 财务支付详细持久化类
 * @author smj
 */
@Entity
@Table(uniqueConstraints = {}, name = "financialpay_detail")
public class FinancialPayDetail extends BaseEntity {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8664393449506911291L;
	
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
	 * 财务收支详细
	 */
	@ManyToOne(targetEntity = CustomerFinancial.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "customerFinancialId")
	private CustomerFinancial customerFinancial;
	
	/**
	 * 消费人
	 */
	@ManyToOne(targetEntity = Employee.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "employeeId")
	private Employee employee;
	
	/**
	 * 付款类型
	 * <ul>
	 * <li>1：油费</li>
	 * <li>2：车辆维修</li>
	 * <li>3：车辆违章事故</li>
	 * <li>4：车辆租用</li>
	 * <li>5：其他</li>
	 * </ul>
	 */
	private String type;
	
	/**
	 * 支付日期
	 */
	private Date payDate;

	/**
	 * 支付金额
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the payDate
	 */
	public Date getPayDate() {
		return payDate;
	}

	/**
	 * @param payDate the payDate to set
	 */
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
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
