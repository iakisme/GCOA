package com.qylm.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 配件入库久化类
 * @author smj
 */
@Entity
@Table(uniqueConstraints = {}, name = "fittingstorage")
public class FittingStorage extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 480033594748613332L;
	
	/**
	 * 采购单
	 */
	public static final String FITTING_PURCHASE = "fittingPurchase";
	
	/**
	 * 入库单编号
	 */
	public static final String NUMBER = "number";

	/**
	 * 入库日期
	 */
	public static final String STORAGE_DATE = "storageDate";
	
	/**
	 * 配件采购单
	 */
	@ManyToOne(targetEntity = FittingPurchase.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "fittingPurchaseId")
	private FittingPurchase fittingPurchase;
	
	/**
	 * 入库单编号
	 */
	private String number;
	
	/**
	 * 入库日期
	 */
	private Date storageDate;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 入库状态
	 * true：已入库，反之未入库
	 */
	private boolean state;
	
	/**
	 * 存放对于的入库详细
	 */
	@Transient
	private List<FittingStorageDetail> fittingStorageDetailList;

	/**
	 * @return the fittingPurchase
	 */
	public FittingPurchase getFittingPurchase() {
		return fittingPurchase;
	}

	/**
	 * @param fittingPurchase the fittingPurchase to set
	 */
	public void setFittingPurchase(FittingPurchase fittingPurchase) {
		this.fittingPurchase = fittingPurchase;
	}

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
	 * @return the storageDate
	 */
	public Date getStorageDate() {
		return storageDate;
	}

	/**
	 * @param storageDate the storageDate to set
	 */
	public void setStorageDate(Date storageDate) {
		this.storageDate = storageDate;
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
	 * @return the fittingStorageDetailList
	 */
	public List<FittingStorageDetail> getFittingStorageDetailList() {
		return fittingStorageDetailList;
	}

	/**
	 * @param fittingStorageDetailList the fittingStorageDetailList to set
	 */
	public void setFittingStorageDetailList(
			List<FittingStorageDetail> fittingStorageDetailList) {
		this.fittingStorageDetailList = fittingStorageDetailList;
	}
	
}
