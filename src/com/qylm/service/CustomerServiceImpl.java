package com.qylm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qylm.dao.CustomerDao;
import com.qylm.entity.Customer;

@Service("customerService")
public class CustomerServiceImpl extends GenericServiceImpl<Customer, Integer> implements CustomerService {

	@Autowired
	protected CustomerServiceImpl(CustomerDao customerDao) {
		super(customerDao);
	}

}
