package com.qylm.bean.engineeringMaterials;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

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
import com.qylm.bean.FileExportBean;
import com.qylm.bean.UserBean;
import com.qylm.bean.engineering.EngineeringProjectCreateBean;
import com.qylm.bean.engineering.ProjectStateCreateBean;
import com.qylm.bean.returner.Returner;
import com.qylm.bean.returner.engineering.EngineeringProjectManageReturner;
import com.qylm.bean.returner.engineering.ProjectStateManageReturner;
import com.qylm.bean.returner.engineeringMaterials.MaterialsGrantManageProjectReturner;
import com.qylm.common.ExcelFiles;
import com.qylm.common.Message;
import com.qylm.common.MothedUtil;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.common.expand.impl.EngineeringProjectFileExportServiceImpl;
import com.qylm.common.utils.BigDecimalUtil;
import com.qylm.common.utils.DateUtil;
import com.qylm.common.utils.DownloadUtil;
import com.qylm.common.utils.StringUtil;
import com.qylm.constants.Constants;
import com.qylm.constants.FileConstants;
import com.qylm.dto.engineering.EngineeringProjectManageDto;
import com.qylm.dto.engineeringMaterials.MaterialsGrantCreateDto;
import com.qylm.dto.engineeringMaterials.MaterialsGrantManageProjectDto;
import com.qylm.dxo.MaterialsGrantCreateDxo;
import com.qylm.entity.EngineeringProject;
import com.qylm.entity.EngineeringProjectDetail;
import com.qylm.entity.MaterialsGrant;
import com.qylm.entity.ProjectEmployeeDetail;
import com.qylm.service.EngineeringFinancialService;
import com.qylm.service.EngineeringProjectDetailService;
import com.qylm.service.EngineeringProjectService;
import com.qylm.service.FinancialCollectDetailService;
import com.qylm.service.FinancialPayDetailService;
import com.qylm.service.MaterialsGrantService;
import com.qylm.service.ProjectEmployeeDetailService;

/**
 * 工程项目管理
 * @author qylm
 */
@ManagedBean
@RequestScoped
public class MaterialsGrantManageProjectBean extends BasePagingBean {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2092014040015417190L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(MaterialsGrantManageProjectBean.class);
	
	/**
	 * 保存原料发放所需要的值
	 */
	private MaterialsGrantCreateDto materialsGrantCreateDto = new MaterialsGrantCreateDto();
	private MaterialsGrantManageProjectDto materialsGrantManageProjectDto = new MaterialsGrantManageProjectDto();

	/**
	 * 工程项目列表（检索结果）
	 */
	private List<EngineeringProject> engineeringProjectList;
	
	/**
	 * 工程项目详细列表（检索结果）
	 */
	private List<EngineeringProjectDetail> engineeringProjectDetailList;
	
	/**
	 * 删除复选框选择的值
	 */
	private EngineeringProject[] selectedModels;

	/**
	 * 工程项目管理bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;

	/**
	 * 工程项目管理业务类
	 */
	@ManagedProperty(value="#{engineeringProjectService}")
	private EngineeringProjectService engineeringProjectService;
	
	/**
	 * 工程项目详细业务类
	 */
	@ManagedProperty(value="#{engineeringProjectDetailService}")
	private EngineeringProjectDetailService engineeringProjectDetailService;
	
	/**
	 * 工程项目详细下的工作人员业务类
	 */
	@ManagedProperty(value="#{projectEmployeeDetailService}")
	private ProjectEmployeeDetailService projectEmployeeDetailService;
	
	/**
	 * 原料发放记录业务类
	 */
	@ManagedProperty(value="#{materialsGrantService}")
	private MaterialsGrantService materialsGrantService;
	

	/**
	 * 查询出所有工程项目列表
	 * 
	 * @return 工程项目管理画面
	 */
	public String getAll() {
		Tool.sendLog(LOG, userBean, "按下【功能菜单_工程项目管理按钮】");
		fetchData(0, true);
		return Navigation.MATERIALS_GRANT_PROJECT_MANAGE;
	}

