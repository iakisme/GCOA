package com.qylm.bean.engineering;

import java.math.BigDecimal;
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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.qylm.bean.BasePagingBean;
import com.qylm.bean.UserBean;
import com.qylm.bean.engineeringMaterials.MaterialsGrantCreateBean;
import com.qylm.bean.returner.Returner;
import com.qylm.bean.returner.engineering.ProjectStateManageReturner;
import com.qylm.bean.returner.engineeringMaterials.MaterialsGrantManageReturner;
import com.qylm.common.Message;
import com.qylm.common.MothedUtil;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.common.utils.BigDecimalUtil;
import com.qylm.common.utils.DateUtil;
import com.qylm.common.utils.StringUtil;
import com.qylm.constants.Constants;
import com.qylm.dto.engineering.ProjectStateManageDto;
import com.qylm.entity.EngineeringProject;
import com.qylm.entity.EngineeringProjectDetail;
import com.qylm.entity.MaterialsGrant;
import com.qylm.entity.ProjectEmployeeDetail;
import com.qylm.service.EngineeringProjectDetailService;
import com.qylm.service.EngineeringProjectService;
import com.qylm.service.ProjectEmployeeDetailService;

/**
 * 工作登记管理
 * @author qylm
 */
@ManagedBean
@RequestScoped
public class ProjectStateManageBean extends BasePagingBean {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1747256037264477755L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(ProjectStateManageBean.class);
	
	/**
	 * 保存工作登记管理画面需要保存的值
	 */
	private ProjectStateManageDto projectStateManageDto = new ProjectStateManageDto();

	/**
	 * 工作登记列表（检索结果）
	 */
	private List<EngineeringProject> engineeringProjectList;
	
	/**
	 * 工作登记管理bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;

	/**
	 * 工作登记管理业务类
	 */
	@ManagedProperty(value="#{engineeringProjectService}")
	private EngineeringProjectService engineeringProjectService;
	
	/**
	 * 工作登记详细业务类
	 */
	@ManagedProperty(value="#{engineeringProjectDetailService}")
	private EngineeringProjectDetailService engineeringProjectDetailService;
	
	/**
	 * 工作登记详细下的工作人员业务类
	 */
	@ManagedProperty(value="#{projectEmployeeDetailService}")
	private ProjectEmployeeDetailService projectEmployeeDetailService;
	
	/**
	 * 查询出所有工作登记列表
	 * 
	 * @return 工作登记管理画面
	 */
	public String getAll() {
		Tool.sendLog(LOG, userBean, "按下【功能菜单_工作登记管理按钮】");
		fetchData(0, true);
		return Navigation.PROJECT_STATE_MANAGE;
	}
	
	/**
	 * 供其它页面调用，根据项目状态搜索出对应的项目工程信息
	 * @param type 项目状态
	 * @return
	 */
	public String selectEngineeringProject(String type, Returner<?, ?, ?> returner) {
		projectStateManageDto.setType(type);
		projectStateManageDto.setReturner(returner);
		fetchData(0, true);
		return Navigation.PROJECT_STATE_MANAGE;
	}

	/**
	 * 此方法绑定于项目管理画面的修改按钮 
	 * 实现功能：根据修改的对象，跳转至工作登记登陆画面
	 * @return 工作登记登陆画面
	 */
	public String updateEngineeringProject(EngineeringProject transferEngineeringProject) {
		Tool.sendLog(LOG, userBean, "按下【工作登记管理画面_详细按钮】");
		return Tool.getBackBean(ProjectStateCreateBean.class).updateDetail(new ProjectStateManageReturner(projectStateManageDto, currentPage), transferEngineeringProject);
	}
	
	/**
	 * 此方法绑定于工作登记管理画面的检索按钮 
	 * 实现功能：根据检索条件，检索出工作登记
	 * @return 画面不跳转
	 */
	public void selectEngineeringProject() {
		Tool.sendLog(LOG, userBean, "按下【工作登记管理画面_检索按钮】");
		fetchData(0, true);
	}
	
