package com.qylm.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 车辆信息详细持久化类
 * @author smj
 */
@Entity
@Table(uniqueConstraints = {}, name = "vehicleinfo_detail")
public class VehicleInfoDetail extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6223963728314264173L;
	
	/**
	 * vehicleInfo
	 */
	public static final String VEHICLE_INFO = "vehicleInfo";
	
	/**
	 * employee
	 */
	public static final String EMPLOYEE = "employee";

	/**
	 * 车辆信息
	 */
	@ManyToOne(targetEntity = VehicleInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "vehicleInfoId")
	private VehicleInfo vehicleInfo;
	
	/**
	 * 开机人
	 */
	@ManyToOne(targetEntity = Employee.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "employeeId")
	private Employee employee;
	
	/**
	 * 开机人级别
	 * 1：主手
	 * 2：副手
	 */
	private String level;
	
	/**
	 * 备注
	 */
	private String remark;

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
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
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
