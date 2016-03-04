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
import com.qylm.bean.returner.vehicle.VehiclePeccancyManageReturner;
import com.qylm.common.Message;
import com.qylm.common.MothedUtil;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.common.utils.StringUtil;
import com.qylm.dto.vehicle.VehiclePeccancyManageDto;
import com.qylm.entity.VehiclePeccancy;
import com.qylm.service.VehiclePeccancyService;

/**
 * 车辆违章事故管理
 * @author qylm
 */
@ManagedBean
@RequestScoped
public class VehiclePeccancyManageBean extends BasePagingBean {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4813015523144952737L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(VehiclePeccancyManageBean.class);
	
	/**
	 * 保存车辆违章事故管理画面需要保存的值
	 */
	private VehiclePeccancyManageDto vehiclePeccancyManageDto = new VehiclePeccancyManageDto();

	/**
	 * 车辆违章事故列表（检索结果）
	 */
	private List<VehiclePeccancy> vehiclePeccancyList;
	
	/**
	 * 删除复选框选择的值
	 */
	private VehiclePeccancy[] selectedModels;

	/**
	 * 车辆违章事故管理bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;

	/**
	 * 车辆违章事故管理业务类
	 */
	@ManagedProperty(value="#{vehiclePeccancyService}")
	private VehiclePeccancyService vehiclePeccancyService;
	
	/**
	 * 查询出所有车辆违章事故列表
	 * 
	 * @return 车辆违章事故管理画面
	 */
	public String getAll() {
		Tool.sendLog(LOG, userBean, "按下【功能菜单_车辆违章事故管理按钮】");
		fetchData(0, true);
		return Navigation.VEHICLE_PECCANCY_MANAGE;
	}

	/**
	 * 此方法绑定于项目管理画面的新建按钮 
	 * 实现功能：跳转至车辆违章事故登陆画面，新建一家车辆违章事故
	 * @return 车辆违章事故登陆画面
	 */
	public String newVehiclePeccancy() {
		Tool.sendLog(LOG, userBean, "按下【车辆违章事故管理画面_新建按钮】");
		return Tool.getBackBean(VehiclePeccancyCreateBean.class).newCreate(new VehiclePeccancyManageReturner(vehiclePeccancyManageDto, currentPage));
	}

	/**
	 * 此方法绑定于项目管理画面的修改按钮 
	 * 实现功能：根据修改的对象，跳转至车辆违章事故登陆画面
	 * @return 车辆违章事故登陆画面
	 */
	public String updateVehiclePeccancy(VehiclePeccancy transferVehiclePeccancy) {
		Tool.sendLog(LOG, userBean, "按下【车辆违章事故管理画面_修改按钮】");
		return Tool.getBackBean(VehiclePeccancyCreateBean.class).updateDetail(new VehiclePeccancyManageReturner(vehiclePeccancyManageDto, currentPage), transferVehiclePeccancy);
	}
	
	/**
	 * 此方法绑定于车辆违章事故管理画面的检索按钮 
	 * 实现功能：根据检索条件，检索出车辆违章事故
	 * @return 画面不跳转
	 */
	public void selectVehiclePeccancy() {
		Tool.sendLog(LOG, userBean, "按下【车辆违章事故管理画面_检索按钮】");
		fetchData(0, true);
	}
	
	/**
	 * 绑定于车辆违章事故管理画面的全选删除按钮
	 * @param event
	 */
	public void deleteMul(ActionEvent event) {
		Tool.sendLog(LOG, userBean, "按下【车辆违章事故管理画面_批量删除按钮】");
		if (selectedModels != null) {
			List<VehiclePeccancy> asList = Arrays.asList(selectedModels);
			vehiclePeccancyList.removeAll(asList);
			vehiclePeccancyService.deleteEntityAll(asList);
			removeData(1, vehiclePeccancyList.isEmpty());
			Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
		}
	}

	/**
	 * 此方法绑定于车辆违章事故管理画面的删除按钮 
	 * 实现功能：先移除集合内的数据，在移除数据库内的数据
	 * @return 画面不跳转
	 */
	public void deleteVehiclePeccancy(VehiclePeccancy transferVehiclePeccancy) {
		Tool.sendLog(LOG, userBean, "按下【车辆违章事故管理画面的_删除按钮】");
		vehiclePeccancyList.remove(transferVehiclePeccancy);
		vehiclePeccancyService.deleteEntity(transferVehiclePeccancy);
		removeData(1, vehiclePeccancyList.isEmpty());
		Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
	}
	
