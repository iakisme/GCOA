package com.qylm.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 车辆违章持久化类
 * @author smj
 */
@Entity
@Table(uniqueConstraints = {}, name = "vehiclepeccancy")
public class VehiclePeccancy extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2941595832952426374L;

	/**
	 * vehicleInfo
	 */
	public static final String VEHICLE_INFO = "vehicleInfo";
	
	/**
	 * 车辆编号
	 */
	public static final String VEHICLE_INFO_NUMBER = "vehicleInfo.number";
	
	/**
	 * employee
	 */
	public static final String EMPLOYEE = "employee";
	
	/**
	 * cause（违章或事故原因）
	 */
	public static final String CAUSE = "cause";
	
	/**
	 * address（违章或事故地址）
	 */
	public static final String ADDRESS = "address";
	
	/**
	 * state（违章事故处理情况）
	 */
	public static final String STATE = "state";
	
	/**
	 * peccancyDate（违章事故处理日期）
	 */
	public static final String PECCANCY_DATE = "peccancyDate";

	/**
	 * 车辆信息
	 */
	@ManyToOne(targetEntity = VehicleInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "vehicleInfoId")
	private VehicleInfo vehicleInfo;
	
	/**
	 * 违章人
	 */
	@ManyToOne(targetEntity = Employee.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "employeeId")
	private Employee employee;
	
	/**
	 * 违章或事故原因
	 */
	private String cause;
	
	/**
	 * 违章或事故地址
	 */
	private String address;
	
	/**
	 * 违章或事故费用
	 */
	private BigDecimal cost;
	
	/**
	 * 违章扣分
	 */
	private Integer score;
	
	/**
	 * 违章或事故备注
	 */
	private String remark;
	
	/**
	 * 有效情况
	 * true有效，反之无效
	 */
	private boolean state;
	
	/**
	 * 违章或事故日期
	 */
	private Date peccancyDate;

	/**
	 * @return the vehicleInfo
	 */
	public VehicleInfo getVehicleInfo() {
		return vehicleInfo;
	}

	/**
	 * @param vehicleInfo the vehicleInfo to set
	 */
	public void setVehicleInfo(VehicleInfo vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
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
	 * @return the cause
	 */
	public String getCause() {
		return cause;
	}

	/**
	 * @param cause the cause to set
	 */
	public void setCause(String cause) {
		this.cause = cause;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the cost
	 */
	public BigDecimal getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	/**
	 * @return the score
	 */
	public Integer getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(Integer score) {
		this.score = score;
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

	/**
	 * @return the peccancyDate
	 */
	public Date getPeccancyDate() {
		return peccancyDate;
	}

	/**
	 * @param peccancyDate the peccancyDate to set
	 */
	public void setPeccancyDate(Date peccancyDate) {
		this.peccancyDate = peccancyDate;
	}

}
