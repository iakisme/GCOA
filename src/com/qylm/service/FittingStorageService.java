package com.qylm.service;

import java.util.List;

import com.qylm.entity.FittingStorage;
import com.qylm.entity.FittingStorageDetail;

public interface FittingStorageService extends GenericService<FittingStorage, Integer> {

	/**
	 * 保存时保存入库详细
	 * @param fittingStorage
	 * @param fittingStorageDetailList
	 */
	public void saveFittingStorage(FittingStorage fittingStorage, List<FittingStorageDetail> fittingStorageDetailList);
	
	/**
	 * 更新时，更新或保存入库详细
	 * @param fittingStorage
	 * @param fittingStorageDetailList
	 */
	public void updateFittingStorage(FittingStorage fittingStorage, List<FittingStorageDetail> fittingStorageDetailList);
}
