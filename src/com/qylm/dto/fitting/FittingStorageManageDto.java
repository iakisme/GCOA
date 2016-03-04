package com.qylm.dto.fitting;

import java.io.Serializable;
import java.util.Date;

/**
 * 保存配件入库管理画面需要的值
 * @author smj
 */
public class FittingStorageManageDto implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -878340529116068407L;

	/**
	 * 入库单编号
	 */
	private String number;
	
	/**
	 * 入库日期（开始）
	 */
	private Date beginSorageDate;

	/**
	 * 入库日期（结束）
	 */
	private Date endSorageDate;

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
	 * @return the beginSorageDate
	 */
	public Date getBeginSorageDate() {
		return beginSorageDate;
	}

	/**
	 * @param beginSorageDate the beginSorageDate to set
	 */
	public void setBeginSorageDate(Date beginSorageDate) {
		this.beginSorageDate = beginSorageDate;
	}

	/**
	 * @return the endSorageDate
	 */
	public Date getEndSorageDate() {
		return endSorageDate;
	}

	/**
	 * @param endSorageDate the endSorageDate to set
	 */
	public void setEndSorageDate(Date endSorageDate) {
		this.endSorageDate = endSorageDate;
	}

}
