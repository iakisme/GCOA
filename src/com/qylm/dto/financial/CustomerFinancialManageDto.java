package com.qylm.dto.financial;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.qylm.entity.CustomerFinancial;
import com.qylm.entity.EngineeringFinancial;
import com.qylm.entity.FinancialCollectDetail;
import com.qylm.entity.FinancialPayDetail;

/**
 * 保存客户收支明细管理画面需要的值
 * @author smj
 */
public class CustomerFinancialManageDto implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8297131132359253761L;
	
	/**
	 * 编号
	 */
	private String number;

	/**
	 * 客户
	 */
	private String customerName;
	
	/**
	 * 收款日期（开始）
	 */
	private Date beginFinancialDate;
	
	/**
	 * 收款日期（结束）
	 */
	private Date endFinancialDate;
	
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
	 * 收款项目列表
	 */
	private List<EngineeringFinancial> engineeringFinancialList;
	
	/**
	 * 收款项目
	 */
	private CustomerFinancial customerFinancial;
	
	/**
	 * 用于登记收款明细
	 */
	private FinancialCollectDetail financialCollectDetail = new FinancialCollectDetail();
	
	/**
	 * 显示收款明细列表
	 */
	private List<FinancialCollectDetail> financialCollectDetailList;
	
	/**
	 * 用于登记支付明细
	 */
	private FinancialPayDetail financialPayDetail = new FinancialPayDetail();
	
	/**
	 * 显示支付明细列表
	 */
	private List<FinancialPayDetail> financialPayDetailList;

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
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the beginFinancialDate
	 */
	public Date getBeginFinancialDate() {
		return beginFinancialDate;
	}

	/**
	 * @param beginFinancialDate the beginFinancialDate to set
	 */
	public void setBeginFinancialDate(Date beginFinancialDate) {
		this.beginFinancialDate = beginFinancialDate;
	}

	/**
	 * @return the endFinancialDate
	 */
	public Date getEndFinancialDate() {
		return endFinancialDate;
	}

	/**
	 * @param endFinancialDate the endFinancialDate to set
	 */
	public void setEndFinancialDate(Date endFinancialDate) {
		this.endFinancialDate = endFinancialDate;
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
	 * @return the financialCollectDetail
	 */
	public FinancialCollectDetail getFinancialCollectDetail() {
		return financialCollectDetail;
	}

	/**
	 * @param financialCollectDetail the financialCollectDetail to set
	 */
	public void setFinancialCollectDetail(
			FinancialCollectDetail financialCollectDetail) {
		this.financialCollectDetail = financialCollectDetail;
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
	 * @return the financialPayDetail
	 */
	public FinancialPayDetail getFinancialPayDetail() {
		return financialPayDetail;
	}

	/**
	 * @param financialPayDetail the financialPayDetail to set
	 */
	public void setFinancialPayDetail(FinancialPayDetail financialPayDetail) {
		this.financialPayDetail = financialPayDetail;
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
	
}
