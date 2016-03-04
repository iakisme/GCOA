package com.qylm.logic.engineering;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qylm.dao.CustomerFinancialDao;
import com.qylm.dao.EngineeringFinancialDao;
import com.qylm.entity.CustomerFinancial;
import com.qylm.entity.EngineeringFinancial;

public class CustomerFinancialLogic {
	
	@Autowired
	private CustomerFinancialDao customerFinancialDao;
	
	@Autowired
	private EngineeringFinancialDao engineeringFinancialDao;
	
	public void updateCustomerFinancial(CustomerFinancial customerFinancial,
			List<EngineeringFinancial> engineeringFinancialList) {
		customerFinancialDao.updateEntity(customerFinancial);
		if (engineeringFinancialList != null && !engineeringFinancialList.isEmpty()) {
			engineeringFinancialDao.saveOrUpdateAll(engineeringFinancialList);
		}
	}
	
}
