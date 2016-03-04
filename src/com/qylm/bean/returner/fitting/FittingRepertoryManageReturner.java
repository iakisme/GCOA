package com.qylm.bean.returner.fitting;

import com.qylm.bean.fitting.FittingRepertoryManageBean;
import com.qylm.bean.returner.Returner;
import com.qylm.dto.fitting.FittingRepertoryManageDto;
import com.qylm.entity.FittingRepertory;

public class FittingRepertoryManageReturner extends Returner<FittingRepertoryManageBean, FittingRepertoryManageDto, FittingRepertory> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -762722608894881526L;

	public FittingRepertoryManageReturner(FittingRepertoryManageDto fittingRepertoryManageDto, int currentPage) {
		super(fittingRepertoryManageDto, currentPage);
	}
	
	@Override
	public String returnOnly(FittingRepertoryManageBean backBean) {
		backBean.setFittingRepertoryManageDto(super.dto);
		return backBean.back(currentPage);
	}

}
