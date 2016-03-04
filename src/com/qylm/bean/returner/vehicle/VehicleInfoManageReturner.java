package com.qylm.bean.returner.vehicle;

import com.qylm.bean.returner.Returner;
import com.qylm.bean.vehicle.VehicleInfoManageBean;
import com.qylm.dto.vehicle.VehicleInfoManageDto;
import com.qylm.entity.VehicleInfo;

public class VehicleInfoManageReturner extends Returner<VehicleInfoManageBean, VehicleInfoManageDto, VehicleInfo> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5826515247228822563L;

	public VehicleInfoManageReturner(VehicleInfoManageDto vehicleInfoManageDto, int currentPage) {
		super(vehicleInfoManageDto, currentPage);
	}
	
	@Override
	public String returnOnly(VehicleInfoManageBean backBean) {
		backBean.setVehicleInfoManageDto(super.dto);
		return backBean.back(currentPage);
	}

}
