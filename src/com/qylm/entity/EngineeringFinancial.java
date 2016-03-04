package com.qylm.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 工程收支明细持久化类
 * @author smj
 */
@Entity
@Table(uniqueConstraints = {}, name = "engineeringfinancial")
public class EngineeringFinancial extends BaseEntity {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 890230263307459842L;
	
	/**
	 * engineeringProject
	 */
	public static final String ENGINEERING_PROJECT = "engineeringProject";
	
	/**
	 * customerFinancial
	 */
	public static final String CUSTOMER_FINANCIAL = "customerFinancial";
	
	/**
	 * 客户收支明细
	 */
	@ManyToOne(targetEntity = CustomerFinancial.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "customerFinancialId")
	private CustomerFinancial customerFinancial;

	/**
	 * 项目
	 */
	@ManyToOne(targetEntity = EngineeringProject.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "engineeringProjectId")
	private EngineeringProject engineeringProject;

	/**
	 * @return the customerFinancial
	 */
	public CustomerFinancial getCustomerFinancial() {
		return customerFinancial;
	}

	/**
	 * @param customerFinancial the customerFinancial to set
	 */
	public void setCustomerFinancial(CustomerFinancial customerFinancial) {
		this.customerFinancial = customerFinancial;
	}

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

}
