package com.qylm.bean.engineering;

import java.io.Serializable;
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
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.qylm.bean.UserBean;
import com.qylm.bean.returner.Returner;
import com.qylm.common.CreateBean;
import com.qylm.common.Message;
import com.qylm.common.MothedUtil;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.common.utils.BigDecimalUtil;
import com.qylm.common.utils.DateUtil;
import com.qylm.common.utils.StringUtil;
import com.qylm.constants.Constants;
import com.qylm.dto.engineering.EngineeringProjectCreateDto;
import com.qylm.dxo.EngineeringProjectCreateDxo;
import com.qylm.entity.Customer;
import com.qylm.entity.Employee;
import com.qylm.entity.EngineeringProject;
import com.qylm.entity.EngineeringProjectDetail;
import com.qylm.entity.ProjectEmployeeDetail;
import com.qylm.entity.VehicleInfo;
import com.qylm.entity.VehicleInfoDetail;
import com.qylm.service.EmployeeService;
import com.qylm.service.EngineeringProjectDetailService;
import com.qylm.service.EngineeringProjectService;
import com.qylm.service.ProjectEmployeeDetailService;
import com.qylm.service.VehicleInfoDetailService;
import com.qylm.service.VehicleInfoService;

/**
 * 工程项目登陆画面bean
 * @author 
 */
