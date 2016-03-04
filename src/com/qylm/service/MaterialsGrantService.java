package com.qylm.service;

import java.util.List;

import com.qylm.entity.EngineeringProjectDetail;
import com.qylm.entity.MaterialsGrant;

public interface MaterialsGrantService extends GenericService<MaterialsGrant, Integer> {
	public void updateEngineeringProjectSendSms(EngineeringProjectDetail engineeringProjectDetail);
}
