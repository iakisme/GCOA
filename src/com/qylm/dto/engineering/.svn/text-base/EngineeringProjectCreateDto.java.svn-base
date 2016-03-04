package com.qylm.dto.engineering;

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
import com.qylm.entity.VehicleInfo;

/**
 * 保存工程项目管理画面需要的值
 * @author smj
 */
public class EngineeringProjectCreateDto implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1346656690023017497L;

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
	 * 预算总金额
	 */
	private BigDecimal money;
	
	/**
	 * 单价
	 */
	private BigDecimal pumpPrice;
	
	/**
	 * 预算方量
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
	 * 3：工作进行中，（显示绿色#00FF00）
	 * 4：工作已汇报，（显示绿色#00FF00）
	 * 5：工作暂停，（显示黄色#FFD700）
	 * 6：工作完结，（显示蓝色#0000FF）
	 */
	private String type;
	
	/**
	 * 车辆详细列表
	 */
	private List<EngineeringProjectDetail> engineeringProjectDetailList;
	
	/**
	 * 修改传值
	 */
	private EngineeringProjectDetail engineeringProjectDetail;
	
	/**
	 * 车辆信息列表
	 */
	private List<VehicleInfo> vehicleInfoList; 
	
	/**
	 * 车辆编号（搜索条件）
	 */
	private String number;
	
	/**
	 * 车辆负责人（搜索条件）
	 */
	private String employeeName;
	
	/**
	 * 车辆信息列表
	 */
	private List<Employee> employeeList; 
	
	/**
	 * 工号（搜索条件）
	 */
	private String workNumber;
	
	/**
	 * 姓名（搜索条件）
	 */
	private String name;
	
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
	 * 验证是否可以操作
	 * @return true不可以操作反之可操作
	 */
	public boolean isOperation() {
		return EngineeringProject.TYPE_1.equals(type);
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
	 * @return the engineeringProjectDetail
	 */
	public EngineeringProjectDetail getEngineeringProjectDetail() {
		return engineeringProjectDetail;
	}

	/**
	 * @param engineeringProjectDetail the engineeringProjectDetail to set
	 */
	public void setEngineeringProjectDetail(
			EngineeringProjectDetail engineeringProjectDetail) {
		this.engineeringProjectDetail = engineeringProjectDetail;
	}

	/**
	 * @return the vehicleInfoList
	 */
	public List<VehicleInfo> getVehicleInfoList() {
		return vehicleInfoList;
	}

	/**
	 * @param vehicleInfoList the vehicleInfoList to set
	 */
	public void setVehicleInfoList(List<VehicleInfo> vehicleInfoList) {
		this.vehicleInfoList = vehicleInfoList;
	}

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

	/**
	 * @return the employeeList
	 */
	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	/**
	 * @param employeeList the employeeList to set
	 */
	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	/**
	 * @return the workNumber
	 */
	public String getWorkNumber() {
		return workNumber;
	}

	/**
	 * @param workNumber the workNumber to set
	 */
	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
