package com.qylm.logic.engineering;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;

import com.qylm.common.sms.SmsUtil;
import com.qylm.common.utils.BigDecimalUtil;
import com.qylm.common.utils.DateUtil;
import com.qylm.common.utils.StringUtil;
import com.qylm.constants.Constants;
import com.qylm.constants.SmsConstants;
import com.qylm.dao.CommonSmsDao;
import com.qylm.dao.EngineeringFinancialDao;
import com.qylm.dao.EngineeringProjectDao;
import com.qylm.dao.EngineeringProjectDetailDao;
import com.qylm.dao.ProjectEmployeeDetailDao;
import com.qylm.entity.CommonSms;
import com.qylm.entity.EngineeringProject;
import com.qylm.entity.EngineeringProjectDetail;
import com.qylm.entity.ProjectEmployeeDetail;
import com.qylm.entity.User;

public class EngineeringProjectLogic {
	
	@Autowired
	private EngineeringProjectDao engineeringProjectDao;
	
	@Autowired
	private EngineeringProjectDetailDao engineeringProjectDetailDao;
	
	@Autowired
	private ProjectEmployeeDetailDao projectEmployeeDetailDao;
	
	@Autowired
	private EngineeringFinancialDao engineeringFinancialDao;
	
	@Autowired
	private CommonSmsDao commonSmsDao;
	
	public void saveEngineeringProject(EngineeringProject engineeringProject,
			List<EngineeringProjectDetail> engineeringProjectDetailList) {
		engineeringProjectDao.saveEntity(engineeringProject);
		if (engineeringProjectDetailList != null && !engineeringProjectDetailList.isEmpty()) {
			Date nowyyyymmddhhmmss = DateUtil.getNowyyyymmddhhmmss();
			List<ProjectEmployeeDetail> newDetailList = new ArrayList<ProjectEmployeeDetail>();
			for (EngineeringProjectDetail engineeringProjectDetail : engineeringProjectDetailList) {
				engineeringProjectDetail.setEngineeringProject(engineeringProject);
				engineeringProjectDetail.setCreateDate(nowyyyymmddhhmmss);
				engineeringProjectDetail.setBelongingUser(engineeringProject.getBelongingUser());
				List<ProjectEmployeeDetail> projectEmployeeDetailList = engineeringProjectDetail.getProjectEmployeeDetailList();
				if (projectEmployeeDetailList != null && !projectEmployeeDetailList.isEmpty()) {
					newDetailList.addAll(projectEmployeeDetailList);
				}
			}
			engineeringProjectDetailDao.saveEntityAll(engineeringProjectDetailList);
			if (!newDetailList.isEmpty()) {
				for (ProjectEmployeeDetail projectEmployeeDetail : newDetailList) {
					projectEmployeeDetail.setCreateDate(nowyyyymmddhhmmss);
					projectEmployeeDetail.setBelongingUser(engineeringProject.getBelongingUser());
					projectEmployeeDetail.setType(ProjectEmployeeDetail.TYPE_1);
				}
				projectEmployeeDetailDao.saveOrUpdateAll(newDetailList);
			}
		}
	}
	
	public void updateEngineeringProject(EngineeringProject engineeringProject,
			List<EngineeringProjectDetail> engineeringProjectDetailList) {
		engineeringProjectDao.updateEntity(engineeringProject);
		if (engineeringProjectDetailList != null && !engineeringProjectDetailList.isEmpty()) {
			List<ProjectEmployeeDetail> newDetailList = new ArrayList<ProjectEmployeeDetail>();
			for (EngineeringProjectDetail engineeringProjectDetail : engineeringProjectDetailList) {
				engineeringProjectDetail.setBelongingUser(engineeringProject.getBelongingUser());
				List<ProjectEmployeeDetail> projectEmployeeDetailList = engineeringProjectDetail.getProjectEmployeeDetailList();
				if (projectEmployeeDetailList != null && !projectEmployeeDetailList.isEmpty()) {
					newDetailList.addAll(projectEmployeeDetailList);
				}
			}
			engineeringProjectDetailDao.saveOrUpdateAll(engineeringProjectDetailList);
			if (!newDetailList.isEmpty()) {
				for (ProjectEmployeeDetail projectEmployeeDetail : newDetailList) {
					projectEmployeeDetail.setCreateDate(DateUtil.getNowyyyymmddhhmmss());
				}
				projectEmployeeDetailDao.saveOrUpdateAll(newDetailList);
			}
		}
	}