@ManagedBean
@RequestScoped
public class EngineeringProjectCreateBean implements CreateBean<EngineeringProject>, Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5886231499796256531L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(EngineeringProjectCreateBean.class);

	/**
	 * 存放工程项目登陆画面需要保存的值
	 */
	private EngineeringProjectCreateDto engineeringProjectCreateDto = new EngineeringProjectCreateDto();
	
	/**
	 * 选择开机人员
	 */
	private Employee[] selectedModels;
	
	/**
	 * 工程项目bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	/**
	 * 工程项目业务类
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
	 * 车辆管理业务类
	 */
	@ManagedProperty(value="#{vehicleInfoService}")
	private VehicleInfoService vehicleInfoService;
	
	/**
	 * 车辆管理详细业务类
	 */
	@ManagedProperty(value="#{vehicleInfoDetailService}")
	private VehicleInfoDetailService vehicleInfoDetailService;
	
	/**
	 * 员工管理业务类
	 */
	@ManagedProperty(value="#{employeeService}")
	private EmployeeService employeeService;
	
	/**
	 * 此方法绑定于工程项目登陆画面的新建按钮 
	 * 实现功能：清空注入信息，并新建一个工程项目
	 * @return 工程项目登陆画面
	 */
	public String newEngineeringProject() {
		Tool.sendLog(LOG, userBean, "【工程项目登陆画面_新建按钮】");
		engineeringProjectCreateDto.setCustomer(null);
		engineeringProjectCreateDto.setEmployee(null);
		engineeringProjectCreateDto.setBeginDate(null);
		engineeringProjectCreateDto.setEndDate(null);
		engineeringProjectCreateDto.setWorkAddress(null);
		engineeringProjectCreateDto.setConstructionName(null);
		engineeringProjectCreateDto.setRemark(null);
		engineeringProjectCreateDto.setMoney(null);
		engineeringProjectCreateDto.setPumpPrice(null);
		engineeringProjectCreateDto.setActualVolume(null);
		engineeringProjectCreateDto.setSchedule(null);
		engineeringProjectCreateDto.setType(EngineeringProject.TYPE_1);
		engineeringProjectCreateDto.setEngineeringProjectDetailList(null);
		engineeringProjectCreateDto.setEngineeringProjectDetail(null);
		engineeringProjectCreateDto.setVehicleInfoList(null);
		engineeringProjectCreateDto.setNumber(null);
		engineeringProjectCreateDto.setEmployeeName(null);
		engineeringProjectCreateDto.setEmployeeList(null);
		engineeringProjectCreateDto.setWorkNumber(null);
		engineeringProjectCreateDto.setName(null);
		engineeringProjectCreateDto.setCreater(null);
		engineeringProjectCreateDto.setBelongingUser(null);
		engineeringProjectCreateDto.setTransferEngineeringProject(null);
		return Navigation.ENGINEERING_PROJECT_CREATE;
	}
	
	/**
	 * 此方法绑定于工程项目登陆画面的返回按钮 
	 * 实现功能：返回共通方法
	 * @return 返回共通方法
	 */
	public String back() {
		Tool.sendLog(LOG, userBean, "【工程项目登陆画面_返回按钮】");
		return engineeringProjectCreateDto.getReturner().returnOnly();
	}

	/**
	 * 此方法绑定于工程项目登陆画面的保存按钮 
	 * 实现功能：根据transferEngineeringProject对象判断当前操作时保存还是更新
	 * @return 画面不跳转
	 */
	public void saveEngineeringProject() {
		Tool.sendLog(LOG, userBean, "【工程项目登陆画面_保存按钮】");
		EngineeringProject transferEngineeringProject = engineeringProjectCreateDto.getTransferEngineeringProject();
		if (transferEngineeringProject == null) {
			transferEngineeringProject = new EngineeringProject();
			engineeringProjectCreateDto.setCreater(userBean.getUser());
			engineeringProjectCreateDto.setBelongingUser(userBean.getUser().getBelongingUser());
			engineeringProjectCreateDto.setType(EngineeringProject.TYPE_1);
			create(transferEngineeringProject);
			transferEngineeringProject.setCreateDate(DateUtil.getNowyyyymmddhhmmss());
			engineeringProjectService.saveEngineeringProject(transferEngineeringProject, engineeringProjectCreateDto.getEngineeringProjectDetailList());
			engineeringProjectCreateDto.setTransferEngineeringProject(transferEngineeringProject);
			Tool.sendErrorMessage(Message.GENERAL_SAVESUCCESS);
		} else {
			transferEngineeringProject.setUpdateDate(DateUtil.getNowyyyymmddhhmmss());
			List<EngineeringProjectDetail> engineeringProjectDetailList = engineeringProjectCreateDto.getEngineeringProjectDetailList();
			if (engineeringProjectDetailList != null && !engineeringProjectDetailList.isEmpty()) {
				for (EngineeringProjectDetail engineeringProjectDetail : engineeringProjectDetailList) {
					engineeringProjectDetail.setCreateDate(DateUtil.getNowyyyymmdd());
				}
			}
			create(transferEngineeringProject);
			engineeringProjectService.updateEngineeringProject(transferEngineeringProject, engineeringProjectDetailList);
			Tool.sendErrorMessage(Message.GENERAL_UPDATESUCCESS);
		}
	}
	
	/**
	 * 此方法绑定与工程项目登记画面的确认按钮
	 * 实现功能：确认项目生效并不可以修改项目信息，并编辑短信发送车辆负责人
	 */
	public void queryEngineeringProject() {
		Tool.sendLog(LOG, userBean, "【工程项目登陆画面_保存按钮】");
		EngineeringProject transferEngineeringProject = engineeringProjectCreateDto.getTransferEngineeringProject();
		transferEngineeringProject.setType(EngineeringProject.TYPE_2);
		engineeringProjectCreateDto.setType(EngineeringProject.TYPE_2);
		engineeringProjectService.updateEngineeringProjectSendSms(transferEngineeringProject, engineeringProjectCreateDto.getEngineeringProjectDetailList());
		Tool.sendErrorMessage(Message.GENERAL_UPDATESUCCESS);
	}
	
	/**
	 * 赋值
	 * @param transferEngineeringProject
	 */
	private void create(EngineeringProject transferEngineeringProject) {
		engineeringProjectCreateDto.setPumpPrice(engineeringProjectCreateDto.getCustomer().getPumpPrice());
		EngineeringProjectCreateDxo.dtoToEntity(engineeringProjectCreateDto, transferEngineeringProject);
	}

	public String newCreate(Returner<?, ?, ?> returner) {
		engineeringProjectCreateDto.setReturner(returner);
		engineeringProjectCreateDto.setType(EngineeringProject.TYPE_1);
		return Navigation.ENGINEERING_PROJECT_CREATE;
	}

	public String updateDetail(Returner<?, ?, ?> returner, EngineeringProject engineeringProject) {
		engineeringProjectCreateDto.setReturner(returner);
		EngineeringProjectCreateDxo.entityToDto(engineeringProject, engineeringProjectCreateDto);
		engineeringProjectCreateDto.setTransferEngineeringProject(engineeringProject);
		// 计算出预算金额
		Customer customer = engineeringProjectCreateDto.getCustomer();
		engineeringProjectCreateDto.setPumpPrice(customer.getPumpPrice());
		String type = customer.getType();
		if (Customer.TYPE_1.equals(type))  {
			if (BigDecimalUtil.isNotNullOrZero(engineeringProjectCreateDto.getActualVolume())) {
				engineeringProjectCreateDto.setMoney(BigDecimalUtil.multiply(engineeringProjectCreateDto.getActualVolume(), engineeringProjectCreateDto.getPumpPrice()));
			}
		}
		getVehicleDateil();
		return Navigation.ENGINEERING_PROJECT_CREATE;
	}
	
	/**
	 * 增加一行工程项目负责人信息
	 */
	public void addVehicleDateil() {
		Tool.sendLog(LOG, userBean, "【工程项目登陆画面_安排工作车辆按钮】");
		List<EngineeringProjectDetail> engineeringProjectDetailList = engineeringProjectCreateDto.getEngineeringProjectDetailList();
		if (engineeringProjectDetailList == null) {
			engineeringProjectDetailList = new ArrayList<EngineeringProjectDetail>();
			engineeringProjectCreateDto.setEngineeringProjectDetailList(engineeringProjectDetailList);
		}
		EngineeringProjectDetail engineeringProjectDetail = new EngineeringProjectDetail();
		
		EngineeringProject transferEngineeringProject = engineeringProjectCreateDto.getTransferEngineeringProject();
		if (transferEngineeringProject != null) {
			engineeringProjectDetail.setEngineeringProject(transferEngineeringProject);
		}
		engineeringProjectDetail.setCreater(userBean.getUser());
		// 工作方量=实际方量 -已完成方量
		engineeringProjectDetail.setWorkVolume(BigDecimalUtil.subtract(engineeringProjectCreateDto.getActualVolume(), engineeringProjectCreateDto.getSchedule()));
		engineeringProjectDetail.setStartPumpDate(engineeringProjectCreateDto.getBeginDate());
		engineeringProjectDetail.setEndPumpDate(engineeringProjectCreateDto.getEndDate());
		engineeringProjectDetailList.add(engineeringProjectDetail);
	}
	
	/**
	 * 查询出所有车辆列表信息
	 * @param engineeringProjectDetail
	 */
	public void selectVehicleInfo(EngineeringProjectDetail engineeringProjectDetail) {
		Tool.sendLog(LOG, userBean, "【工程项目登陆画面_选择按钮】");
		engineeringProjectCreateDto.setEngineeringProjectDetail(engineeringProjectDetail);
		selectVehicle();
	}

	/**
	 * 查询出所有车辆列表信息
	 * @param engineeringProjectDetail
	 */
	public void selectVehicleInfo() {
		Tool.sendLog(LOG, userBean, "【工程项目登陆画面_搜索工作车辆按钮】");
		selectVehicle();
	}
	
	/**
	 * 查询出车辆信息
	 */
	private void selectVehicle() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(VehicleInfo.class);
		detachedCriteria.createAlias(VehicleInfo.EMPLOYEE, VehicleInfo.EMPLOYEE, JoinType.LEFT_OUTER_JOIN);
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		String number = engineeringProjectCreateDto.getNumber();
		String employeeName = engineeringProjectCreateDto.getEmployeeName();
		if (StringUtil.isNotBlank(number)) {
			detachedCriteria.add(Restrictions.like(VehicleInfo.NUMBER, number, MatchMode.ANYWHERE));
		}
		if (StringUtil.isNotBlank(employeeName)) {
			detachedCriteria.add(Restrictions.like(VehicleInfo.EMPLOYEE_NAME, employeeName, MatchMode.ANYWHERE));
		}
		engineeringProjectCreateDto.setVehicleInfoList(vehicleInfoService.getByDetachedCriteria(detachedCriteria, 0, 20));
	}
	
	/**
	 * 选择车辆列表信息
	 * @param engineeringProjectDetail
	 */
	public void findVehicleInfo(VehicleInfo vehicleInfo) {
		Tool.sendLog(LOG, userBean, "【工程项目登陆画面_选择工作车辆按钮】");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(VehicleInfoDetail.class);
		detachedCriteria.createAlias(VehicleInfoDetail.VEHICLE_INFO, VehicleInfoDetail.VEHICLE_INFO, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias(VehicleInfoDetail.EMPLOYEE, VehicleInfoDetail.EMPLOYEE, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.add(Restrictions.eq(VehicleInfoDetail.VEHICLE_INFO, vehicleInfo));
		List<VehicleInfoDetail> vehicleInfoDetailList = vehicleInfoDetailService.getByDetachedCriteria(detachedCriteria);
		EngineeringProjectDetail engineeringProjectDetail = engineeringProjectCreateDto.getEngineeringProjectDetail();
		engineeringProjectDetail.setVehicleInfo(vehicleInfo);
		engineeringProjectDetail.setVehicleNumber(vehicleInfo.getNumber());
		List<ProjectEmployeeDetail> projectEmployeeDetailList = engineeringProjectDetail.getProjectEmployeeDetailList();
		if (projectEmployeeDetailList == null) {
			projectEmployeeDetailList = new ArrayList<ProjectEmployeeDetail>();
			engineeringProjectDetail.setProjectEmployeeDetailList(projectEmployeeDetailList);
		} else {
			projectEmployeeDetailList.clear();
		}
		if (!vehicleInfoDetailList.isEmpty()) {
			ProjectEmployeeDetail projectEmployeeDetail;
			for (VehicleInfoDetail vehicleInfoDetail : vehicleInfoDetailList) {
				projectEmployeeDetail = new ProjectEmployeeDetail();
				projectEmployeeDetail.setEngineeringProjectDetail(engineeringProjectDetail);
				projectEmployeeDetail.setEmployee(vehicleInfoDetail.getEmployee());
				projectEmployeeDetail.setBelongingUser(userBean.getUser().getBelongingUser());
				projectEmployeeDetailList.add(projectEmployeeDetail);
			}
		} else {
			ProjectEmployeeDetail projectEmployeeDetail = new ProjectEmployeeDetail();
			projectEmployeeDetail.setEngineeringProjectDetail(engineeringProjectDetail);
			projectEmployeeDetail.setEmployee(vehicleInfo.getEmployee());
			projectEmployeeDetail.setBelongingUser(userBean.getUser().getBelongingUser());
			projectEmployeeDetailList.add(projectEmployeeDetail);
		}
	}
	
	/**
	 * 删除车辆安排
	 * @param engineeringProjectDetail
	 */
	public void deleteEngineeringProjectDetail(EngineeringProjectDetail engineeringProjectDetail) {
		Tool.sendLog(LOG, userBean, "【工程项目登陆画面_删除工作车辆按钮】");
		List<EngineeringProjectDetail> engineeringProjectDetailList = engineeringProjectCreateDto.getEngineeringProjectDetailList();
		engineeringProjectDetailList.remove(engineeringProjectDetail);
		if (engineeringProjectDetail.getId() != null) {
			engineeringProjectDetailService.deleteEntity(engineeringProjectDetail);
		}
		Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
	}
	
	/**
	 * 查询出员工信息
	 */
	public void selectEmployee(EngineeringProjectDetail engineeringProjectDetail) {
		Tool.sendLog(LOG, userBean, "【工程项目登陆画面_搜索开机人员按钮】");
		engineeringProjectCreateDto.setEngineeringProjectDetail(engineeringProjectDetail);
		selectAllEmployee();
	}
	
	/**
	 * 查询出员工信息
	 */
	public void selectEmployee() {
		Tool.sendLog(LOG, userBean, "【工程项目登陆画面_按条件搜索开机人员按钮】");
		selectAllEmployee();
	}
	
	/**
	 * 查询出前10条员工信息
	 */
	private void selectAllEmployee() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Employee.class);
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		String workNumber = engineeringProjectCreateDto.getWorkNumber();
		String name = engineeringProjectCreateDto.getName();
		if (StringUtil.isNotBlank(workNumber)) {
			detachedCriteria.add(Restrictions.like(Employee.WORK_NUMBER, workNumber, MatchMode.ANYWHERE));
		}
		if (StringUtil.isNotBlank(name)) {
			detachedCriteria.add(Restrictions.like(Employee.NAME, name, MatchMode.ANYWHERE));
		}
		List<ProjectEmployeeDetail> projectEmployeeDetailList = engineeringProjectCreateDto.getEngineeringProjectDetail().getProjectEmployeeDetailList();
		if (projectEmployeeDetailList != null && !projectEmployeeDetailList.isEmpty()) {
			List<Integer> idList = new ArrayList<Integer>();
			for (ProjectEmployeeDetail projectEmployeeDetail : projectEmployeeDetailList) {
				idList.add(projectEmployeeDetail.getEmployee().getId());
			}
			detachedCriteria.add(Restrictions.not(Restrictions.in(Employee.BASE_ID, idList)));
		}
		List<Employee> list = employeeService.getByDetachedCriteria(detachedCriteria, 0, 10);
		// 获取到工作情况
		if (!list.isEmpty()) {
			detachedCriteria = DetachedCriteria.forClass(ProjectEmployeeDetail.class);
			detachedCriteria.createAlias(ProjectEmployeeDetail.ENGINEERING_PROJECT_DETAIL, ProjectEmployeeDetail.ENGINEERING_PROJECT_DETAIL, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.createAlias(ProjectEmployeeDetail.ENGINEERING_PROJECT_DETAIL_ENGINEERING_PROJECT, ProjectEmployeeDetail.ENGINEERING_PROJECT, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.createAlias(ProjectEmployeeDetail.EMPLOYEE, ProjectEmployeeDetail.EMPLOYEE, JoinType.LEFT_OUTER_JOIN);
			// 查询正在工作的时间，（查结束日期是否在当前时间内）
			Date nowyyyymmddhhmmss = DateUtil.getNowyyyymmddhhmmss();
			detachedCriteria.add(Restrictions.ge(ProjectEmployeeDetail.ENGINEERING_PROJECT_DETAIL_END_PUMP_DATE, nowyyyymmddhhmmss));
			detachedCriteria.add(Restrictions.le(ProjectEmployeeDetail.ENGINEERING_PROJECT_DETAIL_END_PUMP_DATE, nowyyyymmddhhmmss));
			detachedCriteria.add(Restrictions.in(ProjectEmployeeDetail.EMPLOYEE, list));
			projectEmployeeDetailList = projectEmployeeDetailService.getByDetachedCriteria(detachedCriteria);
			if (!projectEmployeeDetailList.isEmpty()) {
				for (Employee employee : list) {
					StringBuilder sb = new StringBuilder();
					for (ProjectEmployeeDetail projectEmployeeDetail : projectEmployeeDetailList) {
						if (employee.equals(projectEmployeeDetail.getEmployee())) {
							Date startPumpDate = projectEmployeeDetail.getEngineeringProjectDetail().getStartPumpDate();
							Date endPumpDate = projectEmployeeDetail.getEngineeringProjectDetail().getEndPumpDate();
							sb.append("已安排工作，工作时间");
							sb.append(DateUtil.formatDate(startPumpDate, Constants.YYYY_MM_DD_HH_MM_SS));
							sb.append(Constants.HORIZONTAL_LINE);
							sb.append(DateUtil.formatDate(endPumpDate, Constants.YYYY_MM_DD_HH_MM_SS));
							sb.append("，还需要");
							sb.append(DateUtil.getSubtractday(nowyyyymmddhhmmss, endPumpDate));
							sb.append("天，此员工才可以完成工作！");
							if (EngineeringProject.TYPE_4.equals(projectEmployeeDetail.getEngineeringProjectDetail().getEngineeringProject().getType())) {
								sb.append("<span style=\"color:#FF0000;\">注意：该员工所分配的工作处于工作暂停中</span>");
								
							} else if (EngineeringProject.TYPE_5.equals(projectEmployeeDetail.getEngineeringProjectDetail().getEngineeringProject().getType())) {
								sb.append("<span style=\"color:#FF0000;\">注意：该员工所分配的工作已完结</span>");
							}
							break;
						}
					}
					if (StringUtil.isObjBlank(sb.toString())) {
						sb.append("<span style=\"color:#00FF00;\">如系统正常使用中，该员处于待工中</span>");
					}
					employee.setWorkInfo(sb.toString());
				}
			}
		}
		engineeringProjectCreateDto.setEmployeeList(list);
	}
	
	/**
	 * 选择员工信息
	 * @param employee
	 */
	public void findEmployee(Employee employee) {
		Tool.sendLog(LOG, userBean, "【工程项目登陆画面_选择开机人员按钮】");
		EngineeringProjectDetail engineeringProjectDetail = engineeringProjectCreateDto.getEngineeringProjectDetail();
		List<ProjectEmployeeDetail> projectEmployeeDetailList = engineeringProjectDetail.getProjectEmployeeDetailList();
		if (projectEmployeeDetailList == null) {
			projectEmployeeDetailList = new ArrayList<ProjectEmployeeDetail>();
			engineeringProjectDetail.setProjectEmployeeDetailList(projectEmployeeDetailList);
		}
		ProjectEmployeeDetail projectEmployeeDetail = new ProjectEmployeeDetail();
		projectEmployeeDetail.setEmployee(employee);
		projectEmployeeDetail.setBelongingUser(userBean.getUser().getBelongingUser());
		projectEmployeeDetail.setEngineeringProjectDetail(engineeringProjectDetail);
		projectEmployeeDetailList.add(projectEmployeeDetail);
		engineeringProjectCreateDto.getEmployeeList().remove(employee);
	}
	
	/**
	 * 移除所有选中的开机人员
	 */
	public void deleteEmployee(EngineeringProjectDetail engineeringProjectDetail) {
		Tool.sendLog(LOG, userBean, "【工程项目登陆画面_删除开机人员按钮】");
		List<ProjectEmployeeDetail> projectEmployeeDetailList = engineeringProjectDetail.getProjectEmployeeDetailList();
		if (projectEmployeeDetailList != null && !projectEmployeeDetailList.isEmpty()) {
			int size = projectEmployeeDetailList.size() - 1;
			List<ProjectEmployeeDetail> deleteList = new ArrayList<ProjectEmployeeDetail>();
			for (int i = size; i >= 0; i--) {
				ProjectEmployeeDetail projectEmployeeDetail = projectEmployeeDetailList.get(i);
				if (projectEmployeeDetail.getId() == null) {
					projectEmployeeDetailList.remove(i);
				} else {
					deleteList.add(projectEmployeeDetail);
				}
			}
			if (!deleteList.isEmpty()) {
				projectEmployeeDetailService.deleteEntityAll(deleteList);
			}
			Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
		}
	}
	
	/**
	 * 获取工程项目详细列表
	 */
	private void getVehicleDateil() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EngineeringProjectDetail.class);
		detachedCriteria.createAlias(EngineeringProjectDetail.ENGINEERING_PROJECT, EngineeringProjectDetail.ENGINEERING_PROJECT, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias(EngineeringProjectDetail.VEHICLE_INFOL, EngineeringProjectDetail.VEHICLE_INFOL, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.add(Restrictions.eq(EngineeringProjectDetail.ENGINEERING_PROJECT, engineeringProjectCreateDto.getTransferEngineeringProject()));
		List<EngineeringProjectDetail> list = engineeringProjectDetailService.getByDetachedCriteria(detachedCriteria);
		if (!list.isEmpty()) {
			detachedCriteria = DetachedCriteria.forClass(ProjectEmployeeDetail.class);
			detachedCriteria.createAlias(ProjectEmployeeDetail.EMPLOYEE, ProjectEmployeeDetail.EMPLOYEE, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.createAlias(ProjectEmployeeDetail.ENGINEERING_PROJECT_DETAIL, ProjectEmployeeDetail.ENGINEERING_PROJECT_DETAIL, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.add(Restrictions.in(ProjectEmployeeDetail.ENGINEERING_PROJECT_DETAIL, list));
			List<ProjectEmployeeDetail> projectEmployeeDetailList = projectEmployeeDetailService.getByDetachedCriteria(detachedCriteria);
			if (!projectEmployeeDetailList.isEmpty()) {
				List<ProjectEmployeeDetail> detailList;
				for (EngineeringProjectDetail engineeringProjectDetail : list) {
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
		engineeringProjectCreateDto.setEngineeringProjectDetailList(list);
	}
	
	/**
	 * @param employeeService the employeeService to set
	 */
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	/**
	 * @param vehicleInfoDetailService the vehicleInfoDetailService to set
	 */
	public void setVehicleInfoDetailService(
			VehicleInfoDetailService vehicleInfoDetailService) {
		this.vehicleInfoDetailService = vehicleInfoDetailService;
	}

	/**
	 * @param vehicleInfoService the vehicleInfoService to set
	 */
	public void setVehicleInfoService(VehicleInfoService vehicleInfoService) {
		this.vehicleInfoService = vehicleInfoService;
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
	 * get engineeringProjectCreateDto
	 * @return the engineeringProjectCreateDto
	 */
	public EngineeringProjectCreateDto getEngineeringProjectCreateDto() {
		return engineeringProjectCreateDto;
	}

	/**
	 * set engineeringProjectCreateDto
	 * @param engineeringProjectCreateDto the engineeringProjectCreateDto to set
	 */
	public void setEngineeringProjectCreateDto(EngineeringProjectCreateDto engineeringProjectCreateDto) {
		this.engineeringProjectCreateDto = engineeringProjectCreateDto;
	}

	/**
	 * @return the selectedModels
	 */
	public Employee[] getSelectedModels() {
		return selectedModels;
	}

	/**
	 * @param selectedModels the selectedModels to set
	 */
	public void setSelectedModels(Employee[] selectedModels) {
		this.selectedModels = selectedModels;
	}

}
