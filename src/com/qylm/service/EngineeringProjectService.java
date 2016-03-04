package com.qylm.service;

import java.util.List;

import com.qylm.entity.EngineeringProject;
import com.qylm.entity.EngineeringProjectDetail;
import com.qylm.entity.User;

public interface EngineeringProjectService extends GenericService<EngineeringProject, Integer> {
	
	/**
	 * 保存，保存工程项目详细
	 * @param engineeringProject 工程项目
	 * @param engineeringProjectDetailList 工程项目详细
	 */
	public void saveEngineeringProject(EngineeringProject engineeringProject, List<EngineeringProjectDetail> engineeringProjectDetailList);

	/**
	 * 更新时，保存或者更新工程项目详细
	 * @param engineeringProject 工程项目
	 * @param engineeringProjectDetailList 工程项目详细
	 */
	public void updateEngineeringProject(EngineeringProject engineeringProject, List<EngineeringProjectDetail> engineeringProjectDetailList);

	/**
	 * 用于工作状态为完结，并建立一条此项目的收款信息，如果已经存就不建立
	 * @param engineeringProject
	 */
	public void updateEngineeringProjectsAndSaveFinancial(EngineeringProject engineeringProject, User user);
	
	/**
	 * 确认更新时，并发送短信通知
	 * @param engineeringProject
	 * @param engineeringProjectDetailList 工程项目详细
	 */
	public void updateEngineeringProjectSendSms(EngineeringProject engineeringProject, List<EngineeringProjectDetail> engineeringProjectDetailList);
	
}
