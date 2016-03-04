package com.qylm.service;

import java.util.List;

import com.qylm.entity.ProcedureSet;
import com.qylm.entity.ProcedureSetDetail;

public interface ProcedureSetService extends GenericService<ProcedureSet, Integer> {

	/**
	 * 保存审核设定信息，及审核情况
	 * @param procedureSet
	 * @param procedureSetDetailList
	 */
	public void saveProcedureSet(ProcedureSet procedureSet, List<ProcedureSetDetail> procedureSetDetailList);
	
	/**
	 * 修改审核设定信息和保存或者修改审核情况
	 * @param procedureSet
	 * @param procedureSetDetailList
	 */
	public void updateProcedureSet(ProcedureSet procedureSet, List<ProcedureSetDetail> procedureSetDetailList);
	
}
