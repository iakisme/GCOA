package com.qylm.logic.engineering;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.qylm.common.utils.DateUtil;
import com.qylm.dao.EngineeringFinancialDao;
import com.qylm.dao.EngineeringProjectDao;
import com.qylm.dao.EngineeringProjectDetailDao;
import com.qylm.dao.FinancialPayDetailDao;
import com.qylm.entity.EngineeringFinancial;
import com.qylm.entity.EngineeringProject;
import com.qylm.entity.EngineeringProjectDetail;
import com.qylm.entity.FinancialPayDetail;

public class EngineeringProjectDetailLogic {
	
	@Autowired
	private EngineeringProjectDao engineeringProjectDao;
	
	@Autowired
	private EngineeringProjectDetailDao engineeringProjectDetailDao;
	
	@Autowired
	private EngineeringFinancialDao engineeringFinancialDao;
	
	@Autowired
	private FinancialPayDetailDao financialPayDetailDao;
	
	public void updateEngineeringProjectDetail(EngineeringProjectDetail engineeringProjectDetail, boolean state) {
		engineeringProjectDetailDao.updateEntity(engineeringProjectDetail);
		EngineeringProject engineeringProject = engineeringProjectDetail.getEngineeringProject();
//		if (state) {
//			// 创建项目付款明细
//			// 如果存在就查询出来，如果不存在就创建
//			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EngineeringFinancial.class);
//			detachedCriteria.add(Restrictions.eq(EngineeringFinancial.ENGINEERING_PROJECT, engineeringProject));
//			List<EngineeringFinancial> engineeringFinancialList = engineeringFinancialDao.getByDetachedCriteria(detachedCriteria);
//			EngineeringFinancial engineeringFinancial;
//			if (engineeringFinancialList.isEmpty()) {
//				engineeringFinancial = new EngineeringFinancial();
//			} else {
//				engineeringFinancial = engineeringFinancialList.get(0);
//			}
//			engineeringFinancial.setEngineeringProject(engineeringProject);
//			engineeringFinancial.setState(true);
//			Date nowyyyymmddhhmmss = DateUtil.getNowyyyymmddhhmmss();
//			engineeringFinancial.setCreateDate(nowyyyymmddhhmmss);
//			engineeringFinancial.setCreater(engineeringProjectDetail.getCreater());
//			engineeringFinancialDao.saveEntity(engineeringFinancial);
//			// 付款明细
//			FinancialPayDetail financialPayDetail = new FinancialPayDetail();
//			financialPayDetail.setEngineeringFinancial(engineeringFinancial);
//			// 消费人，默认是项目负责人
//			financialPayDetail.setEmployee(engineeringProjectDetail.getEmployee() == null ? engineeringProject.getEmployee() : engineeringProjectDetail.getEmployee());
//			financialPayDetail.setMoney(engineeringProjectDetail.getNewGasPrice());
//			financialPayDetail.setPayDate(nowyyyymmddhhmmss);
//			financialPayDetail.setCreateDate(nowyyyymmddhhmmss);
//			financialPayDetail.setRemark("油价消费");
//			financialPayDetail.setCreater(engineeringProjectDetail.getCreater());
//			financialPayDetail.setState(true);
//			financialPayDetailDao.saveEntity(financialPayDetail);
//		}
		engineeringProjectDao.updateEntity(engineeringProject);
	}
	
}
