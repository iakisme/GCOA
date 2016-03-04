package com.qylm.bean.returner.fitting;

import com.qylm.bean.fitting.FittingLibraryManageBean;
import com.qylm.bean.returner.Returner;
import com.qylm.dto.fitting.FittingLibraryManageDto;
import com.qylm.entity.FittingLibrary;

public class FittingLibraryManageReturner extends Returner<FittingLibraryManageBean, FittingLibraryManageDto, FittingLibrary> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2187639037984448075L;

	public FittingLibraryManageReturner(FittingLibraryManageDto fittingLibraryManageDto, int currentPage) {
		super(fittingLibraryManageDto, currentPage);
	}
	
	@Override
	public String returnOnly(FittingLibraryManageBean backBean) {
		backBean.setFittingLibraryManageDto(super.dto);
		return backBean.back(currentPage);
	}

}
