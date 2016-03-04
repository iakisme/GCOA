package com.qylm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qylm.dao.FittingRejectedDetailDao;
import com.qylm.entity.FittingRejectedDetail;

@Service("fittingRejectedDetailService")
public class FittingRejectedDetailServiceImpl extends GenericServiceImpl<FittingRejectedDetail, Integer> implements FittingRejectedDetailService {

	@Autowired
	protected FittingRejectedDetailServiceImpl(FittingRejectedDetailDao fittingRejectedDetailDao) {
		super(fittingRejectedDetailDao);
	}

}
