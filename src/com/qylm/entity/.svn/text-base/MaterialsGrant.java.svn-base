package com.qylm.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 原料发放记录久化类
 * @author smj
 */
@Entity
@Table(uniqueConstraints = {}, name = "materialsgrant")
public class MaterialsGrant extends BaseEntity {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -762597871355946898L;
	
	/**
	 * 发放项目详细
	 */
	@ManyToOne(targetEntity = EngineeringProjectDetail.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "engineeringProjectDetailId")
	private EngineeringProjectDetail engineeringProjectDetail;
	
	/**
	 * 领取员工
	 */
	@ManyToOne(targetEntity = Employee.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "employeeId")
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
	
}
