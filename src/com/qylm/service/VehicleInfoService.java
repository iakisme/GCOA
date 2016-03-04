package com.qylm.service;

import java.util.List;

import com.qylm.entity.VehicleInfo;
import com.qylm.entity.VehicleInfoDetail;

public interface VehicleInfoService extends GenericService<VehicleInfo, Integer> {

	/**
	 * 更新时，同时保存或者更新车辆详细
	 * @param vehicleInfo
	 * @param vehicleInfoDetailList
	 */
	public void updateVehicleInfo(VehicleInfo vehicleInfo, List<VehicleInfoDetail> vehicleInfoDetailList);
}
