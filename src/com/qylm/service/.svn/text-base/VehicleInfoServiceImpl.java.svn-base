package com.qylm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qylm.dao.VehicleInfoDao;
import com.qylm.dao.VehicleInfoDetailDao;
import com.qylm.entity.VehicleInfo;
import com.qylm.entity.VehicleInfoDetail;

@Service("vehicleInfoService")
public class VehicleInfoServiceImpl extends GenericServiceImpl<VehicleInfo, Integer> implements VehicleInfoService {
	
	@Autowired
	private VehicleInfoDetailDao vehicleInfoDetailDao;

	@Autowired
	protected VehicleInfoServiceImpl(VehicleInfoDao vehicleInfoDao) {
		super(vehicleInfoDao);
	}

	public void updateVehicleInfo(VehicleInfo vehicleInfo,
			List<VehicleInfoDetail> vehicleInfoDetailList) {
		super.genericDAO.updateEntity(vehicleInfo);
		if (vehicleInfoDetailList != null && !vehicleInfoDetailList.isEmpty()) {
			vehicleInfoDetailDao.saveOrUpdateAll(vehicleInfoDetailList);
		}
	}

}
