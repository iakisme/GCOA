package com.qylm.dto.vehicle;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.qylm.bean.returner.Returner;
import com.qylm.entity.Employee;
import com.qylm.entity.User;
import com.qylm.entity.VehicleInfo;
import com.qylm.entity.VehiclePeccancy;

/**
 * 保存车辆违章事故管理画面需要的值
 * @author smj
 */
public class VehiclePeccancyCreateDto implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4270581949246623834L;

	/**
	 * 车辆信息
	 */
	private VehicleInfo vehicleInfo;
	
	/**
	 * 违章人
	 */
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
	 * 违章扣分
	 */
	private Integer score;
	
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
	private VehiclePeccancy transferVehiclePeccancy;
	
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
	 * @return the transferVehiclePeccancy
	 */
	public VehiclePeccancy getTransferVehiclePeccancy() {
		return transferVehiclePeccancy;
	}

	/**
	 * @param transferVehiclePeccancy the transferVehiclePeccancy to set
	 */
	public void setTransferVehiclePeccancy(VehiclePeccancy transferVehiclePeccancy) {
		this.transferVehiclePeccancy = transferVehiclePeccancy;
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
