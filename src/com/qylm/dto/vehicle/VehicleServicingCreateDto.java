package com.qylm.dto.vehicle;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.qylm.bean.returner.Returner;
import com.qylm.entity.Employee;
import com.qylm.entity.User;
import com.qylm.entity.VehicleInfo;
import com.qylm.entity.VehicleServicing;

/**
 * 保存车辆维修管理画面需要的值
 * @author smj
 */
public class VehicleServicingCreateDto implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8463614746233559642L;

	/**
	 * 车辆信息
	 */
	private VehicleInfo vehicleInfo;
	
	/**
	 * 维修人
	 */
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
	 * 1：未生效
	 * 2：维修中
	 * 3：维修完毕
	 */
	private String state;
	
	/**
	 * 维修日期
	 */
	private Date servicingDate;
	
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
	private VehicleServicing transferVehicleServicing;
	
	/**
	 * 返回共通方法
	 */
	private Returner<?, ?, ?> returner;

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
	 * @return the transferVehicleServicing
	 */
	public VehicleServicing getTransferVehicleServicing() {
		return transferVehicleServicing;
	}

	/**
	 * @param transferVehicleServicing the transferVehicleServicing to set
	 */
	public void setTransferVehicleServicing(
			VehicleServicing transferVehicleServicing) {
		this.transferVehicleServicing = transferVehicleServicing;
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