	/**
	 * 登记工作情况
	 * @param engineeringProjectDetail
	 */
	public void registerEngineeringProjectDetail(EngineeringProjectDetail engineeringProjectDetail) {
		Tool.sendLog(LOG, userBean, "按下【工作登记管理画面_登记工作情况按钮】");
		projectStateManageDto.setAddress(engineeringProjectDetail.getEngineeringProject().getWorkAddress());
		projectStateManageDto.setWorkVolume(engineeringProjectDetail.getWorkVolume());
		projectStateManageDto.setGasVolume(engineeringProjectDetail.getGasVolume());
		projectStateManageDto.setGasPrice(engineeringProjectDetail.getGasPrice());
		projectStateManageDto.setSchedule(engineeringProjectDetail.getSchedule());
		projectStateManageDto.setPumpParts(engineeringProjectDetail.getPumpParts());
		projectStateManageDto.setStartPumpDate(engineeringProjectDetail.getStartPumpDate());
		projectStateManageDto.setEndPumpDate(engineeringProjectDetail.getEndPumpDate());
		projectStateManageDto.setEngineeringProjectDetail(engineeringProjectDetail);
	}
	
	/**
	 * 工作暂停
	 * @param engineeringProjectDetail
	 */
	public void stopEngineeringProjectDetail(EngineeringProjectDetail engineeringProjectDetail) {
		Tool.sendLog(LOG, userBean, "按下【工作登记管理画面_工作暂停按钮】");
		EngineeringProject engineeringProject = engineeringProjectDetail.getEngineeringProject();
		if (EngineeringProject.TYPE_4.equals(engineeringProject.getType())) {
			engineeringProject.setType(EngineeringProject.TYPE_3);
			Tool.sendErrorMessage("工作已恢复！");
		} else {
			engineeringProject.setType(EngineeringProject.TYPE_5);
			Tool.sendErrorMessage("工作暂停！");
		}
		engineeringProjectService.updateEntity(engineeringProject);
		for (EngineeringProject project : engineeringProjectList) {
			if (project.equals(engineeringProject)) {
				project.setType(engineeringProject.getType());
				break;
			}
		}
	}
	
	/**
	 * 实现功能：更改工程状态为完结，并建立一条收款项目
	 * @param engineeringProjectDetail
	 */
	public void finishEngineeringProjectDetail(EngineeringProjectDetail engineeringProjectDetail) {
		Tool.sendLog(LOG, userBean, "按下【工作登记管理画面_工作完结按钮】");
		EngineeringProject engineeringProject = engineeringProjectDetail.getEngineeringProject();
		engineeringProject.setType(EngineeringProject.TYPE_6);
		engineeringProjectService.updateEngineeringProjectsAndSaveFinancial(engineeringProject, userBean.getUser());
		for (EngineeringProject project : engineeringProjectList) {
			if (project.equals(engineeringProject)) {
				project.setType(engineeringProject.getType());
				break;
			}
		}
		Tool.sendErrorMessage("工作完结！");
	}
	
