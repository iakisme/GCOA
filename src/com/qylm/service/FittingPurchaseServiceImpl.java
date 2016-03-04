package com.qylm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qylm.dao.FittingPurchaseDao;
import com.qylm.entity.FittingPurchase;
import com.qylm.entity.FittingPurchaseDetail;
import com.qylm.logic.procedure.FittingPurchaseLogic;

@Service("fittingPurchaseService")
public class FittingPurchaseServiceImpl extends GenericServiceImpl<FittingPurchase, Integer> implements FittingPurchaseService {
	
	@Autowired
	private FittingPurchaseLogic fittingPurchaseLogic;

	@Autowired
	protected FittingPurchaseServiceImpl(FittingPurchaseDao fittingPurchaseDao) {
		super(fittingPurchaseDao);
	}

	public void updateFittingPurchase(FittingPurchase fittingPurchase,
			List<FittingPurchaseDetail> fittingPurchaseDetailList) {
		fittingPurchaseLogic.updateFittingPurchase(fittingPurchase, fittingPurchaseDetailList);
	}

	public void updateFittingPurchase(FittingPurchase fittingPurchase) {
		fittingPurchaseLogic.updateFittingPurchase(fittingPurchase);
	}

}
