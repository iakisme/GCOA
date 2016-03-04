package com.qylm.dto.fitting;

import java.io.Serializable;
import java.util.Date;

/**
 * 保存配件采购管理画面需要的值
 * @author smj
 */
public class FittingPurchaseManageDto implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7313845189885188320L;

	/**
	 * 采购编号 自动生成
	 */
	private String serialNumber;

	/**
	 * 申请日期（开始）
	 */
	private Date beginApplyDate;
	
	/**
	 * 申请日期（结束）
	 */
	private Date endApplyDate;
	
	/**
	 * 采购日期（开始）
	 */
	private Date beginPurchaseDate;
	
	/**
	 * 采购日期（结束）
	 */
	private Date endPurchaseDate;
	
	/**
	 * 采购单状态
	 */
	private String state;

	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * @return the beginApplyDate
	 */
	public Date getBeginApplyDate() {
		return beginApplyDate;
	}

	/**
	 * @param beginApplyDate the beginApplyDate to set
	 */
	public void setBeginApplyDate(Date beginApplyDate) {
		this.beginApplyDate = beginApplyDate;
	}

	/**
	 * @return the endApplyDate
	 */
	public Date getEndApplyDate() {
		return endApplyDate;
	}

	/**
	 * @param endApplyDate the endApplyDate to set
	 */
	public void setEndApplyDate(Date endApplyDate) {
		this.endApplyDate = endApplyDate;
	}

	/**
	 * @return the beginPurchaseDate
	 */
	public Date getBeginPurchaseDate() {
		return beginPurchaseDate;
	}

	/**
	 * @param beginPurchaseDate the beginPurchaseDate to set
	 */
	public void setBeginPurchaseDate(Date beginPurchaseDate) {
		this.beginPurchaseDate = beginPurchaseDate;
	}

	/**
	 * @return the endPurchaseDate
	 */
	public Date getEndPurchaseDate() {
		return endPurchaseDate;
	}

	/**
	 * @param endPurchaseDate the endPurchaseDate to set
	 */
	public void setEndPurchaseDate(Date endPurchaseDate) {
		this.endPurchaseDate = endPurchaseDate;
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
