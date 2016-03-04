package com.qylm.dto.financial;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.qylm.bean.returner.Returner;
import com.qylm.entity.EngineeringFinancial;
import com.qylm.entity.EngineeringProject;
import com.qylm.entity.FinancialCollectDetail;
import com.qylm.entity.FinancialPayDetail;
import com.qylm.entity.User;

/**
 * 保存工程项目管理画面需要的值
 * @author smj
 */
public class EngineeringFinancialCreateDto implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4132178982351239801L;

	/**
	 * 项目
	 */
	private EngineeringProject engineeringProject;
	
	/**
	 * 项目金额（实际方量X客户单价）
	 */
	private BigDecimal money;
	
	/**
	 * 是否确认收款，true确认
	 */
	private boolean state;
	
	/**
	 * 收款情况
	 * true：已收款完毕，false未收完款
	 */
	private boolean financialType;
	
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
	 * 收款明细
	 */
	private List<FinancialCollectDetail> financialCollectDetailList;
	
	/**
	 * 付款明细
	 */
	private List<FinancialPayDetail> financialPayDetailList;
	
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
	private EngineeringFinancial transferEngineeringFinancial;
	
	/**
	 * 返回共通方法
	 */
	private Returner<?, ?, ?> returner;

	/**
	 * @return the engineeringProject
	 */
	public EngineeringProject getEngineeringProject() {
		return engineeringProject;
	}

	/**
	 * @param engineeringProject the engineeringProject to set
	 */
	public void setEngineeringProject(EngineeringProject engineeringProject) {
		this.engineeringProject = engineeringProject;
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

	/**
	 * @return the financialType
	 */
	public boolean isFinancialType() {
		return financialType;
	}

	/**
	 * @param financialType the financialType to set
	 */
	public void setFinancialType(boolean financialType) {
		this.financialType = financialType;
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
	 * @return the transferEngineeringFinancial
	 */
	public EngineeringFinancial getTransferEngineeringFinancial() {
		return transferEngineeringFinancial;
	}

	/**
	 * @param transferEngineeringFinancial the transferEngineeringFinancial to set
	 */
	public void setTransferEngineeringFinancial(
			EngineeringFinancial transferEngineeringFinancial) {
		this.transferEngineeringFinancial = transferEngineeringFinancial;
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

}
