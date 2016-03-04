package com.qylm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qylm.dao.ProjectEmployeeDetailDao;
import com.qylm.entity.ProjectEmployeeDetail;

@Service("projectEmployeeDetailService")
public class ProjectEmployeeDetailServiceImpl extends GenericServiceImpl<ProjectEmployeeDetail, Integer> implements ProjectEmployeeDetailService {

	@Autowired
	protected ProjectEmployeeDetailServiceImpl(ProjectEmployeeDetailDao projectEmployeeDetailDao) {
		super(projectEmployeeDetailDao);
	}

}
