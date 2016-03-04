package com.qylm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qylm.common.sms.SmsUtil;
import com.qylm.common.utils.DateUtil;
import com.qylm.common.utils.StringUtil;
import com.qylm.constants.Constants;
import com.qylm.constants.SmsConstants;
import com.qylm.dao.MaterialsGrantDao;
import com.qylm.entity.CommonSms;
import com.qylm.entity.EngineeringProjectDetail;
import com.qylm.entity.MaterialsGrant;

@Service("materialsGrantService")
public class MaterialsGrantServiceImpl extends GenericServiceImpl<MaterialsGrant, Integer> implements MaterialsGrantService {

	@Autowired
	protected MaterialsGrantServiceImpl(MaterialsGrantDao materialsGrantDao) {
		super(materialsGrantDao);
	}

	public void updateEngineeringProjectSendSms(EngineeringProjectDetail engineeringProjectDetail) {
		CommonSms commonSms = new CommonSms();
		commonSms.setCreater(engineeringProjectDetail.getCreater());
		commonSms.setBelongingUser(engineeringProjectDetail.getBelongingUser());
		commonSms.setCreateDate(DateUtil.getNowyyyymmddhhmmss());
		commonSms.setSender(SmsConstants.SENDER);
		commonSms.setRecver(engineeringProjectDetail.getEmployee().getMobile());
		StringBuilder sb = new StringBuilder(128);
		sb.append("工作时间");
		sb.append(DateUtil.formatDate(engineeringProjectDetail.getStartPumpDate(), Constants.YYYY_MM_DD_HH_MM_SS));
		sb.append("至");
		if (engineeringProjectDetail.getEndPumpDate() != null) {
			sb.append(DateUtil.formatDate(engineeringProjectDetail.getEndPumpDate(), Constants.YYYY_MM_DD_HH_MM_SS));
		} else {
			sb.append("工地工作结束");
		}
		sb.append("，工作地址：" + engineeringProjectDetail.getEngineeringProject().getWorkAddress());
		sb.append("；需要您完成的方量：" + engineeringProjectDetail.getWorkVolume());
		if (StringUtil.isNotBlank(engineeringProjectDetail.getPumpParts())) {
			sb.append("，泵送部位：" + engineeringProjectDetail.getPumpParts());
		} else {
			sb.append("。");
		}
		sb.append("");
		sb.append("收到短信后，接受请回复“" + engineeringProjectDetail.getId() +"0”");
		sb.append("，拒绝请回复“" + engineeringProjectDetail.getId() +"1”");
		SmsUtil.sendSms(commonSms);
			
	}

}
