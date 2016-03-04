package com.qylm.bean.engineering;

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
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.qylm.bean.BasePagingBean;
import com.qylm.bean.FileExportBean;
import com.qylm.bean.UserBean;
import com.qylm.bean.returner.Returner;
import com.qylm.bean.returner.engineering.EngineeringProjectManageReturner;
import com.qylm.common.ExcelFiles;
import com.qylm.common.Message;
import com.qylm.common.MothedUtil;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.common.expand.impl.EngineeringProjectFileExportServiceImpl;
import com.qylm.common.utils.BigDecimalUtil;
import com.qylm.common.utils.DownloadUtil;
import com.qylm.common.utils.StringUtil;
import com.qylm.constants.Constants;
import com.qylm.constants.FileConstants;
import com.qylm.dto.engineering.EngineeringProjectManageDto;
import com.qylm.entity.EngineeringProject;
import com.qylm.entity.EngineeringProjectDetail;
import com.qylm.entity.ProjectEmployeeDetail;
import com.qylm.service.EngineeringFinancialService;
import com.qylm.service.EngineeringProjectDetailService;
import com.qylm.service.EngineeringProjectService;
import com.qylm.service.FinancialCollectDetailService;
import com.qylm.service.FinancialPayDetailService;
import com.qylm.service.ProjectEmployeeDetailService;

/**
 * 工程项目管理
 * @author qylm
 */
@ManagedBean
@RequestScoped
public class EngineeringProjectManageBean extends BasePagingBean {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2092014040015417190L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(EngineeringProjectManageBean.class);
	
	/**
	 * 保存工程项目管理画面需要保存的值
	 */
	private EngineeringProjectManageDto engineeringProjectManageDto = new EngineeringProjectManageDto();

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
	 * 工程收支明细管理业务类
	 */
	@ManagedProperty(value="#{engineeringFinancialService}")
	private EngineeringFinancialService engineeringFinancialService;
	
	/**
	 * 工程收款明细业务类
	 */
	@ManagedProperty(value="#{financialCollectDetailService}")
	private FinancialCollectDetailService financialCollectDetailService;
	
	/**
	 * 工程支付明细业务类
	 */
	@ManagedProperty(value="#{financialPayDetailService}")
	private FinancialPayDetailService financialPayDetailService;
	
	/**
	 * 查询出所有工程项目列表
	 * 
	 * @return 工程项目管理画面
	 */
	public String getAll() {
		Tool.sendLog(LOG, userBean, "按下【功能菜单_工程项目管理按钮】");
		fetchData(0, true);
		return Navigation.ENGINEERING_PROJECT_MANAGE;
	}

	/**
	 * 此方法绑定于项目管理画面的新建按钮 
	 * 实现功能：跳转至工程项目登陆画面，新建一家工程项目
	 * @return 工程项目登陆画面
	 */
	public String newEngineeringProject() {
		Tool.sendLog(LOG, userBean, "按下【工程项目管理画面_新建按钮】");
		return Tool.getBackBean(EngineeringProjectCreateBean.class).newCreate(new EngineeringProjectManageReturner(engineeringProjectManageDto, currentPage));
	}

	/**
	 * 此方法绑定于项目管理画面的修改按钮 
	 * 实现功能：根据修改的对象，跳转至工程项目登陆画面
	 * @return 工程项目登陆画面
	 */
	public String updateEngineeringProject(EngineeringProject transferEngineeringProject) {
		Tool.sendLog(LOG, userBean, "按下【工程项目管理画面_修改按钮】");
		return Tool.getBackBean(EngineeringProjectCreateBean.class).updateDetail(new EngineeringProjectManageReturner(engineeringProjectManageDto, currentPage), transferEngineeringProject);
	}
	
	/**
	 * 此方法绑定于工程项目管理画面的检索按钮 
	 * 实现功能：根据检索条件，检索出工程项目
	 * @return 画面不跳转
	 */
	public String selectEngineeringProject(Returner<?, ?, ?> returner) {
		Tool.sendLog(LOG, userBean, "按下【工程项目管理画面_检索按钮】");
		fetchData(0, true);
		return Navigation.ENGINEERING_PROJECT_MANAGE;
	}
	
