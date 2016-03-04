package com.qylm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qylm.dao.EngineeringFinancialDao;
import com.qylm.dao.EngineeringProjectDao;
import com.qylm.entity.EngineeringFinancial;
import com.qylm.entity.EngineeringProject;

@Service("engineeringFinancialService")
public class EngineeringFinancialServiceImpl extends GenericServiceImpl<EngineeringFinancial, Integer> implements EngineeringFinancialService {

	@Autowired
	private EngineeringProjectDao engineeringProjectDao;
	
	@Autowired
	protected EngineeringFinancialServiceImpl(EngineeringFinancialDao engineeringFinancialDao) {
		super(engineeringFinancialDao);
	}

	public void saveEngineeringFinancial(
			EngineeringFinancial engineeringFinancial) {
		super.genericDAO.saveEntity(engineeringFinancial);
		EngineeringProject engineeringProject = engineeringFinancial.getEngineeringProject();
		engineeringProject.setPumpPrice(engineeringProject.getCustomer().getPumpPrice());
		engineeringProjectDao.updateEntity(engineeringProject);
	}

	public void updateEngineeringFinancial(
			EngineeringFinancial engineeringFinancial) {
		super.genericDAO.updateEntity(engineeringFinancial);
		EngineeringProject engineeringProject = engineeringFinancial.getEngineeringProject();
		engineeringProject.setPumpPrice(engineeringProject.getCustomer().getPumpPrice());
		engineeringProjectDao.updateEntity(engineeringProject);
	}

}
