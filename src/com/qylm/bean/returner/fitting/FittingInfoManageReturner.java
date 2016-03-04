package com.qylm.bean.returner.fitting;

import com.qylm.bean.fitting.FittingInfoManageBean;
import com.qylm.bean.returner.Returner;
import com.qylm.dto.fitting.FittingInfoManageDto;
import com.qylm.entity.FittingInfo;

public class FittingInfoManageReturner extends Returner<FittingInfoManageBean, FittingInfoManageDto, FittingInfo> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8484353104040834829L;

	public FittingInfoManageReturner(FittingInfoManageDto fittingInfoManageDto, int currentPage) {
		super(fittingInfoManageDto, currentPage);
	}
	
	@Override
	public String returnOnly(FittingInfoManageBean backBean) {
		backBean.setFittingInfoManageDto(super.dto);
		return backBean.back(currentPage);
	}

}
