package com.qylm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qylm.dao.FittingLibraryDao;
import com.qylm.entity.FittingLibrary;
import com.qylm.entity.FittingLibraryDetail;
import com.qylm.exception.FittingLibraryException;
import com.qylm.logic.fitting.FittingLibraryLogic;

@Service("fittingLibraryService")
public class FittingLibraryServiceImpl extends GenericServiceImpl<FittingLibrary, Integer> implements FittingLibraryService {
	
	@Autowired
	private FittingLibraryLogic fittingLibraryLogic;

	@Autowired
	protected FittingLibraryServiceImpl(FittingLibraryDao fittingLibraryDao) {
		super(fittingLibraryDao);
	}
	
	public void saveFittingLibrary(FittingLibrary fittingLibrary,
			List<FittingLibraryDetail> fittingLibraryDetailList) {
		fittingLibraryLogic.saveFittingLibrary(fittingLibrary, fittingLibraryDetailList);
	}

	public void updateFittingLibrary(FittingLibrary fittingLibrary,
			List<FittingLibraryDetail> fittingLibraryDetailList) throws FittingLibraryException {
		fittingLibraryLogic.updateFittingLibrary(fittingLibrary, fittingLibraryDetailList);
	}

}
