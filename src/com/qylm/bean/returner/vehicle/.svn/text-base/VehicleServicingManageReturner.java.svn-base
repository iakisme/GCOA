package com.qylm.bean.returner.vehicle;

import com.qylm.bean.returner.Returner;
import com.qylm.bean.vehicle.VehicleServicingManageBean;
import com.qylm.dto.vehicle.VehicleServicingManageDto;
import com.qylm.entity.VehicleServicing;

public class VehicleServicingManageReturner extends Returner<VehicleServicingManageBean, VehicleServicingManageDto, VehicleServicing> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7451715265797971894L;

	public VehicleServicingManageReturner(VehicleServicingManageDto vehicleServicingManageDto, int currentPage) {
		super(vehicleServicingManageDto, currentPage);
	}
	
	@Override
	public String returnOnly(VehicleServicingManageBean backBean) {
		backBean.setVehicleServicingManageDto(super.dto);
		return backBean.back(currentPage);
	}

}
