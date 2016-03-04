package com.qylm.bean.vehicle;

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
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.qylm.bean.BasePagingBean;
import com.qylm.bean.UserBean;
import com.qylm.bean.returner.vehicle.VehicleInfoManageReturner;
import com.qylm.common.Message;
import com.qylm.common.MothedUtil;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.common.utils.DateUtil;
import com.qylm.common.utils.StringUtil;
import com.qylm.dto.vehicle.VehicleInfoManageDto;
import com.qylm.entity.EngineeringProjectDetail;
import com.qylm.entity.VehicleInfo;
import com.qylm.entity.VehiclePeccancy;
import com.qylm.entity.VehicleServicing;
import com.qylm.service.EngineeringProjectDetailService;
import com.qylm.service.VehicleInfoService;
import com.qylm.service.VehiclePeccancyService;
import com.qylm.service.VehicleServicingService;

/**
 * 车辆信息管理
 * @author qylm
 */
@ManagedBean
@RequestScoped
public class VehicleInfoManageBean extends BasePagingBean {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 9200349285201120937L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(VehicleInfoManageBean.class);
	
	/**
	 * 保存车辆管理画面需要保存的值
	 */
	private VehicleInfoManageDto vehicleInfoManageDto = new VehicleInfoManageDto();

	/**
	 * 车辆列表（检索结果）
	 */
	private List<VehicleInfo> vehicleInfoList;
	
	/**
	 * 删除复选框选择的值
	 */
	private VehicleInfo[] selectedModels;

	/**
	 * 车辆管理bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;

	/**
	 * 车辆管理业务类
	 */
	@ManagedProperty(value="#{vehicleInfoService}")
	private VehicleInfoService vehicleInfoService;
	
	/**
	 * 项目工程详细业务类
	 */
	@ManagedProperty(value="#{engineeringProjectDetailService}")
	private EngineeringProjectDetailService engineeringProjectDetailService;
	
	/**
	 * 车辆维修管理业务类
	 */
	@ManagedProperty(value="#{vehicleServicingService}")
	private VehicleServicingService vehicleServicingService;
	
	/**
	 * 车辆违章事故管理业务类
	 */
	@ManagedProperty(value="#{vehiclePeccancyService}")
	private VehiclePeccancyService vehiclePeccancyService;
	
	/**
	 * 查询出所有车辆列表
	 * 
	 * @return 车辆管理画面
	 */
	public String getAll() {
		Tool.sendLog(LOG, userBean, "按下【功能菜单_车辆管理按钮】");
		fetchData(0, true);
		return Navigation.VEHICLE_INFO_MANAGE;
	}

	/**
	 * 此方法绑定于项目管理画面的新建按钮 
	 * 实现功能：跳转至车辆登陆画面，新建一家车辆
	 * @return 车辆登陆画面
	 */
	public String newVehicleInfo() {
		Tool.sendLog(LOG, userBean, "按下【车辆管理画面_新建按钮】");
		return Tool.getBackBean(VehicleInfoCreateBean.class).newCreate(new VehicleInfoManageReturner(vehicleInfoManageDto, currentPage));
	}

	/**
	 * 此方法绑定于项目管理画面的修改按钮 
	 * 实现功能：根据修改的对象，跳转至车辆登陆画面
	 * @return 车辆登陆画面
	 */
	public String updateVehicleInfo(VehicleInfo transferVehicleInfo) {
		Tool.sendLog(LOG, userBean, "按下【车辆管理画面_修改按钮】");
		return Tool.getBackBean(VehicleInfoCreateBean.class).updateDetail(new VehicleInfoManageReturner(vehicleInfoManageDto, currentPage), transferVehicleInfo);
	}
	
	/**
	 * 此方法绑定于车辆管理画面的检索按钮 
	 * 实现功能：根据检索条件，检索出车辆
	 * @return 画面不跳转
	 */
	public void selectVehicleInfo() {
		Tool.sendLog(LOG, userBean, "按下【车辆管理画面_检索按钮】");
		fetchData(0, true);
	}
	
	/**
	 * 绑定于车辆管理画面的全选删除按钮
	 * @param event
	 */
	public void deleteMul(ActionEvent event) {
		Tool.sendLog(LOG, userBean, "按下【车辆管理画面_批量删除按钮】");
		if (selectedModels != null) {
			List<VehicleInfo> asList = Arrays.asList(selectedModels);
			vehicleInfoList.removeAll(asList);
			vehicleInfoService.deleteEntityAll(asList);
			removeData(1, vehicleInfoList.isEmpty());
			Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
		}
	}

	/**
	 * 此方法绑定于车辆管理画面的删除按钮 
	 * 实现功能：先移除集合内的数据，在移除数据库内的数据
	 * @return 画面不跳转
	 */
	public void deleteVehicleInfo(VehicleInfo transferVehicleInfo) {
		Tool.sendLog(LOG, userBean, "按下【车辆管理画面的_删除按钮】");
		vehicleInfoList.remove(transferVehicleInfo);
		vehicleInfoService.deleteEntity(transferVehicleInfo);
		removeData(1, vehicleInfoList.isEmpty());
		Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
	}

