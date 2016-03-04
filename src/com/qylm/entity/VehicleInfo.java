package com.qylm.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.qylm.common.utils.StringUtil;

/**
 * 车辆信息管理持久化类
 * @author smj
 */
@Entity
@Table(uniqueConstraints = {}, name = "vehicleinfo")
public class VehicleInfo extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1762787066272199139L;
	
	/**
	 * number
	 */
	public static final String NUMBER = "number";
	
	/**
	 * employee
	 */
	public static final String EMPLOYEE = "employee";
	
	/**
	 * employee.name
	 */
	public static final String EMPLOYEE_NAME = "employee.name";
	
	/**
	 * 车辆编号
	 */
	private String number;
	
	/**
	 * 车辆备注
	 */
	private String remark;
	
	/**
	 * 车辆负责人
	 */
	@ManyToOne(targetEntity = Employee.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "employeeId")
	private Employee employee;
	
	/**
	 * 车辆购车日期
	 */
	private Date purchaseDate;
	
	/**
	 * 工作情况
	 */
	@Transient
	private String workInfo;

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
	 * @return the purchaseDate
	 */
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	/**
	 * @param purchaseDate the purchaseDate to set
	 */
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	/**
	 * @return the workInfo
	 */
	public String getWorkInfo() {
		if (StringUtil.isBlank(workInfo)) {
			return "<span style=\"color:#00FF00;\">如系统正常使用中，该车处于待工中</span>";
		}
		return workInfo;
	}

	/**
	 * @param workInfo the workInfo to set
	 */
	public void setWorkInfo(String workInfo) {
		this.workInfo = workInfo;
	}
	
}
