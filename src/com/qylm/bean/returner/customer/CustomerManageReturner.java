package com.qylm.bean.returner.customer;

import com.qylm.bean.customer.CustomerManageBean;
import com.qylm.bean.returner.Returner;
import com.qylm.dto.customer.CustomerManageDto;
import com.qylm.entity.Customer;

public class CustomerManageReturner extends Returner<CustomerManageBean, CustomerManageDto, Customer> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7922763544282067976L;

	public CustomerManageReturner(CustomerManageDto customerManageDto, int currentPage) {
		super(customerManageDto, currentPage);
	}
	
	@Override
	public String returnOnly(CustomerManageBean backBean) {
		backBean.setCustomerManageDto(super.dto);
		return backBean.back(currentPage);
	}

}
