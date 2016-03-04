package com.qylm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qylm.dao.FittingLibraryDetailDao;
import com.qylm.entity.FittingLibraryDetail;

@Service("fittingLibraryDetailService")
public class FittingLibraryDetailServiceImpl extends GenericServiceImpl<FittingLibraryDetail, Integer> implements FittingLibraryDetailService {

	@Autowired
	protected FittingLibraryDetailServiceImpl(FittingLibraryDetailDao fittingLibraryDetailDao) {
		super(fittingLibraryDetailDao);
	}

}
