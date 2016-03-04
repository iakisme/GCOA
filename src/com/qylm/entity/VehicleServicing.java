package com.qylm.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 车辆维修持久化类
 * @author smj
 */
@Entity
@Table(uniqueConstraints = {}, name = "vehicleservicing")
public class VehicleServicing extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4090021653814820781L;

	/**
	 * 车辆
	 */
	public static final String VEHICLE_INFO = "vehicleInfo";
	
	/**
	 * 车辆编号
	 */
	public static final String VEHICLE_INFO_NUMBER = "vehicleInfo.number";
	
	/**
	 * 维修人
	 */
	public static final String EMPLOYEE = "employee";
	
	/**
	 * 维修人姓名
	 */
	public static final String EMPLOYEE_NAME = "employee.name";
	
	/**
	 * breakdown（故障）
	 */
	public static final String BREAKDOWN = "breakdown";
	
	/**
	 * state（维修情况）
	 */
	public static final String STATE = "state";
	
	/**
	 * state.1（未生效）
	 */
	public static final String STATE_1 = "1";
	
	/**
	 * state.2（维修中）
	 */
	public static final String STATE_2 = "2";
	
	/**
	 * state.3（维修完毕）
	 */
	public static final String STATE_3 = "3";
	
	/**
	 * servicingDate（维修日期）
	 */
	public static final String SERVICING_DATE = "servicingDate";

	/**
	 * 车辆信息
	 */
	@ManyToOne(targetEntity = VehicleInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "vehicleInfoId")
	private VehicleInfo vehicleInfo;
	
	/**
	 * 维修人
	 */
	@ManyToOne(targetEntity = Employee.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "employeeId")
	private Employee employee;
	
	/**
	 * 故障
	 */
	private String breakdown;
	
	/**
	 * 费用
	 */
	private BigDecimal cost;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 维修情况
	 * 1：未生效"#000000";// 黑色
	 * 2：维修中"#FFD700";// 黄色
	 * 3：维修完毕"#00FF00";// 绿色
	 */
	private String state;
	
	/**
	 * 维修日期
	 */
	private Date servicingDate;
	
	/**
	 * 此方法用于页面显示，获取项目状态的颜色样子
	 * @return
	 */
	public String getTypeColor() {
		int i = Integer.valueOf(state);
		String color;
		switch (i) {
		case 1:
			color = "#000000";// 黑色
			break;
		case 2:
			color = "#FFD700";// 黄色
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
	 * @return the breakdown
	 */
	public String getBreakdown() {
		return breakdown;
	}

	/**
	 * @param breakdown the breakdown to set
	 */
	public void setBreakdown(String breakdown) {
		this.breakdown = breakdown;
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
	 * @return the servicingDate
	 */
	public Date getServicingDate() {
		return servicingDate;
	}

	/**
	 * @param servicingDate the servicingDate to set
	 */
	public void setServicingDate(Date servicingDate) {
		this.servicingDate = servicingDate;
	}

}
