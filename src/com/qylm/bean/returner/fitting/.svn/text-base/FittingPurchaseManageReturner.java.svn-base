package com.qylm.bean.returner.fitting;

import com.qylm.bean.fitting.FittingPurchaseManageBean;
import com.qylm.bean.returner.Returner;
import com.qylm.dto.fitting.FittingPurchaseManageDto;
import com.qylm.entity.FittingPurchase;

public class FittingPurchaseManageReturner extends Returner<FittingPurchaseManageBean, FittingPurchaseManageDto, FittingPurchase> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6448235457479829851L;

	public FittingPurchaseManageReturner(FittingPurchaseManageDto fittingPurchaseManageDto, int currentPage) {
		super(fittingPurchaseManageDto, currentPage);
	}
	
	@Override
	public String returnOnly(FittingPurchaseManageBean backBean) {
		backBean.setFittingPurchaseManageDto(super.dto);
		return backBean.back(currentPage);
	}

}
