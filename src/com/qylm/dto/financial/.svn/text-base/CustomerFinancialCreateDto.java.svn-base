package com.qylm.dto.financial;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.qylm.bean.returner.Returner;
import com.qylm.entity.Customer;
import com.qylm.entity.CustomerFinancial;
import com.qylm.entity.EngineeringFinancial;
import com.qylm.entity.EngineeringProject;
import com.qylm.entity.FinancialCollectDetail;
import com.qylm.entity.FinancialPayDetail;
import com.qylm.entity.User;

/**
 * 保存客户收支明细管理画面需要的值
 * @author smj
 */
public class CustomerFinancialCreateDto implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4620419558694424344L;

	/**
	 * 编号
	 */
	private String number;

	/**
	 * 客户
	 */
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
	 * 创建车辆
	 */
	private User creater;
	
	/**
	 * 归属公司
	 */
	private User belongingUser;
	
	/**
	 * 修改传值
	 */
	private CustomerFinancial transferCustomerFinancial;
	
	/**
	 * 选中保存的收款项目
	 */
	private List<EngineeringProject> engineeringProjectList;
	
	/**
	 * 收款项目
	 */
	private List<EngineeringFinancial> engineeringFinancialList;
	
	/**
	 * 收款明细
	 */
	private List<FinancialCollectDetail> financialCollectDetailList;
	
	/**
	 * 付款明细
	 */
	private List<FinancialPayDetail> financialPayDetailList;
	
	/**
	 * 总实际方量
	 */
	private BigDecimal sumSchedule;
	
	/**
	 * 返回共通方法
	 */
	private Returner<?, ?, ?> returner;
	
	/**
	 * 工作地址（项目搜索条件）
	 */
	private String workAddress;
	
	/**
	 * 项目开始日期（开始）（项目搜索条件）
	 */
	private Date beginDate;
	
	/**
	 * 项目开始日期（结束）（项目搜索条件）
	 */
	private Date endDate;

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
	 * @return the creater
	 */
	public User getCreater() {
		return creater;
	}

	/**
	 * @param creater the creater to set
	 */
	public void setCreater(User creater) {
		this.creater = creater;
	}

	/**
	 * @return the belongingUser
	 */
	public User getBelongingUser() {
		return belongingUser;
	}

	/**
	 * @param belongingUser the belongingUser to set
	 */
	public void setBelongingUser(User belongingUser) {
		this.belongingUser = belongingUser;
	}

	/**
	 * @return the transferCustomerFinancial
	 */
	public CustomerFinancial getTransferCustomerFinancial() {
		return transferCustomerFinancial;
	}

	/**
	 * @param transferCustomerFinancial the transferCustomerFinancial to set
	 */
	public void setTransferCustomerFinancial(
			CustomerFinancial transferCustomerFinancial) {
		this.transferCustomerFinancial = transferCustomerFinancial;
	}

	/**
	 * @return the engineeringProjectList
	 */
	public List<EngineeringProject> getEngineeringProjectList() {
		return engineeringProjectList;
	}

	/**
	 * @param engineeringProjectList the engineeringProjectList to set
	 */
	public void setEngineeringProjectList(
			List<EngineeringProject> engineeringProjectList) {
		this.engineeringProjectList = engineeringProjectList;
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

	/**
	 * @return the financialCollectDetailList
	 */
	public List<FinancialCollectDetail> getFinancialCollectDetailList() {
		return financialCollectDetailList;
	}

	/**
	 * @param financialCollectDetailList the financialCollectDetailList to set
	 */
	public void setFinancialCollectDetailList(
			List<FinancialCollectDetail> financialCollectDetailList) {
		this.financialCollectDetailList = financialCollectDetailList;
	}

	/**
	 * @return the financialPayDetailList
	 */
	public List<FinancialPayDetail> getFinancialPayDetailList() {
		return financialPayDetailList;
	}

	/**
	 * @param financialPayDetailList the financialPayDetailList to set
	 */
	public void setFinancialPayDetailList(
			List<FinancialPayDetail> financialPayDetailList) {
		this.financialPayDetailList = financialPayDetailList;
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
	 * @return the returner
	 */
	public Returner<?, ?, ?> getReturner() {
		return returner;
	}

	/**
	 * @param returner the returner to set
	 */
	public void setReturner(Returner<?, ?, ?> returner) {
		this.returner = returner;
	}

	/**
	 * @return the workAddress
	 */
	public String getWorkAddress() {
		return workAddress;
	}

	/**
	 * @param workAddress the workAddress to set
	 */
	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	/**
	 * @return the beginDate
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * @param beginDate the beginDate to set
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
