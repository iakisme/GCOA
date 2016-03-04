package com.qylm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qylm.dao.FinancialPayDetailDao;
import com.qylm.entity.FinancialPayDetail;

@Service("financialPayDetailService")
public class FinancialPayDetailServiceImpl extends GenericServiceImpl<FinancialPayDetail, Integer> implements FinancialPayDetailService {

	@Autowired
	protected FinancialPayDetailServiceImpl(FinancialPayDetailDao financialPayDetailDao) {
		super(financialPayDetailDao);
	}

}
