package com.qylm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qylm.dao.ProcedureSetDao;
import com.qylm.entity.ProcedureSet;
import com.qylm.entity.ProcedureSetDetail;
import com.qylm.logic.procedure.ProcedureSetLogic;

@Service("procedureSetService")
public class ProcedureSetServiceImpl extends GenericServiceImpl<ProcedureSet, Integer> implements ProcedureSetService {
	
	@Autowired
	private ProcedureSetLogic procedureSetLogic;

	@Autowired
	protected ProcedureSetServiceImpl(ProcedureSetDao procedureSetDao) {
		super(procedureSetDao);
	}

	public void saveProcedureSet(ProcedureSet procedureSet,
			List<ProcedureSetDetail> procedureSetDetailList) {
		procedureSetLogic.saveProcedureSet(procedureSet, procedureSetDetailList);
	}

	public void updateProcedureSet(ProcedureSet procedureSet,
			List<ProcedureSetDetail> procedureSetDetailList) {
		procedureSetLogic.updateProcedureSet(procedureSet, procedureSetDetailList);
	}

}
