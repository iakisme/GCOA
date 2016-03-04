package com.qylm.bean.returner.personnel;

import com.qylm.bean.personnel.EmployeeManageBean;
import com.qylm.bean.returner.Returner;
import com.qylm.dto.personnel.EmployeeManageDto;
import com.qylm.entity.Employee;

public class EmployeeManageReturner extends Returner<EmployeeManageBean, EmployeeManageDto, Employee> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2733600261944474085L;

	public EmployeeManageReturner(EmployeeManageDto employeeManageDto, int currentPage) {
		super(employeeManageDto, currentPage);
	}
	
	@Override
	public String returnOnly(EmployeeManageBean backBean) {
		backBean.setEmployeeManageDto(super.dto);
		return backBean.back(currentPage);
	}

}
