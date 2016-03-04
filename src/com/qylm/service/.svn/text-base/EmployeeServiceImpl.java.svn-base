package com.qylm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qylm.dao.EmployeeDao;
import com.qylm.entity.Employee;

@Service("employeeService")
public class EmployeeServiceImpl extends GenericServiceImpl<Employee, Integer> implements EmployeeService {

	@Autowired
	protected EmployeeServiceImpl(EmployeeDao employeeDao) {
		super(employeeDao);
	}

}
