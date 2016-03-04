package com.qylm.bean.returner.fitting;

import com.qylm.bean.fitting.FittingInfoCreateBean;
import com.qylm.bean.returner.Returner;
import com.qylm.dto.fitting.FittingInfoCreateDto;
import com.qylm.entity.FittingInfo;

public class FittingInfoCreateReturner extends Returner<FittingInfoCreateBean, FittingInfoCreateDto, FittingInfo> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8614030789212112206L;

	public FittingInfoCreateReturner(FittingInfoCreateDto fuhuGroupPhotoCreateDto) {
		super(fuhuGroupPhotoCreateDto);
	}
	
	@Override
	public String returnOnly(FittingInfoCreateBean backBean) {
		backBean.setFittingInfoCreateDto(super.dto);
		return backBean.returner();
	}

}
