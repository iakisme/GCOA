package com.qylm.dto.vehicle;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.qylm.bean.returner.Returner;
import com.qylm.entity.VehicleInfo;
import com.qylm.entity.Employee;
import com.qylm.entity.User;
import com.qylm.entity.VehicleInfoDetail;

/**
 * 保存车辆信息管理画面需要的值
 * @author smj
 */
public class VehicleInfoCreateDto implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2530355997296031769L;

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
	private Employee employee;
	
	/**
	 * 车辆购车日期
	 */
	private Date purchaseDate;
	
	/**
	 * 车辆详细列表
	 */
	private List<VehicleInfoDetail> vehicleInfoDetailList;
	
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
	private VehicleInfo transferVehicleInfo;
	
	/**
	 * 返回共通方法
	 */
	private Returner<?, ?, ?> returner;

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
	 * @return the vehicleInfoDetailList
	 */
	public List<VehicleInfoDetail> getVehicleInfoDetailList() {
		return vehicleInfoDetailList;
	}

	/**
	 * @param vehicleInfoDetailList the vehicleInfoDetailList to set
	 */
	public void setVehicleInfoDetailList(
			List<VehicleInfoDetail> vehicleInfoDetailList) {
		this.vehicleInfoDetailList = vehicleInfoDetailList;
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
	 * @return the transferVehicleInfo
	 */
	public VehicleInfo getTransferVehicleInfo() {
		return transferVehicleInfo;
	}

	/**
	 * @param transferVehicleInfo the transferVehicleInfo to set
	 */
	public void setTransferVehicleInfo(VehicleInfo transferVehicleInfo) {
		this.transferVehicleInfo = transferVehicleInfo;
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
