package com.qylm.spring.timer;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.smslib.InboundMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.qylm.common.sms.SmsUtil;
import com.qylm.common.utils.StringUtil;
import com.qylm.constants.SmsConstants;
import com.qylm.entity.CommonSms;
import com.qylm.entity.EngineeringProject;
import com.qylm.entity.EngineeringProjectDetail;
import com.qylm.entity.ProjectEmployeeDetail;
import com.qylm.service.CommonSmsService;
import com.qylm.service.EngineeringProjectDetailService;
import com.qylm.service.EngineeringProjectService;
import com.qylm.service.ProjectEmployeeDetailService;

@Component
public class ImportSmsServiceImpl implements ImportSmsService {
	
	/**
	 * 短信业务类
	 */
	@Autowired
	private CommonSmsService commonSmsService;
	
	/**
	 * 短信业务类
	 */
	@Autowired
	private ProjectEmployeeDetailService projectEmployeeDetailService;
	
	/**
	 * 工程信息业务类
	 */
	@Autowired
	private EngineeringProjectService engineeringProjectService;
	
	/**
	 * 工程信息详细业务类
	 */
	@Autowired
	private EngineeringProjectDetailService engineeringProjectDetailService;
	
	@Scheduled(cron = "0/60 * *  * * ? ")
	// 每5秒执行一次cron = "0/5 * *  * * ? "
//	"0 0 12 * * ?"    每天中午十二点触发 
//	 "0 15 10 ? * *"    每天早上10：15触发 
//	 "0 15 10 * * ?"    每天早上10：15触发 
//	 "0 15 10 * * ? *"    每天早上10：15触发 
//	 "0 15 10 * * ? 2005"    2005年的每天早上10：15触发 
//	 "0 * 14 * * ?"    每天从下午2点开始到2点59分每分钟一次触发 
//	 "0 0/5 14 * * ?"    每天从下午2点开始到2：55分结束每5分钟一次触发 
//	 "0 0/5 14,18 * * ?"    每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发 
//	 "0 0-5 14 * * ?"    每天14:00至14:05每分钟一次触发
//	 "0 10,44 14 ? 3 WED"    三月的每周三的14：10和14：44触发 
//	 "0 15 10 ? * MON-FRI"    每个周一、周二、周三、周四、周五的10：15触发
	public void createSms() {
		// 读取短信信息
		List<InboundMessage> inboundMessageList = SmsUtil.readSms();
		if (inboundMessageList != null && !inboundMessageList.isEmpty()) {
			// 存入本地数据库中
			List<CommonSms> commonSmsList = new ArrayList<CommonSms>();
			CommonSms commonSms;
			for (InboundMessage inboundMessage : inboundMessageList) {
				commonSms = new CommonSms();
				commonSms.setCreateDate(inboundMessage.getDate());
				commonSms.setSender(SmsConstants.SENDER);
				commonSms.setSmsId(inboundMessage.getGatewayId());
				commonSms.setRecver(inboundMessage.getOriginator());
				commonSms.setSmstext(inboundMessage.getText());
				commonSmsList.add(commonSms);
			}
			// 查询工程信息
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ProjectEmployeeDetail.class);
			detachedCriteria.createAlias(ProjectEmployeeDetail.ENGINEERING_PROJECT_DETAIL, ProjectEmployeeDetail.ENGINEERING_PROJECT_DETAIL, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.createAlias(ProjectEmployeeDetail.ENGINEERING_PROJECT_DETAIL_ENGINEERING_PROJECT, ProjectEmployeeDetail.ENGINEERING_PROJECT, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.createAlias(ProjectEmployeeDetail.EMPLOYEE, ProjectEmployeeDetail.EMPLOYEE, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.add(Restrictions.eq(ProjectEmployeeDetail.ENGINEERING_PROJECT_TYPE, EngineeringProject.TYPE_2));
			detachedCriteria.add(Restrictions.eq(ProjectEmployeeDetail.TYPE, ProjectEmployeeDetail.TYPE_1));
			List<ProjectEmployeeDetail> projectEmployeeDetailList = projectEmployeeDetailService.getByDetachedCriteria(detachedCriteria);
			if (!projectEmployeeDetailList.isEmpty()) {
				// 比对数据，数据格式id+0（确认接受），id+1（拒绝）
				for (ProjectEmployeeDetail projectEmployeeDetail : projectEmployeeDetailList) {
					for (CommonSms sms : commonSmsList) {
						String smstext = sms.getSmstext();
						// 验证回复人
						if (sms.getRecver().indexOf(StringUtil.trimAll(projectEmployeeDetail.getEmployee().getMobile())) != -1 && !StringUtil.isBlank(smstext)) {
							// 验证回复格式
							String chick1 = projectEmployeeDetail.getId() + "0";
							String chick2 = projectEmployeeDetail.getId() + "1";
							if (chick1.equals(StringUtil.trimAll(smstext))) {
								projectEmployeeDetail.setType(ProjectEmployeeDetail.TYPE_2);
								sms.setState(CommonSms.STATE_3);
							} else if (chick2.equals(StringUtil.trimAll(smstext))) {
								projectEmployeeDetail.setType(ProjectEmployeeDetail.TYPE_3);
								sms.setState(CommonSms.STATE_3);
							}
							sms.setBelongingUser(projectEmployeeDetail.getBelongingUser());
							sms.setCreater(projectEmployeeDetail.getCreater());
						}
					}
				}
				projectEmployeeDetailService.updateEntityAll(projectEmployeeDetailList);
			}
			// 查询出所以工作安排中的工程信息，并验证所以车辆是否已经确认或者是拒绝，工作安排
			detachedCriteria = DetachedCriteria.forClass(EngineeringProject.class);
			detachedCriteria.add(Restrictions.eq(EngineeringProject.TYPE, EngineeringProject.TYPE_2));
			List<EngineeringProject> engineeringProjectList = engineeringProjectService.getByDetachedCriteria(detachedCriteria);
			if (!engineeringProjectList.isEmpty()) {
				for (EngineeringProject engineeringProject : engineeringProjectList) {
					engineeringProject.setEngineeringProjectDetailList(arrangeVehicle(engineeringProject));
				}
				int state; // 是否有拒绝信息
				for (EngineeringProject engineeringProject : engineeringProjectList) {
					state = 1;
					for (EngineeringProjectDetail engineeringProjectDetail : engineeringProject.getEngineeringProjectDetailList()) {
						for (ProjectEmployeeDetail projectEmployeeDetail : engineeringProjectDetail.getProjectEmployeeDetailList()) {
							if (ProjectEmployeeDetail.TYPE_3.equals(projectEmployeeDetail.getType())) {
								state = 2; // 存在拒绝回复
								break;
							} else if (ProjectEmployeeDetail.TYPE_2.equals(projectEmployeeDetail.getType())) {
								state = 3; // 确认回复
							} else {
								state = 1; // 未回复
								break;
							}
						}
						if (state == 2 || state == 1) {break;}
					}
					if (state == 2) {
						engineeringProject.setType(EngineeringProject.TYPE_7);
					} else if (state == 3) {
						engineeringProject.setType(EngineeringProject.TYPE_3);
					}
				}
				engineeringProjectService.updateEntityAll(engineeringProjectList);
			}
			int size = commonSmsList.size() - 1;
			for (int i = size; i >= 0; i--) {
				commonSms = commonSmsList.get(i);
				if (!CommonSms.STATE_3.equals(commonSms.getState())) {
					commonSmsList.remove(i);
				}
			}
			commonSmsService.saveEntityAll(commonSmsList);
			SmsUtil.deleteAllSms(inboundMessageList);
		}
	}
	
	/**
	 * 此方法绑定于工作登记管理画面的安排车辆按钮 
	 * 实现功能：列出车辆安排列表
	 * @return 画面不跳转
	 */
	private List<EngineeringProjectDetail> arrangeVehicle(EngineeringProject transferEngineeringProject) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EngineeringProjectDetail.class);
		detachedCriteria.createAlias(EngineeringProjectDetail.ENGINEERING_PROJECT, EngineeringProjectDetail.ENGINEERING_PROJECT, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias(EngineeringProjectDetail.VEHICLE_INFOL, EngineeringProjectDetail.VEHICLE_INFOL, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.add(Restrictions.eq(EngineeringProjectDetail.ENGINEERING_PROJECT, transferEngineeringProject));
		List<EngineeringProjectDetail> engineeringProjectDetailList = engineeringProjectDetailService.getByDetachedCriteria(detachedCriteria);
		if (!engineeringProjectDetailList.isEmpty()) {
			detachedCriteria = DetachedCriteria.forClass(ProjectEmployeeDetail.class);
			detachedCriteria.createAlias(ProjectEmployeeDetail.EMPLOYEE, ProjectEmployeeDetail.EMPLOYEE, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.createAlias(ProjectEmployeeDetail.ENGINEERING_PROJECT_DETAIL, ProjectEmployeeDetail.ENGINEERING_PROJECT_DETAIL, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.add(Restrictions.in(ProjectEmployeeDetail.ENGINEERING_PROJECT_DETAIL, engineeringProjectDetailList));
			List<ProjectEmployeeDetail> projectEmployeeDetailList = projectEmployeeDetailService.getByDetachedCriteria(detachedCriteria);
			if (!projectEmployeeDetailList.isEmpty()) {
				List<ProjectEmployeeDetail> detailList;
				for (EngineeringProjectDetail engineeringProjectDetail : engineeringProjectDetailList) {
					detailList = new ArrayList<ProjectEmployeeDetail>();
					for (ProjectEmployeeDetail projectEmployeeDetail : projectEmployeeDetailList) {
						if (projectEmployeeDetail.getEngineeringProjectDetail().equals(engineeringProjectDetail)) {
							detailList.add(projectEmployeeDetail);
						}
					}
					engineeringProjectDetail.setProjectEmployeeDetailList(detailList);
				}
			}
			
		}
		return engineeringProjectDetailList;
	}
	
}
