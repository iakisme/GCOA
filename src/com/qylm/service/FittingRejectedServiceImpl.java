package com.qylm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qylm.dao.FittingRejectedDao;
import com.qylm.entity.FittingRejected;

@Service("fittingRejectedService")
public class FittingRejectedServiceImpl extends GenericServiceImpl<FittingRejected, Integer> implements FittingRejectedService {

	@Autowired
	protected FittingRejectedServiceImpl(FittingRejectedDao fittingRejectedDao) {
		super(fittingRejectedDao);
	}

}
