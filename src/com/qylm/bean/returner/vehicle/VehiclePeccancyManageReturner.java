package com.qylm.bean.returner.vehicle;

import com.qylm.bean.returner.Returner;
import com.qylm.bean.vehicle.VehiclePeccancyManageBean;
import com.qylm.dto.vehicle.VehiclePeccancyManageDto;
import com.qylm.entity.VehiclePeccancy;

public class VehiclePeccancyManageReturner extends Returner<VehiclePeccancyManageBean, VehiclePeccancyManageDto, VehiclePeccancy> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7437008856295798429L;

	public VehiclePeccancyManageReturner(VehiclePeccancyManageDto vehiclePeccancyManageDto, int currentPage) {
		super(vehiclePeccancyManageDto, currentPage);
	}
	
	@Override
	public String returnOnly(VehiclePeccancyManageBean backBean) {
		backBean.setVehiclePeccancyManageDto(super.dto);
		return backBean.back(currentPage);
	}

}
