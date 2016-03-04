package com.qylm.bean.returner.engineering;

import com.qylm.bean.engineering.EngineeringProjectManageBean;
import com.qylm.bean.returner.Returner;
import com.qylm.dto.engineering.EngineeringProjectManageDto;
import com.qylm.entity.EngineeringProject;

public class EngineeringProjectManageReturner extends Returner<EngineeringProjectManageBean, EngineeringProjectManageDto, EngineeringProject> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -9049513942945015554L;

	public EngineeringProjectManageReturner(EngineeringProjectManageDto engineeringProjectManageDto, int currentPage) {
		super(engineeringProjectManageDto, currentPage);
	}
	
	@Override
	public String returnOnly(EngineeringProjectManageBean backBean) {
		backBean.setEngineeringProjectManageDto(super.dto);
		return backBean.back(currentPage);
	}

}
