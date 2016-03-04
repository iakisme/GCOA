package com.qylm.bean.mobile.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.qylm.bean.MobileBasePagingBean;
import com.qylm.bean.UserBean;
import com.qylm.common.MothedUtil;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.common.utils.DateUtil;
import com.qylm.common.utils.StringUtil;
import com.qylm.dto.mobile.report.MobileEngineeringStatisticsManageDto;
import com.qylm.entity.EngineeringProject;
import com.qylm.entity.EngineeringProjectDetail;
import com.qylm.entity.ProjectEmployeeDetail;
import com.qylm.service.EngineeringProjectDetailService;
import com.qylm.service.ProjectEmployeeDetailService;

/**
 * 生产报表统计管理
 * @author qylm
 */
@ManagedBean
@RequestScoped
public class MobileEngineeringStatisticsManageBean extends MobileBasePagingBean {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -68155744285325674L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(MobileEngineeringStatisticsManageBean.class);
	
	/**
	 * 保存生产报表统计管理画面需要的值
	 */
	private MobileEngineeringStatisticsManageDto mobileEngineeringStatisticsManageDto = new MobileEngineeringStatisticsManageDto();
	
	/**
	 * 项目工作详细列表
	 */
	private List<EngineeringProjectDetail> engineeringProjectDetailList;
	
	/**
	 * bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;

	/**
	 * 生产报表统计管理业务类
	 */
	@ManagedProperty(value="#{engineeringProjectDetailService}")
	private EngineeringProjectDetailService engineeringProjectDetailService;
	
	/**
	 * 工程项目详细下的工作人员业务类
	 */
	@ManagedProperty(value="#{projectEmployeeDetailService}")
	private ProjectEmployeeDetailService projectEmployeeDetailService;
	
	/**
	 * 供手机桌面页面调用
	 * @param type 搜索类型
	 * @return
	 */
	public String getAll() {
		Tool.sendLog(LOG, userBean, "按下【功能菜单_生产报表统计菜单】");
		fetchData(0, true);
		return Navigation.MOBILE_ENGINEERING_STATISTICS_MANAGE;
	}
	
	/**
	 * 绑定搜索按钮
	 */
	public void selectMobileEngineeringStatistics() {
		Tool.sendLog(LOG, userBean, "按下【功能菜单_搜索按钮】");
		fetchData(0, true);
	}
	
	/**
	 * 返回按钮
	 * @return
	 */
	public String back() {
		return Navigation.MOBILE_MY_DESK;
	}
	
	@Override
	protected void fetchData(int start, boolean needInit) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EngineeringProjectDetail.class);
		detachedCriteria.createAlias(EngineeringProjectDetail.VEHICLE_INFOL, EngineeringProjectDetail.VEHICLE_INFOL, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias(EngineeringProjectDetail.ENGINEERING_PROJECT, EngineeringProjectDetail.ENGINEERING_PROJECT, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias(EngineeringProjectDetail.ENGINEERING_PROJECT_CUSTOMER, EngineeringProjectDetail.CUSTOMER, JoinType.LEFT_OUTER_JOIN);
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		detachedCriteria.addOrder(Order.desc(EngineeringProjectDetail.START_PUMP_DATE));
		String number = mobileEngineeringStatisticsManageDto.getNumber();
		String workAddress = mobileEngineeringStatisticsManageDto.getWorkAddress();
		Date beginDate = mobileEngineeringStatisticsManageDto.getBeginDate();
		detachedCriteria.add(Restrictions.eq(EngineeringProjectDetail.TYPE, EngineeringProject.TYPE_4));
		if (beginDate != null) {
			detachedCriteria.add(Restrictions.ge(EngineeringProjectDetail.START_PUMP_DATE, DateUtil.getBackDate(beginDate, 1)));
			detachedCriteria.add(Restrictions.le(EngineeringProjectDetail.START_PUMP_DATE, DateUtil.getBackDate(beginDate, 2)));
		}
		if (StringUtil.isNotBlank(number)) {
			detachedCriteria.add(Restrictions.like(EngineeringProjectDetail.VEHICLE_INFOL_NUMBER, number, MatchMode.ANYWHERE));
		}
		if (StringUtil.isNotBlank(workAddress)) {
			detachedCriteria.add(Restrictions.like(EngineeringProjectDetail.WORK_ADDRESS, workAddress, MatchMode.ANYWHERE));
		}
		engineeringProjectDetailList = engineeringProjectDetailService.getByDetachedCriteria(detachedCriteria, start, onePageCount);
		if (needInit) {
			init(engineeringProjectDetailService.getRowCount(detachedCriteria));
		}
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
	}

	/**
	 * @param projectEmployeeDetailService the projectEmployeeDetailService to set
	 */
	public void setProjectEmployeeDetailService(
			ProjectEmployeeDetailService projectEmployeeDetailService) {
		this.projectEmployeeDetailService = projectEmployeeDetailService;
	}

	/**
	 * @param engineeringProjectDetailService the engineeringProjectDetailService to set
	 */
	public void setEngineeringProjectDetailService(
			EngineeringProjectDetailService engineeringProjectDetailService) {
		this.engineeringProjectDetailService = engineeringProjectDetailService;
	}
	
	/**
	 * @param userBean the userBean to set
	 */
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	/**
	 * @return the mobileEngineeringStatisticsManageDto
	 */
	public MobileEngineeringStatisticsManageDto getMobileEngineeringStatisticsManageDto() {
		return mobileEngineeringStatisticsManageDto;
	}

	/**
	 * @param mobileEngineeringStatisticsManageDto the mobileEngineeringStatisticsManageDto to set
	 */
	public void setMobileEngineeringStatisticsManageDto(
			MobileEngineeringStatisticsManageDto mobileEngineeringStatisticsManageDto) {
		this.mobileEngineeringStatisticsManageDto = mobileEngineeringStatisticsManageDto;
	}

	/**
	 * @return the engineeringProjectDetailList
	 */
	public List<EngineeringProjectDetail> getEngineeringProjectDetailList() {
		return engineeringProjectDetailList;
	}

	/**
	 * @param engineeringProjectDetailList the engineeringProjectDetailList to set
	 */
	public void setEngineeringProjectDetailList(
			List<EngineeringProjectDetail> engineeringProjectDetailList) {
		this.engineeringProjectDetailList = engineeringProjectDetailList;
	}

}
