package com.qylm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qylm.dao.EngineeringProjectDao;
import com.qylm.entity.EngineeringProject;
import com.qylm.entity.EngineeringProjectDetail;
import com.qylm.entity.User;
import com.qylm.logic.engineering.EngineeringProjectLogic;

@Service("engineeringProjectService")
public class EngineeringProjectServiceImpl extends GenericServiceImpl<EngineeringProject, Integer> implements EngineeringProjectService {
	
	@Autowired
	private EngineeringProjectLogic engineeringProjectLogic;
	
	@Autowired
	protected EngineeringProjectServiceImpl(EngineeringProjectDao engineeringProjectDao) {
		super(engineeringProjectDao);
	}
	
	public void saveEngineeringProject(EngineeringProject engineeringProject,
			List<EngineeringProjectDetail> engineeringProjectDetailList) {
		engineeringProjectLogic.saveEngineeringProject(engineeringProject, engineeringProjectDetailList);
	}

	public void updateEngineeringProject(EngineeringProject engineeringProject,
			List<EngineeringProjectDetail> engineeringProjectDetailList) {
		engineeringProjectLogic.updateEngineeringProject(engineeringProject, engineeringProjectDetailList);
	}

	public void updateEngineeringProjectsAndSaveFinancial(EngineeringProject engineeringProject, User user) {
		engineeringProjectLogic.updateEngineeringProjectsAndSaveFinancial(engineeringProject, user);
	}

	public void updateEngineeringProjectSendSms(EngineeringProject engineeringProject, List<EngineeringProjectDetail> engineeringProjectDetailList) {
		engineeringProjectLogic.updateEngineeringProjectSendSms(engineeringProject, engineeringProjectDetailList);
	}

}
