package com.qylm.bean.returner.financial;

import com.qylm.bean.financial.CustomerFinancialManageBean;
import com.qylm.bean.returner.Returner;
import com.qylm.dto.financial.CustomerFinancialManageDto;
import com.qylm.entity.CustomerFinancial;

public class CustomerFinancialManageReturner extends Returner<CustomerFinancialManageBean, CustomerFinancialManageDto, CustomerFinancial> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4027825955913193792L;

	public CustomerFinancialManageReturner(CustomerFinancialManageDto customerFinancialManageDto, int currentPage) {
		super(customerFinancialManageDto, currentPage);
	}
	
	@Override
	public String returnOnly(CustomerFinancialManageBean backBean) {
		backBean.setCustomerFinancialManageDto(super.dto);
		return backBean.back(currentPage);
	}

}
