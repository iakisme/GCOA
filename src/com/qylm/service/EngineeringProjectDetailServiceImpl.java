package com.qylm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qylm.dao.EngineeringProjectDetailDao;
import com.qylm.entity.EngineeringProjectDetail;
import com.qylm.logic.engineering.EngineeringProjectDetailLogic;

@Service("engineeringProjectDetailService")
public class EngineeringProjectDetailServiceImpl extends GenericServiceImpl<EngineeringProjectDetail, Integer> implements EngineeringProjectDetailService {

	@Autowired
	private EngineeringProjectDetailLogic engineeringProjectDetailLogic;
	
	@Autowired
	protected EngineeringProjectDetailServiceImpl(EngineeringProjectDetailDao engineeringProjectDetailDao) {
		super(engineeringProjectDetailDao);
	}

	public void updateEngineeringProjectDetail(EngineeringProjectDetail engineeringProjectDetail, boolean state) {
		engineeringProjectDetailLogic.updateEngineeringProjectDetail(engineeringProjectDetail, state);
	}

}
