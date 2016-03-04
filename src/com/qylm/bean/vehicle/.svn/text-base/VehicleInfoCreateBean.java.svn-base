package com.qylm.bean.vehicle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.dao.DataIntegrityViolationException;

import com.qylm.bean.UserBean;
import com.qylm.bean.returner.Returner;
import com.qylm.common.CreateBean;
import com.qylm.common.Message;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.common.utils.DateUtil;
import com.qylm.dto.vehicle.VehicleInfoCreateDto;
import com.qylm.dxo.VehicleInfoCreateDxo;
import com.qylm.entity.VehicleInfo;
import com.qylm.entity.VehicleInfoDetail;
import com.qylm.service.VehicleInfoDetailService;
import com.qylm.service.VehicleInfoService;

/**
 * 车辆登陆画面bean
 * @author 
 */
@ManagedBean
@RequestScoped
public class VehicleInfoCreateBean implements CreateBean<VehicleInfo>, Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5886231499796256531L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(VehicleInfoCreateBean.class);

	/**
	 * 存放车辆登陆画面需要保存的值
	 */
	private VehicleInfoCreateDto vehicleInfoCreateDto = new VehicleInfoCreateDto();
	
	/**
	 * 车辆bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	/**
	 * 车辆业务类
	 */
	@ManagedProperty(value="#{vehicleInfoService}")
	private VehicleInfoService vehicleInfoService;
	
	/**
	 * 车辆详细业务类
	 */
	@ManagedProperty(value="#{vehicleInfoDetailService}")
	private VehicleInfoDetailService vehicleInfoDetailService;
	
	/**
	 * 此方法绑定于车辆登陆画面的新建按钮 
	 * 实现功能：清空注入信息，并新建一个车辆
	 * @return 车辆登陆画面
	 */
	public String newVehicleInfo() {
		Tool.sendLog(LOG, userBean, "【车辆登陆画面_新建按钮】");
		vehicleInfoCreateDto.setNumber(null);
		vehicleInfoCreateDto.setRemark(null);
		vehicleInfoCreateDto.setEmployee(null);
		vehicleInfoCreateDto.setPurchaseDate(null);
		vehicleInfoCreateDto.setCreater(null);
		vehicleInfoCreateDto.setTransferVehicleInfo(null);
		vehicleInfoCreateDto.setVehicleInfoDetailList(null);
		vehicleInfoCreateDto.setBelongingUser(null);
		return Navigation.VEHICLE_INFO_CREATE;
	}
	
	/**
	 * 此方法绑定于车辆登陆画面的返回按钮 
	 * 实现功能：返回共通方法
	 * @return 返回共通方法
	 */
	public String back() {
		Tool.sendLog(LOG, userBean, "【车辆登陆画面_返回按钮】");
		return vehicleInfoCreateDto.getReturner().returnOnly();
	}

	/**
	 * 此方法绑定于车辆登陆画面的保存按钮 
	 * 实现功能：根据transferVehicleInfo对象判断当前操作时保存还是更新
	 * @return 画面不跳转
	 */
	public void saveVehicleInfo() {
		Tool.sendLog(LOG, userBean, "【车辆登陆画面_保存按钮】");
		try {
			VehicleInfo transferVehicleInfo = vehicleInfoCreateDto.getTransferVehicleInfo();
			if (transferVehicleInfo == null) {
				transferVehicleInfo = new VehicleInfo();
				vehicleInfoCreateDto.setCreater(userBean.getUser());
				vehicleInfoCreateDto.setBelongingUser(userBean.getUser().getBelongingUser());
				create(transferVehicleInfo);
				transferVehicleInfo.setCreateDate(DateUtil.getNowyyyymmddhhmmss());
				vehicleInfoService.saveEntity(transferVehicleInfo);
				vehicleInfoCreateDto.setTransferVehicleInfo(transferVehicleInfo);
				Tool.sendErrorMessage(Message.GENERAL_SAVESUCCESS);
			} else {
				create(transferVehicleInfo);
				transferVehicleInfo.setUpdateDate(DateUtil.getNowyyyymmddhhmmss());
				List<VehicleInfoDetail> vehicleInfoDetailList = vehicleInfoCreateDto.getVehicleInfoDetailList();
				if (vehicleInfoDetailList != null && !vehicleInfoDetailList.isEmpty()) {
					for (VehicleInfoDetail vehicleInfoDetail : vehicleInfoDetailList) {
						vehicleInfoDetail.setBelongingUser(userBean.getUser().getBelongingUser());
						vehicleInfoDetail.setCreateDate(DateUtil.getNowyyyymmdd());
					}
				}
				vehicleInfoService.updateVehicleInfo(transferVehicleInfo, vehicleInfoDetailList);
				Tool.sendErrorMessage(Message.GENERAL_UPDATESUCCESS);
			}
		} catch (DataIntegrityViolationException e) {
			Tool.sendErrorMessage("车辆编号：" + vehicleInfoCreateDto.getNumber() + "，已存在，请确认！");
		}
	}
	
	/**
	 * 赋值
	 * @param transferVehicleInfo
	 */
	private void create(VehicleInfo transferVehicleInfo) {
		VehicleInfoCreateDxo.dtoToEntity(vehicleInfoCreateDto, transferVehicleInfo);
	}

	public String newCreate(Returner<?, ?, ?> returner) {
		vehicleInfoCreateDto.setReturner(returner);
		return Navigation.VEHICLE_INFO_CREATE;
	}

	public String updateDetail(Returner<?, ?, ?> returner, VehicleInfo vehicleInfo) {
		vehicleInfoCreateDto.setReturner(returner);
		VehicleInfoCreateDxo.entityToDto(vehicleInfo, vehicleInfoCreateDto);
		vehicleInfoCreateDto.setTransferVehicleInfo(vehicleInfo);
		getVehicleDateil();
		return Navigation.VEHICLE_INFO_CREATE;
	}
	
	/**
	 * 增加一行车辆负责人信息
	 */
	public void addVehicleDateil() {
		List<VehicleInfoDetail> vehicleInfoDetailList = vehicleInfoCreateDto.getVehicleInfoDetailList();
		if (vehicleInfoDetailList == null) {
			vehicleInfoDetailList = new ArrayList<VehicleInfoDetail>();
			vehicleInfoCreateDto.setVehicleInfoDetailList(vehicleInfoDetailList);
		}
		VehicleInfoDetail vehicleInfoDetail = new VehicleInfoDetail();
		vehicleInfoDetail.setVehicleInfo(vehicleInfoCreateDto.getTransferVehicleInfo());
		vehicleInfoDetail.setCreater(userBean.getUser());
		vehicleInfoDetailList.add(vehicleInfoDetail);
	}
	
	/**
	 * 获取车辆详细列表
	 */
	private void getVehicleDateil() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(VehicleInfoDetail.class);
		detachedCriteria.createAlias(VehicleInfoDetail.VEHICLE_INFO, VehicleInfoDetail.VEHICLE_INFO, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias(VehicleInfoDetail.EMPLOYEE, VehicleInfoDetail.EMPLOYEE, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.add(Restrictions.eq(VehicleInfoDetail.VEHICLE_INFO, vehicleInfoCreateDto.getTransferVehicleInfo()));
		vehicleInfoCreateDto.setVehicleInfoDetailList(vehicleInfoDetailService.getByDetachedCriteria(detachedCriteria));
	}

	/**
	 * @param vehicleInfoDetailService the vehicleInfoDetailService to set
	 */
	public void setVehicleInfoDetailService(
			VehicleInfoDetailService vehicleInfoDetailService) {
		this.vehicleInfoDetailService = vehicleInfoDetailService;
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
	 * get vehicleInfoCreateDto
	 * @return the vehicleInfoCreateDto
	 */
	public VehicleInfoCreateDto getVehicleInfoCreateDto() {
		return vehicleInfoCreateDto;
	}

	/**
	 * set vehicleInfoCreateDto
	 * @param vehicleInfoCreateDto the vehicleInfoCreateDto to set
	 */
	public void setVehicleInfoCreateDto(VehicleInfoCreateDto vehicleInfoCreateDto) {
		this.vehicleInfoCreateDto = vehicleInfoCreateDto;
	}

}