	@Override
	protected void fetchData(int start, boolean needInit) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(VehicleInfo.class);
		detachedCriteria.createAlias(VehicleInfo.EMPLOYEE, VehicleInfo.EMPLOYEE, JoinType.LEFT_OUTER_JOIN);
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		String number = vehicleInfoManageDto.getNumber();
		String employeeName = vehicleInfoManageDto.getEmployeeName();
		if (StringUtil.isNotBlank(number)) {
			detachedCriteria.add(Restrictions.like(VehicleInfo.NUMBER, number, MatchMode.ANYWHERE));
		}
		if (StringUtil.isNotBlank(employeeName)) {
			detachedCriteria.add(Restrictions.like(VehicleInfo.EMPLOYEE_NAME, employeeName, MatchMode.ANYWHERE));
		}
		vehicleInfoList = vehicleInfoService.getByDetachedCriteria(detachedCriteria, start, onePageCount);
		if (needInit) {
			init(vehicleInfoService.getRowCount(detachedCriteria));
		}
		if (!vehicleInfoList.isEmpty()) {
			// 查询出工作情况
			detachedCriteria = DetachedCriteria.forClass(EngineeringProjectDetail.class);
			detachedCriteria.createAlias(EngineeringProjectDetail.ENGINEERING_PROJECT, EngineeringProjectDetail.ENGINEERING_PROJECT, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.createAlias(EngineeringProjectDetail.VEHICLE_INFOL, EngineeringProjectDetail.VEHICLE_INFOL, JoinType.LEFT_OUTER_JOIN);
			// 查询正在工作的时间，（查结束日期是否在当前时间内）
			Date nowyyyymmddhhmmss = DateUtil.getNowyyyymmddhhmmss();
			detachedCriteria.add(Restrictions.ge(EngineeringProjectDetail.END_PUMP_DATE, nowyyyymmddhhmmss));
			List<EngineeringProjectDetail> engineeringProjectDetailList = engineeringProjectDetailService.getByDetachedCriteria(detachedCriteria);
			// 查询出维修情况
			detachedCriteria = DetachedCriteria.forClass(VehicleServicing.class);
			detachedCriteria.createAlias(VehicleServicing.VEHICLE_INFO, VehicleServicing.VEHICLE_INFO, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.add(Restrictions.in(VehicleServicing.VEHICLE_INFO, vehicleInfoList));
			detachedCriteria.add(Restrictions.eq(VehicleServicing.STATE, VehicleServicing.STATE_2));
			List<VehicleServicing> vehicleServicingList = vehicleServicingService.getByDetachedCriteria(detachedCriteria);
			// 查询出违章事故情况
			detachedCriteria = DetachedCriteria.forClass(VehiclePeccancy.class);
			detachedCriteria.createAlias(VehiclePeccancy.VEHICLE_INFO, VehiclePeccancy.VEHICLE_INFO, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.add(Restrictions.in(VehiclePeccancy.VEHICLE_INFO, vehicleInfoList));
			detachedCriteria.add(Restrictions.eq(VehiclePeccancy.STATE, false));
			List<VehiclePeccancy> vehiclePeccancyList = vehiclePeccancyService.getByDetachedCriteria(detachedCriteria);
			for (VehicleInfo vehicleInfo : vehicleInfoList) {
				vehicleInfo.setWorkInfo(MothedUtil.getVehicleInfoState(vehicleInfo, engineeringProjectDetailList, vehicleServicingList, vehiclePeccancyList));
			}
		}
	}

	/**
	 * 需要返回本画面时都调用此共通方法
	 * @param currentPage 当前页数
	 * @return 个人车辆管理画面
	 */
	public String back(int currentPage) {
		reflushCurrentPage(currentPage);
		return Navigation.VEHICLE_INFO_MANAGE;
	}

	/**
	 * @param vehicleServicingService the vehicleServicingService to set
	 */
	public void setVehicleServicingService(
			VehicleServicingService vehicleServicingService) {
		this.vehicleServicingService = vehicleServicingService;
	}

	/**
	 * @param vehiclePeccancyService the vehiclePeccancyService to set
	 */
	public void setVehiclePeccancyService(
			VehiclePeccancyService vehiclePeccancyService) {
		this.vehiclePeccancyService = vehiclePeccancyService;
	}

	/**
	 * @param engineeringProjectDetailService the engineeringProjectDetailService to set
	 */
	public void setEngineeringProjectDetailService(
			EngineeringProjectDetailService engineeringProjectDetailService) {
		this.engineeringProjectDetailService = engineeringProjectDetailService;
	}

	/**
	 * set vehicleInfoService
	 * @param vehicleInfoService the vehicleInfoService to set
	 */
	public void setVehicleInfoService(VehicleInfoService vehicleInfoService) {
		this.vehicleInfoService = vehicleInfoService;
	}

	/**
	 * set userBean
	 * @param userBean the userBean to set
	 */
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	/**
	 * get vehicleInfoManageDto
	 * @return the vehicleInfoManageDto
	 */
	public VehicleInfoManageDto getVehicleInfoManageDto() {
		return vehicleInfoManageDto;
	}

	/**
	 * set vehicleInfoManageDto
	 * @param vehicleInfoManageDto the vehicleInfoManageDto to set
	 */
	public void setVehicleInfoManageDto(VehicleInfoManageDto vehicleInfoManageDto) {
		this.vehicleInfoManageDto = vehicleInfoManageDto;
	}

	/**
	 * get vehicleInfoList
	 * @return the vehicleInfoList
	 */
	public List<VehicleInfo> getVehicleInfoList() {
		return vehicleInfoList;
	}

	/**
	 * set vehicleInfoList
	 * @param vehicleInfoList the vehicleInfoList to set
	 */
	public void setVehicleInfoList(List<VehicleInfo> vehicleInfoList) {
		this.vehicleInfoList = vehicleInfoList;
	}

	/**
	 * get selectedModels
	 * @return the selectedModels
	 */
	public VehicleInfo[] getSelectedModels() {
		return selectedModels;
	}

	/**
	 * set selectedModels
	 * @param selectedModels the selectedModels to set
	 */
	public void setSelectedModels(VehicleInfo[] selectedModels) {
		this.selectedModels = selectedModels;
	}

}
