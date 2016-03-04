package com.qylm.common.sms;

import org.smslib.AGateway;
import org.smslib.IOutboundMessageNotification;
import org.smslib.OutboundMessage;

public class OutboundNotification implements IOutboundMessageNotification {

	public void process(String gatewayId, OutboundMessage msg) {
		System.out.println("短信设备开始启动");
		System.out.println("Outbound handler called from Gateway: " + gatewayId);
		System.out.println(msg);
	}

	public void process(AGateway arg0, OutboundMessage arg1) {
		// TODO Auto-generated method stub
		
	}

}
