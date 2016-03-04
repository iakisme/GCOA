package com.qylm.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 配件退货久化类
 * @author smj
 */
@Entity
@Table(uniqueConstraints = {}, name = "fittingrejected")
public class FittingRejected extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5852472389517266620L;

	/**
	 * 退货单编号
	 */
	public static final String NUMBER = "number";

	/**
	 * 退货日期
	 */
	public static final String REJECTED_DATE = "rejectedDate";
	
	/**
	 * 退货单编号
	 */
	private String number;
	
	/**
	 * 退货日期
	 */
	private Date rejectedDate;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 退货状态
	 * true：已退货，反之未退货
	 */
	private boolean state;
	
	/**
	 * 存放对于的退货详细
	 */
	@Transient
	private List<FittingRejectedDetail> fittingRejectedDetailList;

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
	 * @return the rejectedDate
	 */
	public Date getRejectedDate() {
		return rejectedDate;
	}

	/**
	 * @param rejectedDate the rejectedDate to set
	 */
	public void setRejectedDate(Date rejectedDate) {
		this.rejectedDate = rejectedDate;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the state
	 */
	public boolean isState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(boolean state) {
		this.state = state;
	}

	/**
	 * @return the fittingRejectedDetailList
	 */
	public List<FittingRejectedDetail> getFittingRejectedDetailList() {
		return fittingRejectedDetailList;
	}

	/**
	 * @param fittingRejectedDetailList the fittingRejectedDetailList to set
	 */
	public void setFittingRejectedDetailList(
			List<FittingRejectedDetail> fittingRejectedDetailList) {
		this.fittingRejectedDetailList = fittingRejectedDetailList;
	}

}
