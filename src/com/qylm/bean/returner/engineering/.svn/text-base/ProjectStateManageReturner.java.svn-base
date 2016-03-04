package com.qylm.bean.returner.engineering;

import com.qylm.bean.engineering.ProjectStateManageBean;
import com.qylm.bean.returner.Returner;
import com.qylm.dto.engineering.ProjectStateManageDto;
import com.qylm.entity.EngineeringProject;

public class ProjectStateManageReturner extends Returner<ProjectStateManageBean, ProjectStateManageDto, EngineeringProject> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6220915336896013211L;

	public ProjectStateManageReturner(ProjectStateManageDto projectStateManageDto, int currentPage) {
		super(projectStateManageDto, currentPage);
	}
	
	@Override
	public String returnOnly(ProjectStateManageBean backBean) {
		backBean.setProjectStateManageDto(super.dto);
		return backBean.back(currentPage);
	}

}
