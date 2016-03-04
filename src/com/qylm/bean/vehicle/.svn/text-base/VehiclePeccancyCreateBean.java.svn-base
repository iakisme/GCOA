package com.qylm.bean.vehicle;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qylm.bean.UserBean;
import com.qylm.bean.returner.Returner;
import com.qylm.common.CreateBean;
import com.qylm.common.Message;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.common.utils.DateUtil;
import com.qylm.dto.vehicle.VehiclePeccancyCreateDto;
import com.qylm.dxo.VehiclePeccancyCreateDxo;
import com.qylm.entity.VehiclePeccancy;
import com.qylm.service.VehiclePeccancyService;

/**
 * 车辆违章事故登陆画面bean
 * @author 
 */
@ManagedBean
@RequestScoped
public class VehiclePeccancyCreateBean implements CreateBean<VehiclePeccancy>, Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5886231499796256531L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(VehiclePeccancyCreateBean.class);

	/**
	 * 存放车辆违章事故登陆画面需要保存的值
	 */
	private VehiclePeccancyCreateDto vehiclePeccancyCreateDto = new VehiclePeccancyCreateDto();
	
	/**
	 * 车辆违章事故bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	/**
	 * 车辆违章事故业务类
	 */
	@ManagedProperty(value="#{vehiclePeccancyService}")
	private VehiclePeccancyService vehiclePeccancyService;
	
	/**
	 * 此方法绑定于车辆违章事故登陆画面的新建按钮 
	 * 实现功能：清空注入信息，并新建一个车辆违章事故
	 * @return 车辆违章事故登陆画面
	 */
	public String newVehiclePeccancy() {
		Tool.sendLog(LOG, userBean, "【车辆违章事故登陆画面_新建按钮】");
		vehiclePeccancyCreateDto.setVehicleInfo(null);
		vehiclePeccancyCreateDto.setEmployee(null);
		vehiclePeccancyCreateDto.setCause(null);
		vehiclePeccancyCreateDto.setAddress(null);
		vehiclePeccancyCreateDto.setCost(null);
		vehiclePeccancyCreateDto.setRemark(null);
		vehiclePeccancyCreateDto.setState(false);
		vehiclePeccancyCreateDto.setPeccancyDate(null);
		vehiclePeccancyCreateDto.setScore(null);
		vehiclePeccancyCreateDto.setCreater(null);
		vehiclePeccancyCreateDto.setBelongingUser(null);
		vehiclePeccancyCreateDto.setTransferVehiclePeccancy(null);
		return Navigation.VEHICLE_PECCANCY_CREATE;
	}
	
	/**
	 * 此方法绑定于车辆违章事故登陆画面的返回按钮 
	 * 实现功能：返回共通方法
	 * @return 返回共通方法
	 */
	public String back() {
		Tool.sendLog(LOG, userBean, "【车辆违章事故登陆画面_返回按钮】");
		return vehiclePeccancyCreateDto.getReturner().returnOnly();
	}

	/**
	 * 此方法绑定于车辆违章事故登陆画面的保存按钮 
	 * 实现功能：根据transferVehiclePeccancy对象判断当前操作时保存还是更新
	 * @return 画面不跳转
	 */
	public void saveVehiclePeccancy() {
		Tool.sendLog(LOG, userBean, "【车辆违章事故登陆画面_保存按钮】");
		VehiclePeccancy transferVehiclePeccancy = vehiclePeccancyCreateDto.getTransferVehiclePeccancy();
		if (transferVehiclePeccancy == null) {
			transferVehiclePeccancy = new VehiclePeccancy();
			vehiclePeccancyCreateDto.setState(false);
			vehiclePeccancyCreateDto.setCreater(userBean.getUser());
			vehiclePeccancyCreateDto.setBelongingUser(userBean.getUser().getBelongingUser());
			create(transferVehiclePeccancy);
			transferVehiclePeccancy.setCreateDate(DateUtil.getNowyyyymmddhhmmss());
			vehiclePeccancyService.saveEntity(transferVehiclePeccancy);
			vehiclePeccancyCreateDto.setTransferVehiclePeccancy(transferVehiclePeccancy);
			Tool.sendErrorMessage(Message.GENERAL_SAVESUCCESS);
		} else {
			create(transferVehiclePeccancy);
			transferVehiclePeccancy.setUpdateDate(DateUtil.getNowyyyymmddhhmmss());
			vehiclePeccancyService.updateEntity(transferVehiclePeccancy);
			Tool.sendErrorMessage(Message.GENERAL_UPDATESUCCESS);
		}
	}
	
	/**
	 * 维修记录确认
	 */
	public void queryVehiclePeccancy() {
		Tool.sendLog(LOG, userBean, "【车辆违章事故登陆画面_确认按钮】");
		VehiclePeccancy transferVehiclePeccancy = vehiclePeccancyCreateDto.getTransferVehiclePeccancy();
		transferVehiclePeccancy.setState(true);
		vehiclePeccancyCreateDto.setState(true);
		vehiclePeccancyService.updateEntity(transferVehiclePeccancy);
		Tool.sendErrorMessage(Message.GENERAL_UPDATESUCCESS);
	}
	
	/**
	 * 赋值
	 * @param transferVehiclePeccancy
	 */
	private void create(VehiclePeccancy transferVehiclePeccancy) {
		VehiclePeccancyCreateDxo.dtoToEntity(vehiclePeccancyCreateDto, transferVehiclePeccancy);
	}

	public String newCreate(Returner<?, ?, ?> returner) {
		vehiclePeccancyCreateDto.setReturner(returner);
		return Navigation.VEHICLE_PECCANCY_CREATE;
	}

	public String updateDetail(Returner<?, ?, ?> returner, VehiclePeccancy vehiclePeccancy) {
		vehiclePeccancyCreateDto.setReturner(returner);
		VehiclePeccancyCreateDxo.entityToDto(vehiclePeccancy, vehiclePeccancyCreateDto);
		vehiclePeccancyCreateDto.setTransferVehiclePeccancy(vehiclePeccancy);
		return Navigation.VEHICLE_PECCANCY_CREATE;
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
	 * get vehiclePeccancyCreateDto
	 * @return the vehiclePeccancyCreateDto
	 */
	public VehiclePeccancyCreateDto getVehiclePeccancyCreateDto() {
		return vehiclePeccancyCreateDto;
	}

	/**
	 * set vehiclePeccancyCreateDto
	 * @param vehiclePeccancyCreateDto the vehiclePeccancyCreateDto to set
	 */
	public void setVehiclePeccancyCreateDto(VehiclePeccancyCreateDto vehiclePeccancyCreateDto) {
		this.vehiclePeccancyCreateDto = vehiclePeccancyCreateDto;
	}

}