	/**
	 * 此方法绑定于车辆违章事故管理画面的维修完毕按钮 
	 * 实现功能：先移除集合内的数据，在移除数据库内的数据
	 * @return 画面不跳转
	 */
	public void queryPeccancy(VehiclePeccancy transferVehiclePeccancy) {
		Tool.sendLog(LOG, userBean, "按下【车辆违章事故管理画面的_维修完毕按钮】");
		transferVehiclePeccancy.setState(true);
		vehiclePeccancyService.updateEntity(transferVehiclePeccancy);
		Tool.sendErrorMessage(Message.GENERAL_UPDATESUCCESS);
	}

	@Override
	protected void fetchData(int start, boolean needInit) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(VehiclePeccancy.class);
		detachedCriteria.createAlias(VehiclePeccancy.EMPLOYEE, VehiclePeccancy.EMPLOYEE, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias(VehiclePeccancy.VEHICLE_INFO, VehiclePeccancy.VEHICLE_INFO, JoinType.LEFT_OUTER_JOIN);
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		String number = vehiclePeccancyManageDto.getNumber();
		String cause = vehiclePeccancyManageDto.getCause();
		Date beginPeccancyDate = vehiclePeccancyManageDto.getBeginPeccancyDate();
		Date endPeccancyDate = vehiclePeccancyManageDto.getEndPeccancyDate();
		if (StringUtil.isNotBlank(number)) {
			detachedCriteria.add(Restrictions.like(VehiclePeccancy.VEHICLE_INFO_NUMBER, number, MatchMode.ANYWHERE));
		}
		if (StringUtil.isNotBlank(cause)) {
			detachedCriteria.add(Restrictions.like(VehiclePeccancy.CAUSE, cause, MatchMode.ANYWHERE));
		}
		if (beginPeccancyDate != null) {
			detachedCriteria.add(Restrictions.ge(VehiclePeccancy.PECCANCY_DATE, beginPeccancyDate));
		}
		if (endPeccancyDate != null) {
			detachedCriteria.add(Restrictions.le(VehiclePeccancy.PECCANCY_DATE, endPeccancyDate));
		}
		vehiclePeccancyList = vehiclePeccancyService.getByDetachedCriteria(detachedCriteria, start, onePageCount);
		if (needInit) {
			init(vehiclePeccancyService.getRowCount(detachedCriteria));
		}
	}

	/**
	 * 需要返回本画面时都调用此共通方法
	 * @param currentPage 当前页数
	 * @return 个人车辆违章事故管理画面
	 */
	public String back(int currentPage) {
		reflushCurrentPage(currentPage);
		return Navigation.VEHICLE_PECCANCY_MANAGE;
	}

	/**
	 * set vehiclePeccancyService
	 * @param vehiclePeccancyService the vehiclePeccancyService to set
	 */
	public void setVehiclePeccancyService(VehiclePeccancyService vehiclePeccancyService) {
		this.vehiclePeccancyService = vehiclePeccancyService;
	}

	/**
	 * set userBean
	 * @param userBean the userBean to set
	 */
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	/**
	 * get vehiclePeccancyManageDto
	 * @return the vehiclePeccancyManageDto
	 */
	public VehiclePeccancyManageDto getVehiclePeccancyManageDto() {
		return vehiclePeccancyManageDto;
	}

	/**
	 * set vehiclePeccancyManageDto
	 * @param vehiclePeccancyManageDto the vehiclePeccancyManageDto to set
	 */
	public void setVehiclePeccancyManageDto(VehiclePeccancyManageDto vehiclePeccancyManageDto) {
		this.vehiclePeccancyManageDto = vehiclePeccancyManageDto;
	}

	/**
	 * get vehiclePeccancyList
	 * @return the vehiclePeccancyList
	 */
	public List<VehiclePeccancy> getVehiclePeccancyList() {
		return vehiclePeccancyList;
	}

	/**
	 * set vehiclePeccancyList
	 * @param vehiclePeccancyList the vehiclePeccancyList to set
	 */
	public void setVehiclePeccancyList(List<VehiclePeccancy> vehiclePeccancyList) {
		this.vehiclePeccancyList = vehiclePeccancyList;
	}

	/**
	 * get selectedModels
	 * @return the selectedModels
	 */
	public VehiclePeccancy[] getSelectedModels() {
		return selectedModels;
	}

	/**
	 * set selectedModels
	 * @param selectedModels the selectedModels to set
	 */
	public void setSelectedModels(VehiclePeccancy[] selectedModels) {
		this.selectedModels = selectedModels;
	}

}
