package com.qylm.dto.fitting;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.qylm.bean.returner.Returner;
import com.qylm.entity.FittingInfo;
import com.qylm.entity.FittingStorage;
import com.qylm.entity.FittingStorageDetail;
import com.qylm.entity.User;

/**
 * 保存配件采购管理画面需要的值
 * @author smj
 */
public class FittingStorageCreateDto implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6431499339303519410L;
	
	/**
	 * 配件采购单
	 */
	private FittingStorage fittingStorage;
	
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
	 * 配件采购详细
	 */
	private List<FittingStorageDetail> fittingStorageDetailList;
	
	/**
	 * 修改传值
	 */
	private FittingStorageDetail fittingStorageDetail;
	
	/**
	 * 配件名称（搜索条件）
	 */
	private String fittingName;
	
	/**
	 * 配件信息列表
	 */
	private List<FittingInfo> fittingInfoList;
	
	/**
	 * 修改传值
	 */
	private FittingStorage transferFittingStorage;
	
	/**
	 * 创建配件
	 */
	private User creater;
	
	/**
	 * 归属公司
	 */
	private User belongingUser;
	
	/**
	 * 返回共通方法
	 */
	private Returner<?, ?, ?> returner;

	/**
	 * @return the fittingStorage
	 */
	public FittingStorage getFittingStorage() {
		return fittingStorage;
	}

	/**
	 * @param fittingStorage the fittingStorage to set
	 */
	public void setFittingStorage(FittingStorage fittingStorage) {
		this.fittingStorage = fittingStorage;
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

	/**
	 * @return the fittingStorageDetail
	 */
	public FittingStorageDetail getFittingStorageDetail() {
		return fittingStorageDetail;
	}

	/**
	 * @param fittingStorageDetail the fittingStorageDetail to set
	 */
	public void setFittingStorageDetail(FittingStorageDetail fittingStorageDetail) {
		this.fittingStorageDetail = fittingStorageDetail;
	}

	/**
	 * @return the fittingName
	 */
	public String getFittingName() {
		return fittingName;
	}

	/**
	 * @param fittingName the fittingName to set
	 */
	public void setFittingName(String fittingName) {
		this.fittingName = fittingName;
	}

	/**
	 * @return the fittingInfoList
	 */
	public List<FittingInfo> getFittingInfoList() {
		return fittingInfoList;
	}

	/**
	 * @param fittingInfoList the fittingInfoList to set
	 */
	public void setFittingInfoList(List<FittingInfo> fittingInfoList) {
		this.fittingInfoList = fittingInfoList;
	}

	/**
	 * @return the transferFittingStorage
	 */
	public FittingStorage getTransferFittingStorage() {
		return transferFittingStorage;
	}

	/**
	 * @param transferFittingStorage the transferFittingStorage to set
	 */
	public void setTransferFittingStorage(FittingStorage transferFittingStorage) {
		this.transferFittingStorage = transferFittingStorage;
	}

	/**
	 * @return the creater
	 */
	public User getCreater() {
		return creater;
	}

	/**
	 * @param creater the creater to set
	 */
	public void setCreater(User creater) {
		this.creater = creater;
	}

	/**
	 * @return the belongingUser
	 */
	public User getBelongingUser() {
		return belongingUser;
	}

	/**
	 * @param belongingUser the belongingUser to set
	 */
	public void setBelongingUser(User belongingUser) {
		this.belongingUser = belongingUser;
	}

	/**
	 * @return the returner
	 */
	public Returner<?, ?, ?> getReturner() {
		return returner;
	}

	/**
	 * @param returner the returner to set
	 */
	public void setReturner(Returner<?, ?, ?> returner) {
		this.returner = returner;
	}
	
}
