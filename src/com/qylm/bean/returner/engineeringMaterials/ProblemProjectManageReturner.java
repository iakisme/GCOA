package com.qylm.bean.returner.engineeringMaterials;

import com.qylm.bean.engineeringMaterials.MaterialsGrantManageProjectBean;
import com.qylm.bean.engineeringMaterials.ProblemProjectManageBean;
import com.qylm.bean.returner.Returner;
import com.qylm.dto.engineeringMaterials.MaterialsGrantManageProjectDto;
import com.qylm.dto.engineeringMaterials.ProblemProjectManageDto;
import com.qylm.entity.MaterialsGrant;

public class ProblemProjectManageReturner extends Returner<ProblemProjectManageBean, ProblemProjectManageDto, MaterialsGrant> {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1417104064051328451L;

	public ProblemProjectManageReturner(ProblemProjectManageDto problemProjectManageDto, int currentPage) {
		super(problemProjectManageDto, currentPage);
	}
	
	@Override
	public String returnOnly(ProblemProjectManageBean backBean) {
		backBean.setProblemProjectManageDto(super.dto);
		return backBean.back(currentPage);
	}

}
