package com.qylm.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.qylm.common.utils.BigDecimalUtil;
import com.qylm.constants.Constants;

/**
 * 客户收款情况
 * @author smj
 */
@Entity
@Table(uniqueConstraints = {}, name = "customerfinancial")
public class CustomerFinancial extends BaseEntity {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3806007950363539075L;

	/**
	 * engineeringProject
	 */
	public static final String ENGINEERING_PROJECT = "engineeringProject";
	
	/**
	 * engineeringProject.id
	 */
	public static final String ENGINEERING_PROJECT_ID = "engineeringProject.id";
	
	/**
	 * engineeringProject.customer
	 */
	public static final String ENGINEERING_PROJECT_CUSTOMER = "engineeringProject.customer";
	
	/**
	 * customer
	 */
	public static final String CUSTOMER = "customer";
	
	/**
	 * customer.name
	 */
	public static final String CUSTOMER_NAME = "customer.name";
	
	/**
	 * number编号
	 */
	public static final String NUMBER = "number";
	
	/**
	 * 收款类型
	 */
	public static final String TYPE = "type";
	
	/**
	 * 收款类型_1
	 */
	public static final String TYPE_1 = "1";
	
	/**
	 * 收款类型_2
	 */
	public static final String TYPE_2 = "2";
	
	/**
	 * 收款类型_3
	 */
	public static final String TYPE_3 = "3";
	
	/**
	 * 收款状态
	 */
	public static final String STATE = "state";
	
	/**
	 * 收款状态_1
	 */
	public static final String STATE_1 = "1";
	
	/**
	 * 收款状态_2
	 */
	public static final String STATE_2 = "2";
	
	/**
	 * 收款状态_3
	 */
	public static final String STATE_3 = "3";
	
	/**
	 * 收款日期
	 */
	public static final String FINANCIAL_DATE = "financialDate";
	
	/**
	 * money收取金额
	 */
	public static final String MONEY = "money";
	
	/**
	 * 编号
	 */
	private String number;

	/**
	 * 客户
	 */
	@ManyToOne(targetEntity = Customer.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "customerId")
	private Customer customer;
	
	/**
	 * 收款类型
	 * 1：单价
	 * 2：包月
	 * 3：包年
	 */
	private String type;
	
	/**
	 * 方量单价
	 */
	private BigDecimal pumpPrice;
	
	/**
	 * 收款状态
	 * <ul>
	 * <li>1：未生效</li>
	 * <li>2：确认收款中</li>
	 * <li>3：收款完毕</li>
	 * </ul>
	 */
	private String state;
	
	/**
	 * 收款日期
	 */
	private Date financialDate;
	
	/**
	 * 收取金额
	 */
	private BigDecimal money;
	
	/**
	 * 总方量
	 */
	@Transient
	private BigDecimal sumSchedule;
	
	/**
	 * 已收款金额
	 */
	@Transient
	private BigDecimal collectMoney;
	
	/**
	 * 消费金额
	 */
	@Transient
	private BigDecimal payMoney;
	
	/**
	 * 收款项目列表
	 */
	@Transient
	private List<EngineeringFinancial> engineeringFinancialList;
	
	/**
	 * 未收款金额=收款金额-已收金额
	 */
	public BigDecimal getNotMoney() {
		if (BigDecimalUtil.isNotNullOrZero(money)) {
			return BigDecimalUtil.subtract(money, collectMoney);
		}
		return Constants.BIGDECIMAL_ZERO;
	}
	
	/**
	 * 此方法用于页面显示，获取项目状态的颜色样子
	 * @return
	 */
	public String getTypeColor() {
		int i = Integer.valueOf(type);
		String color;
		switch (i) {
		case 1:
			color = "#000000";// 黑色
			break;
		case 2:
			color = "#FF4040";// 暗红色
			break;
		case 3:
			color = "#00FF00";// 绿色
			break;
		default:
			color = "#000000";// 黑色
			break;
		}
		return color;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
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
	 * @return the pumpPrice
	 */
	public BigDecimal getPumpPrice() {
		return pumpPrice;
	}

	/**
	 * @param pumpPrice the pumpPrice to set
	 */
	public void setPumpPrice(BigDecimal pumpPrice) {
		this.pumpPrice = pumpPrice;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the financialDate
	 */
	public Date getFinancialDate() {
		return financialDate;
	}

	/**
	 * @param financialDate the financialDate to set
	 */
	public void setFinancialDate(Date financialDate) {
		this.financialDate = financialDate;
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
	 * @return the sumSchedule
	 */
	public BigDecimal getSumSchedule() {
		return sumSchedule;
	}

	/**
	 * @param sumSchedule the sumSchedule to set
	 */
	public void setSumSchedule(BigDecimal sumSchedule) {
		this.sumSchedule = sumSchedule;
	}

	/**
	 * @return the collectMoney
	 */
	public BigDecimal getCollectMoney() {
		return collectMoney;
	}

	/**
	 * @param collectMoney the collectMoney to set
	 */
	public void setCollectMoney(BigDecimal collectMoney) {
		this.collectMoney = collectMoney;
	}

	/**
	 * @return the payMoney
	 */
	public BigDecimal getPayMoney() {
		return payMoney;
	}

	/**
	 * @param payMoney the payMoney to set
	 */
	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}

	/**
	 * @return the engineeringFinancialList
	 */
	public List<EngineeringFinancial> getEngineeringFinancialList() {
		return engineeringFinancialList;
	}

	/**
	 * @param engineeringFinancialList the engineeringFinancialList to set
	 */
	public void setEngineeringFinancialList(
			List<EngineeringFinancial> engineeringFinancialList) {
		this.engineeringFinancialList = engineeringFinancialList;
	}

}
