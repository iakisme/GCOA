package com.qylm.common.sms;

import org.smslib.AGateway;
import org.smslib.ICallNotification;

public class CallNotification implements ICallNotification {

	public void process(String gatewayId, String callerId) {
		System.out.println(">>> New call detected from Gateway: "
				+ gatewayId + " : " + callerId);
	}

	public void process(AGateway arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

}
