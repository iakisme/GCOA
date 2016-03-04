package com.qylm.common.sms;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.LinkedList;  
import org.smslib.GatewayException;
import org.smslib.InboundMessage.MessageClasses;
import org.smslib.Message.MessageEncodings;
import org.smslib.InboundMessage;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.smslib.modem.SerialModemGateway;
import com.qylm.entity.CommonSms;
import examples.SendMessage;

/**
 * 短信发，读取，删除，串口打开、关闭操作工具类
 * @author 盛民军
 */
public final class SmsUtil {
	
	/**s
	 * 短信服务
	 */
	private static Service srv = null;
	
	/**
	 * 为null时才启动服务
	 */
	public static boolean startService() {
		srv = Service.getInstance();
		Map<String,String> map = getProperties("sms.properties");
		System.out.println(" map.get(\"comPort\")="+ map.get("comPort"));
		SerialModemGateway gateway = new SerialModemGateway("SMS", map.get("comPort"),
				Integer.parseInt(map.get("baudRate")), map.get("manufacturer"), map.get("model"));

		gateway.setInbound(true);
		gateway.setOutbound(true);
		try {
			srv.S.SERIAL_POLLING = true;
			srv.addGateway(gateway);

			srv.startService();
			System.out.println("Modem connected.");

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static Map<String,String> getProperties(String path) {
		Properties p = new Properties();
		InputStream inputStream = SendMessage.class.getResourceAsStream(path);
		
		try {
			p.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Map<String,String> map = new HashMap<String,String>();
		Iterator<Object> it =p.keySet().iterator();
		String key="";
		while(it.hasNext()){
			key = it.next().toString();
			map.put(key, p.getProperty(key).trim());
		}
		return map;
	}
	
	/**
	 * 终止服务
	 */
	public static void stopSerice() {
		try {
			System.out.println("Modem disconnected.");
			srv.stopService();
		} catch (Exception ex) {

		}
	}
	
	/**
	 * 发短信
	 * @param commonSms
	 * @return true发送成功，反之失败
	 */
	public static boolean sendSms(CommonSms commonSms) {
		if (srv == null) {
			if(!startService()){
				stopSerice();
				srv=null;
				return false;
			}
		}
		OutboundMessage msg = new OutboundMessage(commonSms.getRecver(), commonSms.getSmstext());
		msg.setEncoding(MessageEncodings.ENCUCS2);
		try {
			srv.sendMessage(msg);
			System.out.println(msg);
		} catch (Exception ex) {
			try {
				System.out.println("   ...");
				srv.sendMessage(msg);
				System.out.println(msg);
			} catch (Exception ex2) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 读取所有短信
	 * @return
	 */
	public static List<InboundMessage> readSms() {
		 List<InboundMessage> msgList = new LinkedList<InboundMessage>();  
	       try {
	    	   startService();
	            srv.readMessages(msgList, MessageClasses.ALL); 
	        } catch (Exception e) {  
	        	System.out.println(e);
	        }  
	       return msgList;
	}
	
	/**
	 * 删除某条短信
	 * @param index
	 * @return
	 */
	public static boolean deleteSMS(InboundMessage inboundMessage) {
		boolean state = false;
		try {
			Thread.sleep(100);
			state = srv.deleteMessage(inboundMessage);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (GatewayException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return state;
	}
	
	/**
	 * 删除所有短信
	 * @param inboundMessageList
	 * @return
	 */
	public static void deleteAllSms(List<InboundMessage> inboundMessageList) {
		if (inboundMessageList != null && !inboundMessageList.isEmpty()) {
			for (InboundMessage inboundMessage : inboundMessageList) {
				SmsUtil.deleteSMS(inboundMessage);
			}
		}
	}
}
