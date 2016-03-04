package com.qylm.service;

import java.util.List;

import com.qylm.entity.FittingPurchase;
import com.qylm.entity.FittingPurchaseDetail;

public interface FittingPurchaseService extends GenericService<FittingPurchase, Integer> {

	/**
	 * 更新时，同时更新或者增加采购详细
	 * @param fittingPurchase
	 * @param fittingPurchaseDetailList
	 */
	public void updateFittingPurchase(FittingPurchase fittingPurchase, List<FittingPurchaseDetail> fittingPurchaseDetailList);
	
	/**
	 * 更新时，同时更新或者增加采购详细
	 * @param fittingPurchase
	 */
	public void updateFittingPurchase(FittingPurchase fittingPurchase);
}
