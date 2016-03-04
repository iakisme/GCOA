package com.qylm.logic.procedure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;

import com.qylm.common.MothedUtil;
import com.qylm.common.utils.DateUtil;
import com.qylm.dao.FittingPurchaseDao;
import com.qylm.dao.FittingPurchaseDetailDao;
import com.qylm.dao.FittingPurchaseExamineDao;
import com.qylm.dao.ProcedureSetDao;
import com.qylm.dao.ProcedureSetDetailDao;
import com.qylm.entity.FittingPurchase;
import com.qylm.entity.FittingPurchaseDetail;
import com.qylm.entity.FittingPurchaseExamine;
import com.qylm.entity.ProcedureSet;
import com.qylm.entity.ProcedureSetDetail;

public class FittingPurchaseLogic {

	@Autowired
	private FittingPurchaseDao fittingPurchaseDao;
	
	@Autowired
	private FittingPurchaseDetailDao fittingPurchaseDetailDao;
	
	@Autowired
	private ProcedureSetDao procedureSetDao;
	
	@Autowired
	private ProcedureSetDetailDao procedureSetDetailDao;
	
	@Autowired
	private FittingPurchaseExamineDao fittingPurchaseExamineDao;
	
	public void updateFittingPurchase(FittingPurchase fittingPurchase,
			List<FittingPurchaseDetail> fittingPurchaseDetailList) {
		List<FittingPurchaseExamine> fittingPurchaseExamineList = new ArrayList<FittingPurchaseExamine>();
		// 如果提交了申请，就建立审核信息
		if (FittingPurchase.STATE_2.equals(fittingPurchase.getState())) {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ProcedureSet.class);
			detachedCriteria.add(Restrictions.eq(ProcedureSet.APPLY_NUMBER, ProcedureSet.APPLY_NUMBER_1));
			MothedUtil.getBelongingUser(detachedCriteria, fittingPurchase.getCreater());
			List<ProcedureSet> procedureSetList = procedureSetDao.getByDetachedCriteria(detachedCriteria);
			if (!procedureSetList.isEmpty()) {
				detachedCriteria = DetachedCriteria.forClass(ProcedureSetDetail.class);
				detachedCriteria.createAlias(ProcedureSetDetail.PROCEDURE_SET, ProcedureSetDetail.PROCEDURE_SET, JoinType.LEFT_OUTER_JOIN);
				detachedCriteria.createAlias(ProcedureSetDetail.AUDITOR, ProcedureSetDetail.AUDITOR, JoinType.LEFT_OUTER_JOIN);
				detachedCriteria.add(Restrictions.eq(ProcedureSetDetail.PROCEDURE_SET, procedureSetList.get(0)));
				detachedCriteria.addOrder(Order.desc(ProcedureSetDetail.SEQUENCE));
				List<ProcedureSetDetail> procedureSetDetailList = procedureSetDetailDao.getByDetachedCriteria(detachedCriteria);
				if (!procedureSetDetailList.isEmpty()) {
					// 验证申请人是否是审核人员之一
					// 如果是审核人员，如果是最终的审核人员，就直接通过，并不需要建立审核流程
					if (fittingPurchase.getCreater().equals(procedureSetDetailList.get(0).getAuditor())) {
						fittingPurchase.setBudgetPrice(fittingPurchase.getBudgetPrice());
						fittingPurchase.setState(FittingPurchase.STATE_4);
					} else {
						// 验证是否是审核人员之一
						boolean state = false;
						int sequence = 1;
						for (ProcedureSetDetail procedureSetDetail : procedureSetDetailList) {
							if (procedureSetDetail.getAuditor().equals(fittingPurchase.getCreater())) {
								state = true;
								sequence = procedureSetDetail.getSequence();
								break;
							}
						}
						if (state) {
							Collections.sort(procedureSetDetailList);
							for (ProcedureSetDetail procedureSetDetail : procedureSetDetailList) {
								if (procedureSetDetail.getSequence() > sequence) {
									create(fittingPurchase,	fittingPurchaseExamineList,	procedureSetDetail);
								}
							}
						} else{
							for (ProcedureSetDetail procedureSetDetail : procedureSetDetailList) {
								create(fittingPurchase,	fittingPurchaseExamineList, procedureSetDetail);
							}
						}
					}
				} else {
					getThrough(fittingPurchase);
				}
			} else {
				getThrough(fittingPurchase);
			}
		}
		fittingPurchaseDao.updateEntity(fittingPurchase);
		if (fittingPurchaseDetailList != null && !fittingPurchaseDetailList.isEmpty()) {
			fittingPurchaseDetailDao.saveOrUpdateAll(fittingPurchaseDetailList);
		}
		if (!fittingPurchaseExamineList.isEmpty()) {
			fittingPurchaseExamineDao.saveEntityAll(fittingPurchaseExamineList);
		}
	}
	
	public void updateFittingPurchase(FittingPurchase fittingPurchase) {
		fittingPurchaseDao.updateEntity(fittingPurchase);
		if (fittingPurchase.getFittingPurchaseDetailList() != null && !fittingPurchase.getFittingPurchaseDetailList().isEmpty()) {
			fittingPurchaseDetailDao.updateEntityAll(fittingPurchase.getFittingPurchaseDetailList());
		}
	}

	/**
	 * 建立审核信息
	 * @param fittingPurchase
	 * @param fittingPurchaseExamineList
	 * @param procedureSetDetail
	 */
	private void create(FittingPurchase fittingPurchase,
			List<FittingPurchaseExamine> fittingPurchaseExamineList,
			ProcedureSetDetail procedureSetDetail) {
		FittingPurchaseExamine fittingPurchaseExamine = new FittingPurchaseExamine();
		fittingPurchaseExamine.setAuditor(procedureSetDetail.getAuditor());
		fittingPurchaseExamine.setCreateDate(DateUtil.getNowyyyymmddhhmmss());
		fittingPurchaseExamine.setFittingPurchase(fittingPurchase);
		fittingPurchaseExamine.setSequence(procedureSetDetail.getSequence());
		fittingPurchaseExamine.setState(FittingPurchaseExamine.STATE_1);
		fittingPurchaseExamine.setCreater(fittingPurchase.getCreater());
		fittingPurchaseExamineList.add(fittingPurchaseExamine);
	}
	
	/**
	 * 直接通过
	 * @param fittingPurchase
	 */
	private void getThrough(FittingPurchase fittingPurchase) {
		fittingPurchase.setState(FittingPurchase.STATE_4);
	}
}
