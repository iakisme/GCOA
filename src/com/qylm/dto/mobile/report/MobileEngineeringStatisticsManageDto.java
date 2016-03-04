package com.qylm.dto.mobile.report;

import java.io.Serializable;
import java.util.Date;

/**
 * 保存生产报表统计管理画面需要的值
 * @author smj
 */
public class MobileEngineeringStatisticsManageDto implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5888463932181753855L;

	/**
	 * 车辆编号
	 */
	private String number;
	
	/**
	 * 工作地址
	 */
	private String workAddress;
	
	/**
	 * 日期
	 */
	private Date beginDate;
	
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the workAddress
	 */
	public String getWorkAddress() {
		return workAddress;
	}

	/**
	 * @param workAddress the workAddress to set
	 */
	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	/**
	 * @return the beginDate
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * @param beginDate the beginDate to set
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
}