	/**
	 * 保存工作详情，并同时把数据更新起来
	 */
	public void saveEngineeringProjectDetail() {
		Tool.sendLog(LOG, userBean, "按下【工作登记管理画面_保存工作情况按钮】");
		EngineeringProjectDetail engineeringProjectDetail = projectStateManageDto.getEngineeringProjectDetail();
		BigDecimal schedule = projectStateManageDto.getSchedule();
		if (schedule == null) {
			schedule = Constants.BIGDECIMAL_ZERO;
		}
		engineeringProjectDetail.setSchedule(BigDecimalUtil.add(schedule, projectStateManageDto.getNewSchedule()));
		engineeringProjectDetail.setNewSchedule(projectStateManageDto.getNewSchedule());
		BigDecimal gasVolume = projectStateManageDto.getGasVolume();
		if (gasVolume == null) {
			gasVolume = Constants.BIGDECIMAL_ZERO;
		}
		engineeringProjectDetail.setGasVolume(BigDecimalUtil.add(gasVolume, projectStateManageDto.getNewGasVolume()));
		BigDecimal gasPrice = projectStateManageDto.getGasPrice();
		if (gasPrice == null) {
			gasPrice = Constants.BIGDECIMAL_ZERO;
		}
		engineeringProjectDetail.setGasPrice(BigDecimalUtil.add(gasPrice, projectStateManageDto.getNewGasPrice()));
		engineeringProjectDetail.setUpdateDate(DateUtil.getNowyyyymmddhhmmss());
		engineeringProjectDetail.setRemark(projectStateManageDto.getRemark());
		engineeringProjectDetail.setPumpParts(projectStateManageDto.getPumpParts());
		engineeringProjectDetail.setStartPumpDate(projectStateManageDto.getStartPumpDate());
		engineeringProjectDetail.setEndPumpDate(projectStateManageDto.getEndPumpDate());
		// 更新项目工程完成的方量
		EngineeringProject engineeringProject = engineeringProjectDetail.getEngineeringProject();
		BigDecimal engineeringProjectSchedule = engineeringProject.getSchedule();
		if (engineeringProjectSchedule == null) {
			engineeringProjectSchedule = Constants.BIGDECIMAL_ZERO;
		}
		engineeringProject.setSchedule(BigDecimalUtil.add(engineeringProjectSchedule, engineeringProjectDetail.getNewSchedule()));
		engineeringProject.setUpdateDate(DateUtil.getNowyyyymmddhhmmss());
		engineeringProject.setType(EngineeringProject.TYPE_4);
		if (engineeringProjectDetail.getEndPumpDate() != null) {
			engineeringProject.setEndDate(DateUtil.toyyyymmdd(engineeringProjectDetail.getEndPumpDate()));
		}
		engineeringProject.setWorkAddress(projectStateManageDto.getAddress());
		// 如果完成的方量大于或者等于整个工程的方量，更改项目状态为已完结
//		if (BigDecimalUtil.bigThanOrEqual(engineeringProject.getSchedule(), engineeringProject.getActualVolume())) {
//			engineeringProject.setType(EngineeringProject.TYPE_5);
//		}
		for (EngineeringProject project : engineeringProjectList) {
			if (project.equals(engineeringProject)) {
				project.setSchedule(engineeringProject.getSchedule());
				project.setType(engineeringProject.getType());
			}
		}
		// 如果数据为空，就不进行添加
		// boolean	state = BigDecimalUtil.isNotNullOrZero(projectStateManageDto.getNewGasPrice());
		engineeringProjectDetail.setEmployee(projectStateManageDto.getEmployee());
		engineeringProjectDetail.setNewGasPrice(projectStateManageDto.getNewGasPrice());
		engineeringProjectDetailService.updateEngineeringProjectDetail(engineeringProjectDetail, false);
		
		
		
		// 清空保存信息
		projectStateManageDto.setGasVolume(null);
		projectStateManageDto.setGasPrice(null);
		projectStateManageDto.setSchedule(null);
		projectStateManageDto.setRemark(null);
		projectStateManageDto.setEngineeringProjectDetail(null);
		Tool.sendErrorMessage(Message.GENERAL_SAVESUCCESS);
	}
	
	/**
	 * 获取到总完成方量
	 * @return
	 */
	public BigDecimal getSumSchedule() {
		DetachedCriteria detachedCriteria = getDetached();
		detachedCriteria.setProjection(Projections.sum(EngineeringProject.SCHEDULE));
		List<Object[]> objectList = engineeringProjectService.getByDetachedCriteriaForObject(detachedCriteria);
		BigDecimal sumSchedule = Constants.BIGDECIMAL_ZERO;
		if (!objectList.isEmpty()) {
			Object[] objects = objectList.toArray();
			sumSchedule = BigDecimalUtil.toBigDecimal(objects[0]);
		}
		return sumSchedule;
	}
	
