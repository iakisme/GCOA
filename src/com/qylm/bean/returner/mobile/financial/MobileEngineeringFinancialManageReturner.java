package com.qylm.bean.returner.mobile.financial;

import com.qylm.bean.mobile.financial.MobileEngineeringFinancialManageBean;
import com.qylm.bean.returner.Returner;
import com.qylm.dto.mobile.financial.MobileEngineeringFinancialManageDto;
import com.qylm.entity.CustomerFinancial;

public class MobileEngineeringFinancialManageReturner extends Returner<MobileEngineeringFinancialManageBean, MobileEngineeringFinancialManageDto, CustomerFinancial> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6864403038262801612L;

	public MobileEngineeringFinancialManageReturner(MobileEngineeringFinancialManageDto mobileEngineeringFinancialManageDto, int currentPage) {
		super(mobileEngineeringFinancialManageDto, currentPage);
	}
	
	@Override
	public String returnOnly(MobileEngineeringFinancialManageBean backBean) {
		backBean.setMobileEngineeringFinancialManageDto(super.dto);
		return backBean.back(currentPage);
	}

}
