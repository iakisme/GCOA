package com.qylm.bean.returner.fitting;

import com.qylm.bean.fitting.FittingStorageManageBean;
import com.qylm.bean.returner.Returner;
import com.qylm.dto.fitting.FittingStorageManageDto;
import com.qylm.entity.FittingStorage;

public class FittingStorageManageReturner extends Returner<FittingStorageManageBean, FittingStorageManageDto, FittingStorage> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4436887718869418028L;

	public FittingStorageManageReturner(FittingStorageManageDto fittingStorageManageDto, int currentPage) {
		super(fittingStorageManageDto, currentPage);
	}
	
	@Override
	public String returnOnly(FittingStorageManageBean backBean) {
		backBean.setFittingStorageManageDto(super.dto);
		return backBean.back(currentPage);
	}

}