	@Override
	protected void fetchData(int start, boolean needInit) {
		DetachedCriteria detachedCriteria = getDetached();
		engineeringProjectList = engineeringProjectService.getByDetachedCriteria(detachedCriteria, start, onePageCount);
		if (needInit) {
			init(engineeringProjectService.getRowCount(detachedCriteria));
		}
		if (!engineeringProjectList.isEmpty()) {
			for (EngineeringProject engineeringProject : engineeringProjectList) {
				engineeringProject.setEngineeringProjectDetailList(arrangeVehicle(engineeringProject));
			}
		}
	}
	
	/**
	 * 供其它页面调用查询出正在工作中的项目
	 * @return
	 */
	public List<EngineeringProject> getEngineeringProjectList(String type) {
		DetachedCriteria detachedCriteria = getDetached();
		if (StringUtil.isNotBlank(type)) {
			detachedCriteria.add(Restrictions.eq(EngineeringProject.TYPE, type));
		}
		engineeringProjectList = engineeringProjectService.getByDetachedCriteria(detachedCriteria);
		if (!engineeringProjectList.isEmpty()) {
			for (EngineeringProject engineeringProject : engineeringProjectList) {
				engineeringProject.setEngineeringProjectDetailList(arrangeVehicle(engineeringProject));
			}
		}
		return engineeringProjectList;
	}
	
	/**
	 * 供其它页面调用查询出正在工作中的项目
	 * @return
	 */
	public List<EngineeringProject> getEngineeringProjectList(String... type) {
		DetachedCriteria detachedCriteria = getDetached();
		if (type != null && type.length > 0) {
			List<String> types = new ArrayList<String>();
			for (int i = 0; i < type.length; i++) {
				types.add(type[i]);
			}
			detachedCriteria.add(Restrictions.in(EngineeringProject.TYPE, types));
		}
		engineeringProjectList = engineeringProjectService.getByDetachedCriteria(detachedCriteria);
		if (!engineeringProjectList.isEmpty()) {
			for (EngineeringProject engineeringProject : engineeringProjectList) {
				engineeringProject.setEngineeringProjectDetailList(arrangeVehicle(engineeringProject));
			}
		}
		return engineeringProjectList;
	}

