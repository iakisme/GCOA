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
import com.qylm.dto.vehicle.VehicleServicingCreateDto;
import com.qylm.dxo.VehicleServicingCreateDxo;
import com.qylm.entity.VehicleServicing;
import com.qylm.service.VehicleServicingService;

/**
 * 车辆维修登陆画面bean
 * @author 
 */
@ManagedBean
@RequestScoped
public class VehicleServicingCreateBean implements CreateBean<VehicleServicing>, Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5886231499796256531L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(VehicleServicingCreateBean.class);

	/**
	 * 存放车辆维修登陆画面需要保存的值
	 */
	private VehicleServicingCreateDto vehicleServicingCreateDto = new VehicleServicingCreateDto();
	
	/**
	 * 车辆维修bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	/**
	 * 车辆维修业务类
	 */
	@ManagedProperty(value="#{vehicleServicingService}")
	private VehicleServicingService vehicleServicingService;
	
	/**
	 * 此方法绑定于车辆维修登陆画面的新建按钮 
	 * 实现功能：清空注入信息，并新建一个车辆维修
	 * @return 车辆维修登陆画面
	 */
	public String newVehicleServicing() {
		Tool.sendLog(LOG, userBean, "【车辆维修登陆画面_新建按钮】");
		vehicleServicingCreateDto.setVehicleInfo(null);
		vehicleServicingCreateDto.setEmployee(null);
		vehicleServicingCreateDto.setBreakdown(null);
		vehicleServicingCreateDto.setCost(null);
		vehicleServicingCreateDto.setRemark(null);
		vehicleServicingCreateDto.setState(null);
		vehicleServicingCreateDto.setServicingDate(DateUtil.getNowyyyymmdd());
		vehicleServicingCreateDto.setCreater(null);
		vehicleServicingCreateDto.setBelongingUser(null);
		vehicleServicingCreateDto.setTransferVehicleServicing(null);
		return Navigation.VEHICLE_SERVICING_CREATE;
	}
	
	/**
	 * 此方法绑定于车辆维修登陆画面的返回按钮 
	 * 实现功能：返回共通方法
	 * @return 返回共通方法
	 */
	public String back() {
		Tool.sendLog(LOG, userBean, "【车辆维修登陆画面_返回按钮】");
		return vehicleServicingCreateDto.getReturner().returnOnly();
	}

	/**
	 * 此方法绑定于车辆维修登陆画面的保存按钮 
	 * 实现功能：根据transferVehicleServicing对象判断当前操作时保存还是更新
	 * @return 画面不跳转
	 */
	public void saveVehicleServicing() {
		Tool.sendLog(LOG, userBean, "【车辆维修登陆画面_保存按钮】");
		VehicleServicing transferVehicleServicing = vehicleServicingCreateDto.getTransferVehicleServicing();
		if (transferVehicleServicing == null) {
			transferVehicleServicing = new VehicleServicing();
			vehicleServicingCreateDto.setState(VehicleServicing.STATE_1);
			vehicleServicingCreateDto.setCreater(userBean.getUser());
			vehicleServicingCreateDto.setBelongingUser(userBean.getUser().getBelongingUser());
			create(transferVehicleServicing);
			transferVehicleServicing.setCreateDate(DateUtil.getNowyyyymmddhhmmss());
			vehicleServicingService.saveEntity(transferVehicleServicing);
			vehicleServicingCreateDto.setTransferVehicleServicing(transferVehicleServicing);
			Tool.sendErrorMessage(Message.GENERAL_SAVESUCCESS);
		} else {
			create(transferVehicleServicing);
			transferVehicleServicing.setUpdateDate(DateUtil.getNowyyyymmddhhmmss());
			vehicleServicingService.updateEntity(transferVehicleServicing);
			Tool.sendErrorMessage(Message.GENERAL_UPDATESUCCESS);
		}
	}
	
	/**
	 * 维修记录确认
	 */
	public void queryVehicleServicing() {
		Tool.sendLog(LOG, userBean, "【车辆维修登陆画面_确认按钮】");
		VehicleServicing transferVehicleServicing = vehicleServicingCreateDto.getTransferVehicleServicing();
		transferVehicleServicing.setState(VehicleServicing.STATE_2);
		vehicleServicingService.updateEntity(transferVehicleServicing);
		Tool.sendErrorMessage(Message.GENERAL_UPDATESUCCESS);
	}
	
	/**
	 * 赋值
	 * @param transferVehicleServicing
	 */
	private void create(VehicleServicing transferVehicleServicing) {
		VehicleServicingCreateDxo.dtoToEntity(vehicleServicingCreateDto, transferVehicleServicing);
	}

	public String newCreate(Returner<?, ?, ?> returner) {
		vehicleServicingCreateDto.setReturner(returner);
		vehicleServicingCreateDto.setServicingDate(DateUtil.getNowyyyymmdd());
		return Navigation.VEHICLE_SERVICING_CREATE;
	}

	public String updateDetail(Returner<?, ?, ?> returner, VehicleServicing vehicleServicing) {
		vehicleServicingCreateDto.setReturner(returner);
		VehicleServicingCreateDxo.entityToDto(vehicleServicing, vehicleServicingCreateDto);
		vehicleServicingCreateDto.setTransferVehicleServicing(vehicleServicing);
		return Navigation.VEHICLE_SERVICING_CREATE;
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
	 * get vehicleServicingCreateDto
	 * @return the vehicleServicingCreateDto
	 */
	public VehicleServicingCreateDto getVehicleServicingCreateDto() {
		return vehicleServicingCreateDto;
	}

	/**
	 * set vehicleServicingCreateDto
	 * @param vehicleServicingCreateDto the vehicleServicingCreateDto to set
	 */
	public void setVehicleServicingCreateDto(VehicleServicingCreateDto vehicleServicingCreateDto) {
		this.vehicleServicingCreateDto = vehicleServicingCreateDto;
	}

}
