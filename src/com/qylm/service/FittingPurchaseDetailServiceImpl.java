package com.qylm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qylm.dao.FittingPurchaseDetailDao;
import com.qylm.entity.FittingPurchaseDetail;

@Service("fittingPurchaseDetailService")
public class FittingPurchaseDetailServiceImpl extends GenericServiceImpl<FittingPurchaseDetail, Integer> implements FittingPurchaseDetailService {

	@Autowired
	protected FittingPurchaseDetailServiceImpl(FittingPurchaseDetailDao fittingPurchaseDetailDao) {
		super(fittingPurchaseDetailDao);
	}

}
