package com.qylm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qylm.dao.CustomerFinancialDao;
import com.qylm.entity.CustomerFinancial;
import com.qylm.entity.EngineeringFinancial;
import com.qylm.logic.engineering.CustomerFinancialLogic;

@Service("customerFinancialService")
public class CustomerFinancialServiceImpl extends GenericServiceImpl<CustomerFinancial, Integer> implements CustomerFinancialService {

	@Autowired
	private CustomerFinancialLogic customerFinancialLogic;
	
	@Autowired
	protected CustomerFinancialServiceImpl(CustomerFinancialDao customerFinancialDao) {
		super(customerFinancialDao);
	}

	public void updateCustomerFinancial(CustomerFinancial customerFinancial,
			List<EngineeringFinancial> engineeringFinancialList) {
		customerFinancialLogic.updateCustomerFinancial(customerFinancial, engineeringFinancialList);
	}

}
