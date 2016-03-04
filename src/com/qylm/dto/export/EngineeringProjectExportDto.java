package com.qylm.dto.export;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.qylm.entity.Customer;
import com.qylm.entity.VehicleInfo;

/**
 * 项目工程导入
 * @author Administrator
 */
public class EngineeringProjectExportDto extends BaseExportDto {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1155789487165627630L;

	/**
	 * 项目日期
	 */
	private Date date;
	
	/**
	 * 车辆编号
	 */
	private VehicleInfo vehicleInfo;
	
	/**
	 * 车辆编号
	 */
	private String vehicleNumber;
	
	/**
	 * 工作地址（客户）
	 */
	private Customer customer;

	/**
	 * 工地名称
	 */
	private String workAddress;
	
	/**
	 * 泵送部位
	 */
	private String pumpParts;
	
	/**
	 * 完成量，进度条（实际方量）
	 */
	private BigDecimal schedule;
	
	/**
	 * 泵送开始时间
	 */
	private Date startPumpDate;
	
	/**
	 * 泵送结束时间
	 */
	private Date endPumpDate;
	
	/**
	 * 单价(泵送价)
	 */
	private BigDecimal pumpPrice;
	
	/**
	 * 加油量（升）
	 */
	private BigDecimal gasVolume;
	
	/**
	 * 油费（￥）
	 */
	private BigDecimal gasPrice;
	
	/**
	 * 开机人列表
	 */
	private List<String> employeeList;
	
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
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

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the workAddress
	 */
	public String getWorkAddress() {
		return workAddress;
	}

	/**
	 * @param workAddress the workAddress to set
	 */
	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	/**
	 * @return the pumpParts
	 */
	public String getPumpParts() {
		return pumpParts;
	}

	/**
	 * @param pumpParts the pumpParts to set
	 */
	public void setPumpParts(String pumpParts) {
		this.pumpParts = pumpParts;
	}

	/**
	 * @return the schedule
	 */
	public BigDecimal getSchedule() {
		return schedule;
	}

	/**
	 * @param schedule the schedule to set
	 */
	public void setSchedule(BigDecimal schedule) {
		this.schedule = schedule;
	}

	/**
	 * @return the startPumpDate
	 */
	public Date getStartPumpDate() {
		return startPumpDate;
	}

	/**
	 * @param startPumpDate the startPumpDate to set
	 */
	public void setStartPumpDate(Date startPumpDate) {
		this.startPumpDate = startPumpDate;
	}

	/**
	 * @return the endPumpDate
	 */
	public Date getEndPumpDate() {
		return endPumpDate;
	}

	/**
	 * @param endPumpDate the endPumpDate to set
	 */
	public void setEndPumpDate(Date endPumpDate) {
		this.endPumpDate = endPumpDate;
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
	 * @return the gasVolume
	 */
	public BigDecimal getGasVolume() {
		return gasVolume;
	}

	/**
	 * @param gasVolume the gasVolume to set
	 */
	public void setGasVolume(BigDecimal gasVolume) {
		this.gasVolume = gasVolume;
	}

	/**
	 * @return the gasPrice
	 */
	public BigDecimal getGasPrice() {
		return gasPrice;
	}

	/**
	 * @param gasPrice the gasPrice to set
	 */
	public void setGasPrice(BigDecimal gasPrice) {
		this.gasPrice = gasPrice;
	}

	/**
	 * @return the employeeList
	 */
	public List<String> getEmployeeList() {
		return employeeList;
	}

	/**
	 * @param employeeList the employeeList to set
	 */
	public void setEmployeeList(List<String> employeeList) {
		this.employeeList = employeeList;
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
