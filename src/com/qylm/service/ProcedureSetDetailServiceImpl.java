package com.qylm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qylm.dao.ProcedureSetDetailDao;
import com.qylm.entity.ProcedureSetDetail;

@Service("procedureSetDetailService")
public class ProcedureSetDetailServiceImpl extends GenericServiceImpl<ProcedureSetDetail, Integer> implements ProcedureSetDetailService {

	@Autowired
	protected ProcedureSetDetailServiceImpl(ProcedureSetDetailDao procedureSetDetailDao) {
		super(procedureSetDetailDao);
	}

}