	/**
	 * 此方法绑定于项目管理画面的修改按钮 
	 * 实现功能：根据修改的对象，跳转至工作登记登陆画面
	 * @return 工作登记登陆画面
	 */
	public String updateEngineeringProject(EngineeringProject transferEngineeringProject) {
		Tool.sendLog(LOG, userBean, "按下【工作登记管理画面_详细按钮】");
		return Tool.getBackBean(MaterialsGrantCreateProjectBean.class).updateDetail(new MaterialsGrantManageProjectReturner(materialsGrantManageProjectDto, currentPage), transferEngineeringProject);
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
	 * 共通搜索条件
	 * @return
	 */
	private DetachedCriteria getDetached() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EngineeringProject.class);
		detachedCriteria.createAlias(EngineeringProject.CUSTOMER, EngineeringProject.CUSTOMER, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias(EngineeringProject.EMPLOYEE, EngineeringProject.EMPLOYEE, JoinType.LEFT_OUTER_JOIN);
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		detachedCriteria.addOrder(Order.asc((EngineeringProject.TYPE)));
		detachedCriteria.addOrder(Order.desc(EngineeringProject.CREATE_DATE));
		String number = materialsGrantManageProjectDto.getVehicleNumber();
		String customerName = materialsGrantManageProjectDto.getCustomerName();
		Date beginDate = materialsGrantManageProjectDto.getStartBeginDate();
		Date endDate = materialsGrantManageProjectDto.getEndBeginDate();
		String constructionName = materialsGrantManageProjectDto.getConstructionName();
		String workAddress = materialsGrantManageProjectDto.getWorkAddress();
		String type = materialsGrantManageProjectDto.getType();
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
	 * 需要返回本画面时都调用此共通方法
	 * @param currentPage 当前页数
	 * @return 个人工程项目管理画面
	 */
	public String back(int currentPage) {
		reflushCurrentPage(currentPage);
		return Navigation.MATERIALS_GRANT_PROJECT_MANAGE;
	}
	
//	/**
//	 * 此方法绑定于项目管理画面的修改按钮 
//	 * 实现功能：根据修改的对象，跳转至工作登记登陆画面
//	 * @return 工作登记登陆画面
//	 */
//	public String updateEngineeringProject(EngineeringProject transferEngineeringProject) {
//		Tool.sendLog(LOG, userBean, "按下【工作登记管理画面_详细按钮】");
//		return Tool.getBackBean(MaterialsGrantCreate.class).updateDetail(new MaterialsGrantManageProjectReturner(materialsGrantManageProjectDto, currentPage), transferEngineeringProject);
//	}
//	
	
	/**
	 * 登记工作情况
	 * @param engineeringProjectDetail
	 */
	public void registerEngineeringProjectDetail(EngineeringProjectDetail engineeringProjectDetail) {
		Tool.sendLog(LOG, userBean, "按下【工作登记管理画面_登记工作情况按钮】");
		materialsGrantManageProjectDto.setAddress(engineeringProjectDetail.getEngineeringProject().getWorkAddress());
		materialsGrantManageProjectDto.setWorkVolume(engineeringProjectDetail.getWorkVolume());
		materialsGrantManageProjectDto.setGasVolume(engineeringProjectDetail.getGasVolume());
		materialsGrantManageProjectDto.setGasPrice(engineeringProjectDetail.getGasPrice());
		materialsGrantManageProjectDto.setSchedule(engineeringProjectDetail.getSchedule());
		materialsGrantManageProjectDto.setPumpParts(engineeringProjectDetail.getPumpParts());
		materialsGrantManageProjectDto.setStartPumpDate(engineeringProjectDetail.getStartPumpDate());
		materialsGrantManageProjectDto.setEndPumpDate(engineeringProjectDetail.getEndPumpDate());
		materialsGrantManageProjectDto.setEngineeringProjectDetail(engineeringProjectDetail);
	}
	
	/**
	 * 此方法绑定于原料发放记录登陆画面的保存按钮 
	 * 实现功能：根据transferMaterialsGrant对象判断当前操作时保存还是更新
	 * @return 画面不跳转
	 */
	public void saveMaterialsGrant() {
		Tool.sendLog(LOG, userBean, "【原料发放记录登陆画面_保存按钮】");
		MaterialsGrant transferMaterialsGrant = materialsGrantCreateDto.getTransferMaterialsGrant();
		EngineeringProjectDetail engineeringProjectDetail = materialsGrantManageProjectDto.getEngineeringProjectDetail();
		EngineeringProject engineeringProject = engineeringProjectDetail.getEngineeringProject();
		if (transferMaterialsGrant == null) {
			transferMaterialsGrant = new MaterialsGrant();
			materialsGrantCreateDto.setCreater(userBean.getUser());
			materialsGrantCreateDto.setBelongingUser(userBean.getUser().getBelongingUser());
			materialsGrantCreateDto.setEngineeringProjectDetail(materialsGrantManageProjectDto.getEngineeringProjectDetail());
			create(transferMaterialsGrant);
			transferMaterialsGrant.setCreateDate(DateUtil.getNowyyyymmddhhmmss());
			materialsGrantService.saveEntity(transferMaterialsGrant);
			materialsGrantCreateDto.setTransferMaterialsGrant(transferMaterialsGrant);
			engineeringProjectDetail.setEmployee(transferMaterialsGrant.getEmployee());
			//更新数据
			BigDecimal engineeringProjectSchedule = materialsGrantCreateDto.getReceiveSum();
			BigDecimal schedule = materialsGrantManageProjectDto.getSchedule();
			if (engineeringProjectSchedule == null) {
				engineeringProjectSchedule = Constants.BIGDECIMAL_ZERO;
			}
			if (schedule == null) {
				schedule = Constants.BIGDECIMAL_ZERO;
			}
			engineeringProjectDetail.setSchedule(BigDecimalUtil.add(engineeringProjectSchedule,materialsGrantManageProjectDto.getSchedule()));
			engineeringProject.setSchedule(engineeringProjectDetail.getSchedule());
			//设置项目状态（需要改）
			BigDecimal shiji = engineeringProject.getSchedule();
			BigDecimal yusuan = engineeringProject.getActualVolume();
			BigDecimal chazhi = shiji.subtract(yusuan);
			//设置允许的误差
			double wucha = 0.15;
			if((!chazhi.equals(0)) && chazhi.divide(yusuan).doubleValue()> wucha){
			engineeringProject.setType("8");
			}else{
			engineeringProject.setType("2");
			}
			engineeringProjectDetailService.updateEngineeringProjectDetail(engineeringProjectDetail, false);
			materialsGrantCreateDto.setTransferMaterialsGrant(null);
			Tool.sendErrorMessage(Message.GENERAL_SAVESUCCESS);
		} else {
			create(transferMaterialsGrant);
			transferMaterialsGrant.setUpdateDate(DateUtil.getNowyyyymmddhhmmss());
			materialsGrantService.updateEntity(transferMaterialsGrant);
			Tool.sendErrorMessage(Message.GENERAL_UPDATESUCCESS);
		}
//  			
//		materialsGrantService.updateEngineeringProjectSendSms(engineeringProjectDetail);
	}
	
	/**
	 * 此方法绑定于工程项目管理画面的检索按钮 
	 * 实现功能：根据检索条件，检索出工程项目
	 * @return 画面不跳转
	 */
	public String selectMaterialsGrant(Returner<?, ?, ?> returner) {
		Tool.sendLog(LOG, userBean, "按下【工程项目管理画面_检索按钮】");
		fetchData(0, true);
		return Navigation.MATERIALS_GRANT_PROJECT_MANAGE;
	}
	
	/**
	 * 此方法绑定于工程项目管理画面的检索按钮 
	 * 实现功能：根据检索条件，检索出工程项目
	 * @return 画面不跳转
	 */
	public String selectMaterialsGrant() {
		Tool.sendLog(LOG, userBean, "按下【工程项目管理画面_检索按钮】");
		fetchData(0, true);
		return Navigation.MATERIALS_GRANT_PROJECT_MANAGE;
	}
	
	/**
	 * 赋值
	 * @param transferMaterialsGrant
	 */
	private void create(MaterialsGrant transferMaterialsGrant) {
		MaterialsGrantCreateDxo.dtoToEntity(materialsGrantCreateDto, transferMaterialsGrant);
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

	/**
	 * get selectedModels
	 * @return the selectedModels
	 */
	public EngineeringProject[] getSelectedModels() {
		return selectedModels;
	}

	/**
	 * @param materialsGrantCreateDto the materialsGrantCreateDto to set
	 */
	public void setMaterialsGrantCreateDto(
			MaterialsGrantCreateDto materialsGrantCreateDto) {
		this.materialsGrantCreateDto = materialsGrantCreateDto;
	}
	/**
	 * @param materialsGrantService the materialsGrantService to set
	 */
	public void setMaterialsGrantService(MaterialsGrantService materialsGrantService) {
		this.materialsGrantService = materialsGrantService;
	}
	/**
	 * @return the materialsGrantCreateDto
	 */
	public MaterialsGrantCreateDto getMaterialsGrantCreateDto() {
		return materialsGrantCreateDto;
	}
	/**
	 * @return the materialsGrantManageProjectDto
	 */
	public MaterialsGrantManageProjectDto getMaterialsGrantManageProjectDto() {
		return materialsGrantManageProjectDto;
	}
	/**
	 * @param materialsGrantManageProjectDto the materialsGrantManageProjectDto to set
	 */
	public void setMaterialsGrantManageProjectDto(
			MaterialsGrantManageProjectDto materialsGrantManageProjectDto) {
		this.materialsGrantManageProjectDto = materialsGrantManageProjectDto;
	}
	
}
