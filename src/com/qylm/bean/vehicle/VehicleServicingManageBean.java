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
import com.qylm.bean.returner.vehicle.VehicleServicingManageReturner;
import com.qylm.common.Message;
import com.qylm.common.MothedUtil;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.common.utils.StringUtil;
import com.qylm.dto.vehicle.VehicleServicingManageDto;
import com.qylm.entity.VehicleServicing;
import com.qylm.service.VehicleServicingService;

/**
 * 车辆维修管理
 * @author qylm
 */
@ManagedBean
@RequestScoped
public class VehicleServicingManageBean extends BasePagingBean {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3544556950067784998L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(VehicleServicingManageBean.class);
	
	/**
	 * 保存车辆维修管理画面需要保存的值
	 */
	private VehicleServicingManageDto vehicleServicingManageDto = new VehicleServicingManageDto();

	/**
	 * 车辆维修列表（检索结果）
	 */
	private List<VehicleServicing> vehicleServicingList;
	
	/**
	 * 删除复选框选择的值
	 */
	private VehicleServicing[] selectedModels;

	/**
	 * 车辆维修管理bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;

	/**
	 * 车辆维修管理业务类
	 */
	@ManagedProperty(value="#{vehicleServicingService}")
	private VehicleServicingService vehicleServicingService;
	
	/**
	 * 查询出所有车辆维修列表
	 * 
	 * @return 车辆维修管理画面
	 */
	public String getAll() {
		Tool.sendLog(LOG, userBean, "按下【功能菜单_车辆维修管理按钮】");
		fetchData(0, true);
		return Navigation.VEHICLE_SERVICING_MANAGE;
	}

	/**
	 * 此方法绑定于项目管理画面的新建按钮 
	 * 实现功能：跳转至车辆维修登陆画面，新建一家车辆维修
	 * @return 车辆维修登陆画面
	 */
	public String newVehicleServicing() {
		Tool.sendLog(LOG, userBean, "按下【车辆维修管理画面_新建按钮】");
		return Tool.getBackBean(VehicleServicingCreateBean.class).newCreate(new VehicleServicingManageReturner(vehicleServicingManageDto, currentPage));
	}

	/**
	 * 此方法绑定于项目管理画面的修改按钮 
	 * 实现功能：根据修改的对象，跳转至车辆维修登陆画面
	 * @return 车辆维修登陆画面
	 */
	public String updateVehicleServicing(VehicleServicing transferVehicleServicing) {
		Tool.sendLog(LOG, userBean, "按下【车辆维修管理画面_修改按钮】");
		return Tool.getBackBean(VehicleServicingCreateBean.class).updateDetail(new VehicleServicingManageReturner(vehicleServicingManageDto, currentPage), transferVehicleServicing);
	}
	
	/**
	 * 此方法绑定于车辆维修管理画面的检索按钮 
	 * 实现功能：根据检索条件，检索出车辆维修
	 * @return 画面不跳转
	 */
	public void selectVehicleServicing() {
		Tool.sendLog(LOG, userBean, "按下【车辆维修管理画面_检索按钮】");
		fetchData(0, true);
	}
	
	/**
	 * 绑定于车辆维修管理画面的全选删除按钮
	 * @param event
	 */
	public void deleteMul(ActionEvent event) {
		Tool.sendLog(LOG, userBean, "按下【车辆维修管理画面_批量删除按钮】");
		if (selectedModels != null) {
			List<VehicleServicing> asList = Arrays.asList(selectedModels);
			vehicleServicingList.removeAll(asList);
			vehicleServicingService.deleteEntityAll(asList);
			removeData(1, vehicleServicingList.isEmpty());
			Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
		}
	}

	/**
	 * 此方法绑定于车辆维修管理画面的删除按钮 
	 * 实现功能：先移除集合内的数据，在移除数据库内的数据
	 * @return 画面不跳转
	 */
	public void deleteVehicleServicing(VehicleServicing transferVehicleServicing) {
		Tool.sendLog(LOG, userBean, "按下【车辆维修管理画面的_删除按钮】");
		vehicleServicingList.remove(transferVehicleServicing);
		vehicleServicingService.deleteEntity(transferVehicleServicing);
		removeData(1, vehicleServicingList.isEmpty());
		Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
	}
	
