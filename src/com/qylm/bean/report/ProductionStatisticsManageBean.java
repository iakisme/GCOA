package com.qylm.bean.report;

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

import com.qylm.bean.BasePagingBean;
import com.qylm.bean.UserBean;
import com.qylm.common.MothedUtil;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.common.excel.WorkWeekXlsCreator;
import com.qylm.common.utils.DateUtil;
import com.qylm.common.utils.DownloadUtil;
import com.qylm.common.utils.StringUtil;
import com.qylm.constants.Constants;
import com.qylm.dto.report.ProductionStatisticsManageDto;
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
public class ProductionStatisticsManageBean extends BasePagingBean {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1846289001419311361L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(ProductionStatisticsManageBean.class);
	
	/**
	 * 保存生产报表统计管理画面需要的值
	 */
	private ProductionStatisticsManageDto productionStatisticsManageDto = new ProductionStatisticsManageDto();
	
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
	 * 查询出所有生产报表统计列表
	 * @return 生产报表统计管理画面
	 */
	public String getAll() {
		Tool.sendLog(LOG, userBean, "按下【功能菜单_生产报表统计菜单】");
		productionStatisticsManageDto.setMonth(DateUtil.getNowyyyymm());
		productionStatisticsManageDto.setYearMonth(DateUtil.formatDate(productionStatisticsManageDto.getMonth(), Constants.YYYY_MM));
		fetchData(0, true);
		return Navigation.PRODUCTION_STATISTICS_MANAGE;
	}
	
	/**
	 * 绑定搜索按钮
	 */
	public void selectProductionStatistics() {
		Tool.sendLog(LOG, userBean, "按下【功能菜单_搜索按钮】");
		fetchData(0, true);
	}
	
	/**
	 * 上一个月
	 */
	public void upMonth() {
		Tool.sendLog(LOG, userBean, "按下【功能菜单_上一个月按钮】");
		Date beginDate = productionStatisticsManageDto.getBeginDate();
		if (beginDate != null) {
			productionStatisticsManageDto.setMonth(beginDate);
			productionStatisticsManageDto.setBeginDate(null);
		}
		productionStatisticsManageDto.setMonth(DateUtil.nonceDate(productionStatisticsManageDto.getMonth(), true));
		productionStatisticsManageDto.setYearMonth(DateUtil.formatDate(productionStatisticsManageDto.getMonth(), Constants.YYYY_MM));
		fetchData(0, true);
	}
	
	/**
	 * 下一个月
	 */
	public void nextMonth() {
		Tool.sendLog(LOG, userBean, "按下【功能菜单_下一个月按钮】");
		Date beginDate = productionStatisticsManageDto.getBeginDate();
		if (beginDate != null) {
			productionStatisticsManageDto.setMonth(beginDate);
			productionStatisticsManageDto.setBeginDate(null);
		}
		productionStatisticsManageDto.setMonth(DateUtil.nonceDate(productionStatisticsManageDto.getMonth(), false));
		productionStatisticsManageDto.setYearMonth(DateUtil.formatDate(productionStatisticsManageDto.getMonth(), Constants.YYYY_MM));
		fetchData(0, true);
	}
	
	/**
	 * 导出excel
	 */
	public void exportXLS() {
		DetachedCriteria detachedCriteria = getDetachedCriteria();
		engineeringProjectDetailList = engineeringProjectDetailService.getByDetachedCriteria(detachedCriteria);
		if (engineeringProjectDetailList != null && !engineeringProjectDetailList.isEmpty()) {
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
			String title = "金华汇聚工程机械有限公司泵车" + productionStatisticsManageDto.getYearMonth() + "生产报表";
			DownloadUtil.downloadExcel(new WorkWeekXlsCreator().workWeekXls(title ,engineeringProjectDetailList), title + Constants.EX_NAME_XLS);
		}
	}

	@Override
	protected void fetchData(int start, boolean needInit) {
		DetachedCriteria detachedCriteria = getDetachedCriteria();
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
	 * 共通搜索条件
	 * @return
	 */
	private DetachedCriteria getDetachedCriteria() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EngineeringProjectDetail.class);
		detachedCriteria.createAlias(EngineeringProjectDetail.VEHICLE_INFOL, EngineeringProjectDetail.VEHICLE_INFOL, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias(EngineeringProjectDetail.ENGINEERING_PROJECT, EngineeringProjectDetail.ENGINEERING_PROJECT, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias(EngineeringProjectDetail.ENGINEERING_PROJECT_CUSTOMER, EngineeringProjectDetail.CUSTOMER, JoinType.LEFT_OUTER_JOIN);
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		detachedCriteria.addOrder(Order.desc(EngineeringProjectDetail.START_PUMP_DATE));
		String customerName = productionStatisticsManageDto.getCustomerName();
		String number = productionStatisticsManageDto.getNumber();
		String workAddress = productionStatisticsManageDto.getWorkAddress();
		String type = productionStatisticsManageDto.getType();
		Date beginDate = productionStatisticsManageDto.getBeginDate();
		if (beginDate != null) {
			detachedCriteria.add(Restrictions.ge(EngineeringProjectDetail.START_PUMP_DATE, DateUtil.getBackDate(beginDate, 1)));
			detachedCriteria.add(Restrictions.le(EngineeringProjectDetail.START_PUMP_DATE, DateUtil.getBackDate(beginDate, 2)));
		} else {
			Date month = productionStatisticsManageDto.getMonth();
			if (month != null) {
				detachedCriteria.add(Restrictions.ge(EngineeringProjectDetail.START_PUMP_DATE, month));
				
				detachedCriteria.add(Restrictions.lt(EngineeringProjectDetail.START_PUMP_DATE, DateUtil.nonceDate(month, false)));
			}
		}
		if (StringUtil.isNotBlank(customerName)) {
			detachedCriteria.add(Restrictions.like(EngineeringProjectDetail.CUSTOMER_NAME, customerName, MatchMode.ANYWHERE));
		}
		if (StringUtil.isNotBlank(number)) {
			detachedCriteria.add(Restrictions.like(EngineeringProjectDetail.VEHICLE_INFOL_NUMBER, number, MatchMode.ANYWHERE));
		}
		if (StringUtil.isNotBlank(workAddress)) {
			detachedCriteria.add(Restrictions.like(EngineeringProjectDetail.WORK_ADDRESS, workAddress, MatchMode.ANYWHERE));
		}
		if (!StringUtil.isUnSelected(type)) {
			detachedCriteria.add(Restrictions.eq(EngineeringProjectDetail.TYPE, type));
		}
		return detachedCriteria;
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
	 * @return the productionStatisticsManageDto
	 */
	public ProductionStatisticsManageDto getProductionStatisticsManageDto() {
		return productionStatisticsManageDto;
	}

	/**
	 * @param productionStatisticsManageDto the productionStatisticsManageDto to set
	 */
	public void setProductionStatisticsManageDto(
			ProductionStatisticsManageDto productionStatisticsManageDto) {
		this.productionStatisticsManageDto = productionStatisticsManageDto;
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
