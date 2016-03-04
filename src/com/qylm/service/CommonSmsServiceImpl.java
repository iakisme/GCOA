package com.qylm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qylm.dao.CommonSmsDao;
import com.qylm.entity.CommonSms;

@Service("commonSmsService")
public class CommonSmsServiceImpl extends GenericServiceImpl<CommonSms, Integer> implements CommonSmsService {

	@Autowired
	protected CommonSmsServiceImpl(CommonSmsDao commonSmsDao) {
		super(commonSmsDao);
	}

}