	/**
	 * 共通检索条件
	 * @return
	 */
	private DetachedCriteria getDetached() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EngineeringProject.class);
		detachedCriteria.createAlias(EngineeringProject.CUSTOMER, EngineeringProject.CUSTOMER, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias(EngineeringProject.EMPLOYEE, EngineeringProject.EMPLOYEE, JoinType.LEFT_OUTER_JOIN);
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		detachedCriteria.addOrder(Order.asc((EngineeringProject.TYPE)));
		detachedCriteria.addOrder(Order.desc(EngineeringProject.CREATE_DATE));
		String number = projectStateManageDto.getNumber();
		String customerName = projectStateManageDto.getCustomerName();
		Date beginDate = projectStateManageDto.getBeginDate();
		Date endDate = projectStateManageDto.getEndDate();
		Date startEndDate = projectStateManageDto.getStartEndDate();
		Date endEndDate = projectStateManageDto.getEndEndDate();
		String constructionName = projectStateManageDto.getConstructionName();
		String workAddress = projectStateManageDto.getWorkAddress();
		String type = projectStateManageDto.getType();
		if (StringUtil.isNotBlank(number)) {
			DetachedCriteria criteria = DetachedCriteria.forClass(EngineeringProjectDetail.class);
			criteria.createAlias(EngineeringProjectDetail.ENGINEERING_PROJECT, EngineeringProjectDetail.ENGINEERING_PROJECT, JoinType.LEFT_OUTER_JOIN);
			criteria.add(Restrictions.like(EngineeringProjectDetail.VEHICLE_INFOL_NUMBER, number, MatchMode.ANYWHERE));
			criteria.setProjection(Property.forName(EngineeringProjectDetail.ENGINEERING_PROJECT_ID));
			detachedCriteria.add(Property.forName(EngineeringProject.BASE_ID).in(criteria));
		}
		if (StringUtil.isNotBlank(customerName)) {
			detachedCriteria.add(Restrictions.like(EngineeringProject.CUSTOMER_NAME, customerName, MatchMode.ANYWHERE));
		}
		if (beginDate != null) {
			detachedCriteria.add(Restrictions.ge(EngineeringProject.BEGIN_DATE, beginDate));
		}
		if (endDate != null) {
			detachedCriteria.add(Restrictions.le(EngineeringProject.BEGIN_DATE, endDate));
		}
		if (startEndDate != null) {
			detachedCriteria.add(Restrictions.ge(EngineeringProject.END_DATE, startEndDate));
		}
		if (endEndDate != null) {
			detachedCriteria.add(Restrictions.le(EngineeringProject.END_DATE, endEndDate));
		}
		if (StringUtil.isNotBlank(constructionName)) {
			detachedCriteria.add(Restrictions.like(EngineeringProject.CONSTRUCTION_NAME, constructionName, MatchMode.ANYWHERE));
		}
		if (StringUtil.isNotBlank(workAddress)) {
			detachedCriteria.add(Restrictions.like(EngineeringProject.WORK_ADDRESS, workAddress, MatchMode.ANYWHERE));
		}
		if (!StringUtil.isUnSelected(type)) {
			detachedCriteria.add(Restrictions.eq(EngineeringProject.TYPE, type));
		}
		return detachedCriteria;
	}
	
	/**
	 * 此方法绑定于工作登记管理画面的安排车辆按钮 
	 * 实现功能：列出车辆安排列表
	 * @return 画面不跳转
	 */
	private List<EngineeringProjectDetail> arrangeVehicle(EngineeringProject transferEngineeringProject) {
		Tool.sendLog(LOG, userBean, "按下【工作登记管理画面的_登记工作情况按钮】");
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
	
	/**
	 * 返回到上一个画面
	 * @return
	 */
	public String back() {
		Tool.sendLog(LOG, userBean, "按下【工作登记管理画面_返回按钮】");
		return projectStateManageDto.getReturner().returnOnly();
	}

	/**
	 * 需要返回本画面时都调用此共通方法
	 * @param currentPage 当前页数
	 * @return 个人工作登记管理画面
	 */
	public String back(int currentPage) {
		reflushCurrentPage(currentPage);
		return Navigation.PROJECT_STATE_MANAGE;
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
	 * set engineeringProjectService
	 * @param engineeringProjectService the engineeringProjectService to set
	 */
	public void setEngineeringProjectService(EngineeringProjectService engineeringProjectService) {
		this.engineeringProjectService = engineeringProjectService;
	}

	/**
	 * set userBean
	 * @param userBean the userBean to set
	 */
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	/**
	 * @return the projectStateManageDto
	 */
	public ProjectStateManageDto getProjectStateManageDto() {
		return projectStateManageDto;
	}

	/**
	 * @param projectStateManageDto the projectStateManageDto to set
	 */
	public void setProjectStateManageDto(ProjectStateManageDto projectStateManageDto) {
		this.projectStateManageDto = projectStateManageDto;
	}

	/**
	 * get engineeringProjectList
	 * @return the engineeringProjectList
	 */
	public List<EngineeringProject> getEngineeringProjectList() {
		return engineeringProjectList;
	}

	/**
	 * set engineeringProjectList
	 * @param engineeringProjectList the engineeringProjectList to set
	 */
	public void setEngineeringProjectList(List<EngineeringProject> engineeringProjectList) {
		this.engineeringProjectList = engineeringProjectList;
	}

}
