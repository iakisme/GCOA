package com.qylm.service;

import java.util.List;

import com.qylm.entity.FittingLibrary;
import com.qylm.entity.FittingLibraryDetail;
import com.qylm.exception.FittingLibraryException;

public interface FittingLibraryService extends GenericService<FittingLibrary, Integer> {

	/**
	 * 保存时保存出库详细
	 * @param fittingLibrary
	 * @param fittingLibraryDetailList
	 */
	public void saveFittingLibrary(FittingLibrary fittingLibrary, List<FittingLibraryDetail> fittingLibraryDetailList);
	
	/**
	 * 更新时，更新或保存出库详细
	 * @param fittingLibrary
	 * @param fittingLibraryDetailList
	 */
	public void updateFittingLibrary(FittingLibrary fittingLibrary, List<FittingLibraryDetail> fittingLibraryDetailList) throws FittingLibraryException;
}
