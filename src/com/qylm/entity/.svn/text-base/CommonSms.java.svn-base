package com.qylm.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/***
 * CommonSms 短信用于全局变量
 * @author cms
 * @version 1.9, 09/10/20
 */
@Entity
@Table(uniqueConstraints = {}, name = "commonsms")
public class CommonSms extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3063527390289820173L;
	
	/**
	 * 消息状态：成功
	 */
	public static final String STATE_1 = "1";
	
	/**
	 * 消息状态：失败
	 */
	public static final String STATE_2 = "2";
	
	/**
	 * 消息状态：系统已读取
	 */
	public static final String STATE_3 = "3";
	
	/**
	 * 回复短信的id(短信猫中的id)
	 */
	private String smsId;

	/**
	 * 短信内容
	 */
	private String smstext;
	
	/**
	 * 短信发送方
	 */
	private String sender;
	
	/**
	 * 短信接收发
	 */
	private String recver;
	
	/**
	 * 消息状态
	 * 1：成功
	 * 2：失败
	 * 3：系统已读取
	 */
	private String state;
	
	/**
	 * @return the smsId
	 */
	public String getSmsId() {
		return smsId;
	}

	/**
	 * @param smsId the smsId to set
	 */
	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}

	/**
	 * @return the smstext
	 */
	public String getSmstext() {
		return smstext;
	}

	/**
	 * @param smstext the smstext to set
	 */
	public void setSmstext(String smstext) {
		this.smstext = smstext;
	}

	/**
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * @param sender the sender to set
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * @return the recver
	 */
	public String getRecver() {
		return recver;
	}

	/**
	 * @param recver the recver to set
	 */
	public void setRecver(String recver) {
		this.recver = recver;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

}
