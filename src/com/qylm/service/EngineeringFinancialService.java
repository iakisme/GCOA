package com.qylm.service;

import com.qylm.entity.EngineeringFinancial;

public interface EngineeringFinancialService extends GenericService<EngineeringFinancial, Integer> {

	/**
	 * 保存时，更新项目的预算金额和单价
	 * @param engineeringFinancial
	 */
	public void saveEngineeringFinancial(EngineeringFinancial engineeringFinancial);
	
	/**
	 * 更新时，更新项目的预算金额和单价
	 * @param engineeringFinancial
	 */
	public void updateEngineeringFinancial(EngineeringFinancial engineeringFinancial);
	
}
