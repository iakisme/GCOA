package com.qylm.dto.vehicle;

import java.io.Serializable;

/**
 * 保存车辆信息管理画面需要的值
 * @author smj
 */
public class VehicleInfoManageDto implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5490290352355870557L;

	/**
	 * 车辆编号
	 */
	private String number;
	
	/**
	 * 车辆负责人
	 */
	private String employeeName;

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
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

}