	/**
	 * 此方法绑定于车辆维修管理画面的维修完毕按钮 
	 * 实现功能：先移除集合内的数据，在移除数据库内的数据
	 * @return 画面不跳转
	 */
	public void queryServicing(VehicleServicing transferVehicleServicing) {
		Tool.sendLog(LOG, userBean, "按下【车辆维修管理画面的_维修完毕按钮】");
		transferVehicleServicing.setState(VehicleServicing.STATE_3);
		vehicleServicingService.updateEntity(transferVehicleServicing);
		Tool.sendErrorMessage(Message.GENERAL_UPDATESUCCESS);
	}

	@Override
	protected void fetchData(int start, boolean needInit) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(VehicleServicing.class);
		detachedCriteria.createAlias(VehicleServicing.EMPLOYEE, VehicleServicing.EMPLOYEE, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias(VehicleServicing.VEHICLE_INFO, VehicleServicing.VEHICLE_INFO, JoinType.LEFT_OUTER_JOIN);
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		String number = vehicleServicingManageDto.getNumber();
		String breakdown = vehicleServicingManageDto.getBreakdown();
		Date beginGervicingDate = vehicleServicingManageDto.getBeginGervicingDate();
		Date endGervicingDate = vehicleServicingManageDto.getEndGervicingDate();
		if (StringUtil.isNotBlank(number)) {
			detachedCriteria.add(Restrictions.like(VehicleServicing.VEHICLE_INFO_NUMBER, number, MatchMode.ANYWHERE));
		}
		if (StringUtil.isNotBlank(breakdown)) {
			detachedCriteria.add(Restrictions.like(VehicleServicing.BREAKDOWN, breakdown, MatchMode.ANYWHERE));
		}
		if (beginGervicingDate != null) {
			detachedCriteria.add(Restrictions.ge(VehicleServicing.SERVICING_DATE, beginGervicingDate));
		}
		if (endGervicingDate != null) {
			detachedCriteria.add(Restrictions.le(VehicleServicing.SERVICING_DATE, endGervicingDate));
		}
		vehicleServicingList = vehicleServicingService.getByDetachedCriteria(detachedCriteria, start, onePageCount);
		if (needInit) {
			init(vehicleServicingService.getRowCount(detachedCriteria));
		}
	}

	/**
	 * 需要返回本画面时都调用此共通方法
	 * @param currentPage 当前页数
	 * @return 个人车辆维修管理画面
	 */
	public String back(int currentPage) {
		reflushCurrentPage(currentPage);
		return Navigation.VEHICLE_SERVICING_MANAGE;
	}

	/**
	 * set vehicleServicingService
	 * @param vehicleServicingService the vehicleServicingService to set
	 */
	public void setVehicleServicingService(VehicleServicingService vehicleServicingService) {
		this.vehicleServicingService = vehicleServicingService;
	}

	/**
	 * set userBean
	 * @param userBean the userBean to set
	 */
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	/**
	 * get vehicleServicingManageDto
	 * @return the vehicleServicingManageDto
	 */
	public VehicleServicingManageDto getVehicleServicingManageDto() {
		return vehicleServicingManageDto;
	}

	/**
	 * set vehicleServicingManageDto
	 * @param vehicleServicingManageDto the vehicleServicingManageDto to set
	 */
	public void setVehicleServicingManageDto(VehicleServicingManageDto vehicleServicingManageDto) {
		this.vehicleServicingManageDto = vehicleServicingManageDto;
	}

	/**
	 * get vehicleServicingList
	 * @return the vehicleServicingList
	 */
	public List<VehicleServicing> getVehicleServicingList() {
		return vehicleServicingList;
	}

	/**
	 * set vehicleServicingList
	 * @param vehicleServicingList the vehicleServicingList to set
	 */
	public void setVehicleServicingList(List<VehicleServicing> vehicleServicingList) {
		this.vehicleServicingList = vehicleServicingList;
	}

	/**
	 * get selectedModels
	 * @return the selectedModels
	 */
	public VehicleServicing[] getSelectedModels() {
		return selectedModels;
	}

	/**
	 * set selectedModels
	 * @param selectedModels the selectedModels to set
	 */
	public void setSelectedModels(VehicleServicing[] selectedModels) {
		this.selectedModels = selectedModels;
	}

}
