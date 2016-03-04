package com.qylm.service;

import com.qylm.entity.EngineeringProjectDetail;

public interface EngineeringProjectDetailService extends GenericService<EngineeringProjectDetail, Integer> {

	/**
	 * 更新工程项目详细，并更新工作工程的方量，并添加项目油费消费信息
	 * @param engineeringProjectDetail
	 * @param state 是否添加项目油费消费
	 */
	public void updateEngineeringProjectDetail(EngineeringProjectDetail engineeringProjectDetail, boolean state);
	
}
