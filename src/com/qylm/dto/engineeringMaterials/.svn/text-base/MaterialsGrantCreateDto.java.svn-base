package com.qylm.dto.engineeringMaterials;

import java.io.Serializable;
import java.math.BigDecimal;

import com.qylm.bean.returner.Returner;
import com.qylm.entity.Employee;
import com.qylm.entity.EngineeringProjectDetail;
import com.qylm.entity.MaterialsGrant;
import com.qylm.entity.User;

/**
 * 保存原料发放记录登录画面需要的值
 * @author smj
 */
public class MaterialsGrantCreateDto implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1012757864260928509L;

	/**
	 * 发放项目详细
	 */
	private EngineeringProjectDetail engineeringProjectDetail;
	
	/**
	 * 领取员工
	 */
	private Employee employee;
	
	/**
	 * 领取量
	 */
	private BigDecimal receiveSum;
	
	/**
	 * 领取状态
	 * 1：未领取
	 * 2：领取中
	 * 3：确认领取
	 */
	private String state;
	
	/**
	 * 原料状态
	 * 1：未发放
	 * 2：发放中
	 * 3：已出发
	 * 4：已确认
	 */
	private String type;
	
	/**
	 * 备注
	 */
	private String remark;
	
	
	/**
	 * 创建客户
	 */
	private User creater;
	
	/**
	 * 归属公司
	 */
	private User belongingUser;
	
	/**
	 * 修改传值
	 */
	private MaterialsGrant transferMaterialsGrant;
	
	/**
	 * 返回共通方法
	 */
	private Returner<?, ?, ?> returner;

	/**
	 * @return the engineeringProjectDetail
	 */
	public EngineeringProjectDetail getEngineeringProjectDetail() {
		return engineeringProjectDetail;
	}

	/**
	 * @param engineeringProjectDetail the engineeringProjectDetail to set
	 */
	public void setEngineeringProjectDetail(
			EngineeringProjectDetail engineeringProjectDetail) {
		this.engineeringProjectDetail = engineeringProjectDetail;
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
	 * @return the receiveSum
	 */
	public BigDecimal getReceiveSum() {
		return receiveSum;
	}

	/**
	 * @param receiveSum the receiveSum to set
	 */
	public void setReceiveSum(BigDecimal receiveSum) {
		this.receiveSum = receiveSum;
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
	 * @return the transferMaterialsGrant
	 */
	public MaterialsGrant getTransferMaterialsGrant() {
		return transferMaterialsGrant;
	}

	/**
	 * @param transferMaterialsGrant the transferMaterialsGrant to set
	 */
	public void setTransferMaterialsGrant(MaterialsGrant transferMaterialsGrant) {
		this.transferMaterialsGrant = transferMaterialsGrant;
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
