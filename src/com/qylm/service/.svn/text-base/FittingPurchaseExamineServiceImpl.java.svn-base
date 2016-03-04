package com.qylm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qylm.dao.FittingPurchaseDao;
import com.qylm.dao.FittingPurchaseExamineDao;
import com.qylm.entity.FittingPurchase;
import com.qylm.entity.FittingPurchaseExamine;

@Service("fittingPurchaseExamineService")
public class FittingPurchaseExamineServiceImpl extends GenericServiceImpl<FittingPurchaseExamine, Integer> implements FittingPurchaseExamineService {

	@Autowired
	private FittingPurchaseDao fittingPurchaseDao;
	
	@Autowired
	protected FittingPurchaseExamineServiceImpl(FittingPurchaseExamineDao fittingPurchaseExamineDao) {
		super(fittingPurchaseExamineDao);
	}

	public void updateExamine(FittingPurchaseExamine fittingPurchaseExamine, FittingPurchase fittingPurchase) {
		genericDAO.updateEntity(fittingPurchaseExamine);
		fittingPurchaseDao.updateEntity(fittingPurchase);
	}

}