	/**
	 * 绑定于工程项目管理画面的全选删除按钮
	 * @param event
	 */
	public void deleteMul(ActionEvent event) {
		Tool.sendLog(LOG, userBean, "按下【工程项目管理画面_批量删除按钮】");
		if (selectedModels != null) {
			List<EngineeringProject> asList = Arrays.asList(selectedModels);
			engineeringProjectList.removeAll(asList);
			engineeringProjectService.deleteEntityAll(asList);
			removeData(1, engineeringProjectList.isEmpty());
			Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
		}
	}

	/**
	 * 此方法绑定于工程项目管理画面的删除按钮 
	 * 实现功能：先移除集合内的数据，在移除数据库内的数据
	 * @return 画面不跳转
	 */
	public void deleteEngineeringProject(EngineeringProject transferEngineeringProject) {
		Tool.sendLog(LOG, userBean, "按下【工程项目管理画面的_删除按钮】");
		engineeringProjectList.remove(transferEngineeringProject);
		engineeringProjectService.deleteEntity(transferEngineeringProject);
		removeData(1, engineeringProjectList.isEmpty());
		Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
	}
	
	/**
	 * 此方法绑定于工程项目管理画面的安排车辆按钮
	 * 实现功能：列出车辆安排列表
	 * @return 画面不跳转
	 */
	public void arrangeVehicle(EngineeringProject transferEngineeringProject) {
		Tool.sendLog(LOG, userBean, "按下【工程项目管理画面的_安排车辆按钮】");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EngineeringProjectDetail.class);
		detachedCriteria.createAlias(EngineeringProjectDetail.ENGINEERING_PROJECT, EngineeringProjectDetail.ENGINEERING_PROJECT, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias(EngineeringProjectDetail.VEHICLE_INFOL, EngineeringProjectDetail.VEHICLE_INFOL, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.add(Restrictions.eq(EngineeringProjectDetail.ENGINEERING_PROJECT, transferEngineeringProject));
		engineeringProjectDetailList = engineeringProjectDetailService.getByDetachedCriteria(detachedCriteria);
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
	
	/**
	 * 获取到总预算金额
	 * @return
	 */
	public BigDecimal getMoney() {
		DetachedCriteria detachedCriteria = getDetached();
		detachedCriteria.setProjection(Projections.sum(EngineeringProject.MONEY));
		List<Object[]> objectList = engineeringProjectService.getByDetachedCriteriaForObject(detachedCriteria);
		BigDecimal sum = Constants.BIGDECIMAL_ZERO;
		if (!objectList.isEmpty()) {
			Object[] objects = objectList.toArray();
			sum = BigDecimalUtil.toBigDecimal(objects[0]);
		}
		return sum;
	}
	
	/**
	 * 获取到总已收款
	 * @return
	 */
	public BigDecimal getCollectMoney() {
		BigDecimal sum = Constants.BIGDECIMAL_ZERO;
//		DetachedCriteria detachedCriteria = getDetached();
//		detachedCriteria.setProjection(Projections.property(EngineeringProject.BASE_ID));
//		List<Object[]> objectList = engineeringProjectService.getByDetachedCriteriaForObject(detachedCriteria);
//		if (!objectList.isEmpty()) {
//			Object[] objects = objectList.toArray();
//			DetachedCriteria criteria = DetachedCriteria.forClass(FinancialCollectDetail.class);
//			criteria.createAlias(FinancialCollectDetail.ENGINEERING_FINANCIAL, FinancialCollectDetail.ENGINEERING_FINANCIAL, JoinType.LEFT_OUTER_JOIN);
//			criteria.createAlias(FinancialCollectDetail.ENGINEERING_FINANCIAL_ENGINEERING_PROJECT, FinancialCollectDetail.ENGINEERING_PROJECT, JoinType.LEFT_OUTER_JOIN);
//			criteria.add(Restrictions.in(FinancialCollectDetail.ENGINEERING_PROJECT_ID, objects));
//			criteria.setProjection(Projections.sum(FinancialCollectDetail.MONEY));
//			List<Object[]> collectList = financialCollectDetailService.getByDetachedCriteriaForObject(criteria);
//			Object[] array = collectList.toArray();
//			if (array[0] != null) {
//				sum = BigDecimalUtil.toBigDecimal(array[0]);
//			}
//		}
		return sum;
	}
	
	@Override
	protected void fetchData(int start, boolean needInit) {
		DetachedCriteria detachedCriteria = getDetached();
		engineeringProjectList = engineeringProjectService.getByDetachedCriteria(detachedCriteria, start, onePageCount);
		if (needInit) {
			init(engineeringProjectService.getRowCount(detachedCriteria));
		}
		
//		if (!engineeringProjectList.isEmpty()) {
//			// 获取到项目收支明细
//			detachedCriteria = DetachedCriteria.forClass(EngineeringFinancial.class);
//			detachedCriteria.createAlias(EngineeringFinancial.ENGINEERING_PROJECT, EngineeringFinancial.ENGINEERING_PROJECT, JoinType.LEFT_OUTER_JOIN);
//			detachedCriteria.add(Restrictions.in(EngineeringFinancial.ENGINEERING_PROJECT, engineeringProjectList));
//			List<EngineeringFinancial> engineeringFinancialList = engineeringFinancialService.getByDetachedCriteria(detachedCriteria);
//			// 如果存在有收支明细
//			if (!engineeringFinancialList.isEmpty()) {
//				// 获取到收款明细
//				detachedCriteria = DetachedCriteria.forClass(FinancialCollectDetail.class);
//				detachedCriteria.createAlias(FinancialCollectDetail.ENGINEERING_FINANCIAL, FinancialCollectDetail.ENGINEERING_FINANCIAL, JoinType.LEFT_OUTER_JOIN);
//				detachedCriteria.add(Restrictions.in(FinancialCollectDetail.ENGINEERING_FINANCIAL, engineeringFinancialList));
//				detachedCriteria.add(Restrictions.eq(FinancialCollectDetail.STATE, true));
//				List<FinancialCollectDetail> financialCollectDetailList = financialCollectDetailService.getByDetachedCriteria(detachedCriteria);
//				// 获取到支付明细
//				detachedCriteria = DetachedCriteria.forClass(FinancialPayDetail.class);
//				detachedCriteria.createAlias(FinancialPayDetail.ENGINEERING_FINANCIAL, FinancialPayDetail.ENGINEERING_FINANCIAL, JoinType.LEFT_OUTER_JOIN);
//				detachedCriteria.add(Restrictions.eq(FinancialPayDetail.STATE, true));
//				detachedCriteria.add(Restrictions.in(FinancialPayDetail.ENGINEERING_FINANCIAL, engineeringFinancialList));
//				List<FinancialPayDetail> financialPayDetailList = financialPayDetailService.getByDetachedCriteria(detachedCriteria);
//				
//				for (EngineeringFinancial engineeringFinancial : engineeringFinancialList) {
//					// 收款金额
//					BigDecimal collectMoney = Constants.BIGDECIMAL_ZERO;
//					// 消费金额
//					BigDecimal payMoney = Constants.BIGDECIMAL_ZERO;
//					
//					for (FinancialCollectDetail financialCollectDetail : financialCollectDetailList) {
//						if (engineeringFinancial.equals(financialCollectDetail.getEngineeringFinancial())) {
//							collectMoney = BigDecimalUtil.add(collectMoney, financialCollectDetail.getMoney());
//						}
//					}
//					for (FinancialPayDetail financialPayDetail : financialPayDetailList) {
//						if (engineeringFinancial.equals(financialPayDetail.getEngineeringFinancial())) {
//							payMoney = BigDecimalUtil.add(payMoney, financialPayDetail.getMoney());
//						}
//					}
//					EngineeringProject engineeringProject = engineeringFinancial.getEngineeringProject();
//					engineeringProject.setCollectMoney(collectMoney);
//					engineeringProject.setPayMoney(payMoney);
//				}
//			}
//			BigDecimal sumMoney = Constants.BIGDECIMAL_ZERO;
//			BigDecimal sumSchedule = Constants.BIGDECIMAL_ZERO;
//			BigDecimal sumCollectMoney = Constants.BIGDECIMAL_ZERO;
//			for (EngineeringProject engineeringProject : engineeringProjectList) {
//				for (EngineeringFinancial engineeringFinancial : engineeringFinancialList) {
//					if (engineeringProject.equals(engineeringFinancial.getEngineeringProject())) {
//						engineeringProject.setCollectMoney(engineeringFinancial.getEngineeringProject().getCollectMoney());
//						engineeringProject.setPayMoney(engineeringFinancial.getEngineeringProject().getPayMoney());
//						break;
//					}
//				}
//				sumMoney = BigDecimalUtil.add(sumMoney, engineeringProject.getMoney());
//				sumSchedule = BigDecimalUtil.add(sumSchedule, engineeringProject.getSchedule());
//				sumCollectMoney = BigDecimalUtil.add(sumCollectMoney, engineeringProject.getCollectMoney());
//			}
//			engineeringProjectManageDto.setSumMoney(sumMoney);
//			engineeringProjectManageDto.setSumSchedule(sumSchedule);
//			engineeringProjectManageDto.setSumCollectMoney(sumCollectMoney);
//		}
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
		detachedCriteria.addOrder(Order.desc(EngineeringProject.BEGIN_DATE));
		String customerName = engineeringProjectManageDto.getCustomerName();
		Date beginDate = engineeringProjectManageDto.getBeginDate();
		Date endDate = engineeringProjectManageDto.getEndDate();
		Date startEndDate = engineeringProjectManageDto.getStartEndDate();
		Date endEndDate = engineeringProjectManageDto.getEndEndDate();
		String constructionName = engineeringProjectManageDto.getConstructionName();
		String workAddress = engineeringProjectManageDto.getWorkAddress();
		String type = engineeringProjectManageDto.getType();
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
	 * 需要返回本画面时都调用此共通方法
	 * @param currentPage 当前页数
	 * @return 个人工程项目管理画面
	 */
	public String back(int currentPage) {
		reflushCurrentPage(currentPage);
		return Navigation.ENGINEERING_PROJECT_MANAGE;
	}
	
	/**
	 * 此方法绑定与下载模板按钮
	 * 实现功能：下载数据导入的规范模板格式
	 */
	public void downloadTemplet() {
		Tool.sendLog(LOG, userBean, "按下【工程项目管理画面的_下载模板按钮】");
		StringBuffer sb = new StringBuffer();
		sb.append("工程项目导入模板");
		sb.append(FileConstants.EX_NAME_XLS);
		// 获取到模板文件，然后转换成二进制数组
		String url = FileConstants.TEMPLET_DOWNLOAD_PATH + ExcelFiles.DOWNLOAD_ENGINEERING_PROJECT_XLS;
		DownloadUtil.downloadFile(FileConstants.analyticExcel(url), sb.toString());
	}
	
	/**
	 * 批量导入
	 * @param event
	 */
	public String fileUpload() {
		Tool.sendLog(LOG, userBean, "按下【工程项目管理画面的_导入按钮】");
		EngineeringProjectManageReturner returner = new EngineeringProjectManageReturner(engineeringProjectManageDto, currentPage);
		return Tool.getBackBean(FileExportBean.class).infoFileUpload(returner, EngineeringProject.DISCRIMINATOR_ENGINEERING_PROJECT, null, new EngineeringProjectFileExportServiceImpl());
	}

	/**
	 * @param engineeringFinancialService the engineeringFinancialService to set
	 */
	public void setEngineeringFinancialService(
			EngineeringFinancialService engineeringFinancialService) {
		this.engineeringFinancialService = engineeringFinancialService;
	}

	/**
	 * @param financialCollectDetailService the financialCollectDetailService to set
	 */
	public void setFinancialCollectDetailService(
			FinancialCollectDetailService financialCollectDetailService) {
		this.financialCollectDetailService = financialCollectDetailService;
	}

	/**
	 * @param financialPayDetailService the financialPayDetailService to set
	 */
	public void setFinancialPayDetailService(
			FinancialPayDetailService financialPayDetailService) {
		this.financialPayDetailService = financialPayDetailService;
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
	 * get engineeringProjectManageDto
	 * @return the engineeringProjectManageDto
	 */
	public EngineeringProjectManageDto getEngineeringProjectManageDto() {
		return engineeringProjectManageDto;
	}

	/**
	 * set engineeringProjectManageDto
	 * @param engineeringProjectManageDto the engineeringProjectManageDto to set
	 */
	public void setEngineeringProjectManageDto(EngineeringProjectManageDto engineeringProjectManageDto) {
		this.engineeringProjectManageDto = engineeringProjectManageDto;
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
	 * set selectedModels
	 * @param selectedModels the selectedModels to set
	 */
	public void setSelectedModels(EngineeringProject[] selectedModels) {
		this.selectedModels = selectedModels;
	}

}
