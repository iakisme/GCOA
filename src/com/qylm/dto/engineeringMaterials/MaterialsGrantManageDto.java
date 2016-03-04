package com.qylm.dto.engineeringMaterials;

import java.io.Serializable;
import java.util.Date;

/**
 * 保存原料领取管理画面需要的值
 * @author smj
 */
public class MaterialsGrantManageDto implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7616786868109171170L;

	/**
	 * 车辆编号
	 */
	private String vehicleNumber;
	
	/**
	 * 客户编号
	 */
	private String customerSerialNumber;
	
	/**
	 * 客户名称
	 */
	private String customerName;
	
	/**
	 * 项目开始日期（开始）
	 */
	private Date startBeginDate;
	
	/**
	 * 项目开始日期（结束）
	 */
	private Date endBeginDate;
	
	/**
	 * 项目状态
	 * 1：未生效，（显示红色#FF0000）
	 * 2：工作安排中，（显示黄色#FFD700）
	 * 3：工作已安排，（显示绿色#00FF00）
	 * 4：工作暂停，（显示暗红色#FF4040）
	 * 5：工作完结，（显示蓝色#0000FF）
	 */
	private String type;

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
	 * @return the customerSerialNumber
	 */
	public String getCustomerSerialNumber() {
		return customerSerialNumber;
	}

	/**
	 * @param customerSerialNumber the customerSerialNumber to set
	 */
	public void setCustomerSerialNumber(String customerSerialNumber) {
		this.customerSerialNumber = customerSerialNumber;
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
	 * @return the startBeginDate
	 */
	public Date getStartBeginDate() {
		return startBeginDate;
	}

	/**
	 * @param startBeginDate the startBeginDate to set
	 */
	public void setStartBeginDate(Date startBeginDate) {
		this.startBeginDate = startBeginDate;
	}

	/**
	 * @return the endBeginDate
	 */
	public Date getEndBeginDate() {
		return endBeginDate;
	}

	/**
	 * @param endBeginDate the endBeginDate to set
	 */
	public void setEndBeginDate(Date endBeginDate) {
		this.endBeginDate = endBeginDate;
	}

	/**
	 * @return the vehicleNumber
	 */
	public String getVehicleNumber() {
		return vehicleNumber;
	}

	/**
	 * @param vehicleNumber the vehicleNumber to set
	 */
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

}
