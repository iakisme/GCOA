package com.qylm.dto.engineeringMaterials;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.qylm.bean.returner.Returner;
import com.qylm.entity.Customer;
import com.qylm.entity.Employee;
import com.qylm.entity.EngineeringProject;
import com.qylm.entity.EngineeringProjectDetail;
import com.qylm.entity.User;

/**
 * 保存工作登记管理画面需要的值
 * @author smj
 */
public class MaterialsGrantCreateProjectDto implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6358307026951866148L;

	/**
	 * 客户
	 */
	private Customer customer;
	
	/**
	 * 项目负责人
	 */
	private Employee employee;
	
	/**
	 * 项目开始日期
	 */
	private Date beginDate;
	
	/**
	 * 项目结束日期
	 */
	private Date endDate;
	
	/**
	 * 工作地址
	 */
	private String workAddress;

	/**
	 * 工地名称
	 */
	private String constructionName;
	
	/**
	 * 工作内容
	 */
	private String remark;
	
	/**
	 * 项目金额
	 */
	private BigDecimal money;
	
	/**
	 * 单价
	 */
	private BigDecimal pumpPrice;
	
	/**
	 * 实际方量
	 */
	private BigDecimal actualVolume;
	
	/**
	 * 完成量，进度条
	 */
	private BigDecimal schedule;
	
	/**
	 * 项目状态
	 * 1：未生效，（显示红色#FF0000）
	 * 2：工作安排中，（显示暗红色#FF4040）
	 * 3：工作已安排，（显示绿色#00FF00）
	 * 4：工作暂停，（显示黄色#FFD700）
	 * 5：工作完结，（显示蓝色#0000FF）
	 */
	private String type;
	
	/**
	 * 车辆详细列表
	 */
	private List<EngineeringProjectDetail> engineeringProjectDetailList;
	
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
	private EngineeringProject transferEngineeringProject;
	
	/**
	 * 返回共通方法
	 */
	private Returner<?, ?, ?> returner;

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
	 * @return the beginDate
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * @param beginDate the beginDate to set
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	 * @return the constructionName
	 */
	public String getConstructionName() {
		return constructionName;
	}

	/**
	 * @param constructionName the constructionName to set
	 */
	public void setConstructionName(String constructionName) {
		this.constructionName = constructionName;
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
	 * @return the money
	 */
	public BigDecimal getMoney() {
		return money;
	}

	/**
	 * @param money the money to set
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
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
	 * @return the actualVolume
	 */
	public BigDecimal getActualVolume() {
		return actualVolume;
	}

	/**
	 * @param actualVolume the actualVolume to set
	 */
	public void setActualVolume(BigDecimal actualVolume) {
		this.actualVolume = actualVolume;
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
	 * @return the engineeringProjectDetailList
	 */
	public List<EngineeringProjectDetail> getEngineeringProjectDetailList() {
		return engineeringProjectDetailList;
	}

	/**
	 * @param engineeringProjectDetailList the engineeringProjectDetailList to set
	 */
	public void setEngineeringProjectDetailList(
			List<EngineeringProjectDetail> engineeringProjectDetailList) {
		this.engineeringProjectDetailList = engineeringProjectDetailList;
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
	 * @return the transferEngineeringProject
	 */
	public EngineeringProject getTransferEngineeringProject() {
		return transferEngineeringProject;
	}

	/**
	 * @param transferEngineeringProject the transferEngineeringProject to set
	 */
	public void setTransferEngineeringProject(
			EngineeringProject transferEngineeringProject) {
		this.transferEngineeringProject = transferEngineeringProject;
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
