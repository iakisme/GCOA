package com.qylm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qylm.dao.FittingStorageDao;
import com.qylm.entity.FittingStorage;
import com.qylm.entity.FittingStorageDetail;
import com.qylm.logic.fitting.FittingStorageLogic;

@Service("fittingStorageService")
public class FittingStorageServiceImpl extends GenericServiceImpl<FittingStorage, Integer> implements FittingStorageService {
	
	@Autowired
	private FittingStorageLogic fittingStorageLogic;
	
	@Autowired
	protected FittingStorageServiceImpl(FittingStorageDao fittingStorageDao) {
		super(fittingStorageDao);
	}

	public void saveFittingStorage(FittingStorage fittingStorage,
			List<FittingStorageDetail> fittingStorageDetailList) {
		fittingStorageLogic.saveFittingStorage(fittingStorage, fittingStorageDetailList);
	}

	public void updateFittingStorage(FittingStorage fittingStorage,
			List<FittingStorageDetail> fittingStorageDetailList) {
		fittingStorageLogic.updateFittingStorage(fittingStorage, fittingStorageDetailList);
	}

}
