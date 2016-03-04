package com.qylm.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.qylm.common.utils.StringUtil;

/**
 * 工程项目详细下的工作人员详细持久化类
 * @author smj
 */
@Entity
@Table(uniqueConstraints = {}, name = "projectemployee_detail")
public class ProjectEmployeeDetail extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8507274223323659768L;

	/**
	 * engineeringProjectDetail
	 */
	public static final String ENGINEERING_PROJECT_DETAIL = "engineeringProjectDetail";
	
	/**
	 * engineeringProjectDetail.startPumpDate
	 */
	public static final String ENGINEERING_PROJECT_DETAIL_START_PUMP_DATE = "engineeringProjectDetail.startPumpDate";
	
	/**
	 * engineeringProjectDetail.endPumpDate
	 */
	public static final String ENGINEERING_PROJECT_DETAIL_END_PUMP_DATE = "engineeringProjectDetail.endPumpDate";
	
	/**
	 * engineeringProjectDetail.engineeringProject
	 */
	public static final String ENGINEERING_PROJECT_DETAIL_ENGINEERING_PROJECT = "engineeringProjectDetail.engineeringProject";
	
	/**
	 * 别名.engineeringProject
	 */
	public static final String ENGINEERING_PROJECT = "engineeringProject";
	
	/**
	 * 别名.engineeringProject.type
	 */
	public static final String ENGINEERING_PROJECT_TYPE = "engineeringProject.type";
	
	/**
	 * employee
	 */
	public static final String EMPLOYEE = "employee";
	
	/**
	 * 工作状态
	 */
	public static final String TYPE = "type";
	
	/**
	 * 工作状态:通知中
	 */
	public static final String TYPE_1 = "1";
	
	/**
	 * 工作状态:已接手
	 */
	public static final String TYPE_2 = "2";
	
	/**
	 * 工作状态:已拒绝
	 */
	public static final String TYPE_3 = "3";
	
	/**
	 * 工作状态:已结束
	 */
	public static final String TYPE_4 = "4";

	/**
	 * 项目详细
	 */
	@ManyToOne(targetEntity = EngineeringProjectDetail.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "engineeringProjectDetailId")
	private EngineeringProjectDetail engineeringProjectDetail;
	
	/**
	 * 员工
	 */
	@ManyToOne(targetEntity = Employee.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "employeeId")
	private Employee employee;
	
	/**
	 * 未在员工信息表内的员工姓名
	 */
	private String name;
	
	/**
	 * 工作状态
	 * 1：通知中
	 * 2：已接手
	 * 3：已拒绝
	 * 4：已结束
	 */
	private String type;
	
	/**
	 * 此方法用于页面显示，获取项目状态的颜色样子
	 * @return
	 */
	public String getTypeColor() {
		int i = Integer.valueOf(type);
		String color;
		switch (i) {
		case 1:
			color = "#000000";// 黑色
			break;
		case 2:
			color = "#FF4040";// 暗红色
			break;
		case 3:
			color = "#FF0000";// 红色
			break;
		case 4:
			color = "#00FF00";// 绿色
			break;
		default:
			color = "#000000";// 黑色
			break;
		}
		return color;
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
	 * @return the name
	 */
	public String getName() {
		if (StringUtil.isBlank(name)) {
			name = employee.getName();
		}
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	
}
