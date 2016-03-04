package com.qylm.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.qylm.common.utils.BigDecimalUtil;
import com.qylm.common.utils.StringUtil;
import com.qylm.constants.Constants;

/**
 * 工程项目详细管理
 * @author smj
 */
@Entity
@Table(uniqueConstraints = {}, name = "engineeringproject_detail")
public class EngineeringProjectDetail extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4985238387962277534L;
	
	/**
	 * engineeringProject
	 */
	public static final String ENGINEERING_PROJECT = "engineeringProject";
	
	/**
	 * engineeringProject.id
	 */
	public static final String ENGINEERING_PROJECT_ID = "engineeringProject.id";
	
	/**
	 * engineeringProject.customer
	 */
	public static final String ENGINEERING_PROJECT_CUSTOMER = "engineeringProject.customer";
	
	/**
	 * engineeringProject.beginDate
	 */
	public static final String ENGINEERING_PROJECT_BEGIN_DATE = "engineeringProject.beginDate";
	
	/**
	 * 别名.customer
	 */
	public static final String CUSTOMER = "customer";
	
	/**
	 * customer.name
	 */
	public static final String CUSTOMER_NAME = "customer.name";
	
	/**
	 * engineeringProject.workAddress
	 */
	public static final String WORK_ADDRESS = "engineeringProject.workAddress";
	
	/**
	 * engineeringProject.constructionName
	 */
	public static final String CONSTRUCTION_NAME = "engineeringProject.constructionName";
	
	/**
	 * engineeringProject.beginDate
	 */
	public static final String BEGIN_DATE = "engineeringProject.beginDate";
	
	/**
	 * engineeringProject.type
	 */
	public static final String TYPE = "engineeringProject.type";
	
	/**
	 * vehicleInfo
	 */
	public static final String VEHICLE_INFOL = "vehicleInfo";
	
	/**
	 * vehicleInfo.number
	 */
	public static final String VEHICLE_INFOL_NUMBER = "vehicleInfo.number";
	
	/**
	 * startPumpDate
	 */
	public static final String START_PUMP_DATE = "startPumpDate";
	
	/**
	 * pumpParts（泵送部位）
	 */
	public static final String PUMP_PARTS = "pumpParts";
	
	/**
	 * endPumpDate
	 */
	public static final String END_PUMP_DATE = "endPumpDate";
	
	/**
	 * 项目
	 */
	@ManyToOne(targetEntity = EngineeringProject.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "engineeringProjectId")
	private EngineeringProject engineeringProject;
	
	/**
	 * 车辆
	 */
	@ManyToOne(targetEntity = VehicleInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "vehicleInfoId")
	private VehicleInfo vehicleInfo;
	
	/**
	 * 车辆编号
	 */
	private String vehicleNumber;
	
	/**
	 * 工作方量
	 */
	private BigDecimal workVolume;
	
	/**
	 * 完成量，进度条
	 */
	private BigDecimal schedule;
	
	/**
	 * 泵送部位
	 */
	private String pumpParts;
	
	/**
	 * 泵送价
	 */
	private BigDecimal pumpPrice;
	
	/**
	 * 泵送开始时间
	 */
	private Date startPumpDate;
	
	/**
	 * 泵送结束时间
	 */
	private Date endPumpDate;
	
	/**
	 * 加油量
	 */
	private BigDecimal gasVolume;
	
	/**
	 * 油费
	 */
	private BigDecimal gasPrice;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 新完成方量
	 */
	@Transient
	private BigDecimal newSchedule;
	
	/**
	 * 新油费
	 */
	@Transient
	private BigDecimal newGasPrice;
	
	/**
	 * 油价消费人
	 */
	@Transient
	private Employee employee;
	
	/**
	 * 开机人列表
	 */
	@Transient
	private List<ProjectEmployeeDetail> projectEmployeeDetailList;
	
	/**
	 * @return the engineeringProject
	 */
	public EngineeringProject getEngineeringProject() {
		return engineeringProject;
	}

	/**
	 * @param engineeringProject the engineeringProject to set
	 */
	public void setEngineeringProject(EngineeringProject engineeringProject) {
		this.engineeringProject = engineeringProject;
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
		if (StringUtil.isBlank(vehicleNumber)) {
			if (vehicleInfo != null) {
				vehicleNumber = vehicleInfo.getNumber();
			}
		}
		return vehicleNumber;
	}

	/**
	 * @param vehicleNumber the vehicleNumber to set
	 */
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	/**
	 * @return the workVolume
	 */
	public BigDecimal getWorkVolume() {
		if (BigDecimalUtil.isNullOrZero(workVolume)) {
			workVolume = Constants.BIGDECIMAL_ZERO;
		}
		return workVolume;
	}

	/**
	 * @param workVolume the workVolume to set
	 */
	public void setWorkVolume(BigDecimal workVolume) {
		this.workVolume = workVolume;
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
	 * @return the newSchedule
	 */
	public BigDecimal getNewSchedule() {
		return newSchedule;
	}

	/**
	 * @param newSchedule the newSchedule to set
	 */
	public void setNewSchedule(BigDecimal newSchedule) {
		this.newSchedule = newSchedule;
	}

	/**
	 * @return the newGasPrice
	 */
	public BigDecimal getNewGasPrice() {
		return newGasPrice;
	}

	/**
	 * @param newGasPrice the newGasPrice to set
	 */
	public void setNewGasPrice(BigDecimal newGasPrice) {
		this.newGasPrice = newGasPrice;
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
	 * @return the projectEmployeeDetailList
	 */
	public List<ProjectEmployeeDetail> getProjectEmployeeDetailList() {
		return projectEmployeeDetailList;
	}

	/**
	 * @param projectEmployeeDetailList the projectEmployeeDetailList to set
	 */
	public void setProjectEmployeeDetailList(
			List<ProjectEmployeeDetail> projectEmployeeDetailList) {
		this.projectEmployeeDetailList = projectEmployeeDetailList;
	}

}
