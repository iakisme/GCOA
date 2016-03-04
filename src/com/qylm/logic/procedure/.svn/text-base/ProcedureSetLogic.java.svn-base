package com.qylm.logic.procedure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qylm.dao.ProcedureSetDao;
import com.qylm.dao.ProcedureSetDetailDao;
import com.qylm.entity.ProcedureSet;
import com.qylm.entity.ProcedureSetDetail;

public class ProcedureSetLogic {

	@Autowired
	private ProcedureSetDao procedureSetDao;
	
	@Autowired
	private ProcedureSetDetailDao procedureSetDetailDao;
	
	public void saveProcedureSet(ProcedureSet procedureSet,
			List<ProcedureSetDetail> procedureSetDetailList) {
		procedureSetDao.saveEntity(procedureSet);
		procedureSetDetailDao.saveEntityAll(procedureSetDetailList);
	}

	public void updateProcedureSet(ProcedureSet procedureSet,
			List<ProcedureSetDetail> procedureSetDetailList) {
		procedureSetDao.updateEntity(procedureSet);
		String sql = "delete From ProcedureSetDetail where procedureSetId = " + procedureSet.getId();
		procedureSetDetailDao.bulkUpdate(sql);
		procedureSetDetailDao.saveEntityAll(procedureSetDetailList);
	}
	
}