	public void updateEngineeringProjectsAndSaveFinancial(EngineeringProject engineeringProject, User user) {
		engineeringProject.setPumpPrice(engineeringProject.getCustomer().getPumpPrice());
		// 如果项目的预算金额为空
		if (BigDecimalUtil.isNullOrZero(engineeringProject.getMoney())) {
			if (BigDecimalUtil.isNotNullOrZero(engineeringProject.getCustomer().getPumpPrice())) {
				engineeringProject.setMoney(BigDecimalUtil.multiply(engineeringProject.getCustomer().getPumpPrice(), engineeringProject.getSchedule()));
			}
		}
		
		engineeringProjectDao.updateEntity(engineeringProject);
//		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EngineeringFinancial.class);
//		detachedCriteria.add(Restrictions.eq(EngineeringFinancial.ENGINEERING_PROJECT, engineeringProject));
//		List<EngineeringFinancial> engineeringFinancialList = engineeringFinancialDao.getByDetachedCriteria(detachedCriteria);
//		// 只有没有建立的项目才建立收款项目
//		if (engineeringFinancialList.isEmpty()) {
//			EngineeringFinancial engineeringFinancial = new EngineeringFinancial();
//			engineeringFinancial.setEngineeringProject(engineeringProject);
//			engineeringFinancial.setCreateDate(DateUtil.getNowyyyymmddhhmmss());
//			engineeringFinancial.setCreater(user);
//			engineeringFinancial.setState(true);
//			engineeringFinancial.setFinancialType(false);
//			engineeringFinancialDao.saveEntity(engineeringFinancial);
//		}
	}
	
	public void updateEngineeringProjectSendSms(EngineeringProject engineeringProject, List<EngineeringProjectDetail> engineeringProjectDetailList) {
		engineeringProjectDao.updateEntity(engineeringProject);
		if (engineeringProjectDetailList != null && !engineeringProjectDetailList.isEmpty()) {
			List<ProjectEmployeeDetail> projectEmployeeDetailList = new ArrayList<ProjectEmployeeDetail>();
			for (EngineeringProjectDetail engineeringProjectDetail : engineeringProjectDetailList) {
				projectEmployeeDetailList.addAll(engineeringProjectDetail.getProjectEmployeeDetailList());
				
			}
			List<Integer> idList = new ArrayList<Integer>();
			for (ProjectEmployeeDetail projectEmployeeDetail : projectEmployeeDetailList) {
				idList.add(projectEmployeeDetail.getId());
			}
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ProjectEmployeeDetail.class);
			detachedCriteria.createAlias(ProjectEmployeeDetail.ENGINEERING_PROJECT_DETAIL, ProjectEmployeeDetail.ENGINEERING_PROJECT_DETAIL, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.createAlias(ProjectEmployeeDetail.ENGINEERING_PROJECT_DETAIL_ENGINEERING_PROJECT, ProjectEmployeeDetail.ENGINEERING_PROJECT_DETAIL_ENGINEERING_PROJECT, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.createAlias(ProjectEmployeeDetail.EMPLOYEE, ProjectEmployeeDetail.EMPLOYEE, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.add(Restrictions.in(ProjectEmployeeDetail.BASE_ID, idList));
			projectEmployeeDetailList = projectEmployeeDetailDao.getByDetachedCriteria(detachedCriteria);
			List<CommonSms> commonSmsList = new ArrayList<CommonSms>();
			CommonSms commonSms;
			for (ProjectEmployeeDetail projectEmployeeDetail : projectEmployeeDetailList) {
				commonSms = new CommonSms();
				commonSms.setCreater(engineeringProject.getCreater());
				commonSms.setBelongingUser(engineeringProject.getBelongingUser());
				commonSms.setCreateDate(DateUtil.getNowyyyymmddhhmmss());
				commonSms.setSender(SmsConstants.SENDER);
				commonSms.setRecver(projectEmployeeDetail.getEmployee().getMobile());
				StringBuilder sb = new StringBuilder(128);
				sb.append("工作时间");
				sb.append(DateUtil.formatDate(projectEmployeeDetail.getEngineeringProjectDetail().getStartPumpDate(), Constants.YYYY_MM_DD_HH_MM_SS));
				sb.append("至");
				if (projectEmployeeDetail.getEngineeringProjectDetail().getEndPumpDate() != null) {
					sb.append(DateUtil.formatDate(projectEmployeeDetail.getEngineeringProjectDetail().getEndPumpDate(), Constants.YYYY_MM_DD_HH_MM_SS));
				} else {
					sb.append("工地工作结束");
				}
				sb.append("，工作地址：" + projectEmployeeDetail.getEngineeringProjectDetail().getEngineeringProject().getWorkAddress());
				sb.append("；需要您完成的方量：" + projectEmployeeDetail.getEngineeringProjectDetail().getWorkVolume());
				if (StringUtil.isNotBlank(projectEmployeeDetail.getEngineeringProjectDetail().getPumpParts())) {
					sb.append("，泵送部位：" + projectEmployeeDetail.getEngineeringProjectDetail().getPumpParts());
				} else {
					sb.append("。");
				}
				sb.append("");
				sb.append("收到短信后，接受请回复“" + projectEmployeeDetail.getId() +"0”");
				sb.append("，拒绝请回复“" + projectEmployeeDetail.getId() +"1”");
				commonSms.setSmstext(sb.toString());
				commonSmsList.add(commonSms);
				projectEmployeeDetail.setType(ProjectEmployeeDetail.TYPE_1);
			}
			if (!commonSmsList.isEmpty()) {
				commonSmsDao.saveEntityAll(commonSmsList);
				projectEmployeeDetailDao.updateEntityAll(projectEmployeeDetailList);
				for (CommonSms sms : commonSmsList) {
					if (SmsUtil.sendSms(sms)) {
						sms.setState(CommonSms.STATE_1);
					} else{
						sms.setState(CommonSms.STATE_2);
					}
				}
			}
		}
		
	}
	
}
