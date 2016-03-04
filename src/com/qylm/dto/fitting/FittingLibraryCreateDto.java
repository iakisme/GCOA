package com.qylm.dto.fitting;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.qylm.bean.returner.Returner;
import com.qylm.entity.FittingLibrary;
import com.qylm.entity.FittingLibraryDetail;
import com.qylm.entity.FittingRepertory;
import com.qylm.entity.User;

/**
 * 保存配件采购管理画面需要的值
 * @author smj
 */
public class FittingLibraryCreateDto implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6431499339303519410L;
	
	/**
	 * 出库单编号
	 */
	private String number;
	
	/**
	 * 出库日期
	 */
	private Date libraryDate;
	
	/**
	 * 出库类型
	 * <ul>
	 * <li>1:车辆维修出库</li>
	 * </ul>
	 */
	private String libraryType;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 出库状态
	 * true：已出库，反之未出库
	 */
	private boolean state;

	/**
	 * 配件采购详细
	 */
	private List<FittingLibraryDetail> fittingLibraryDetailList;
	
	/**
	 * 修改传值
	 */
	private FittingLibraryDetail fittingLibraryDetail;
	
	/**
	 * 配件名称（搜索条件）
	 */
	private String fittingName;
	
	/**
	 * 配件库存列表
	 */
	private List<FittingRepertory> fittingRepertoryList;
	
	/**
	 * 确认配件库存列表
	 */
	private List<FittingRepertory> queryFittingRepertoryList;
	
	/**
	 * 修改传值
	 */
	private FittingLibrary transferFittingLibrary;
	
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
	 * @return the libraryDate
	 */
	public Date getLibraryDate() {
		return libraryDate;
	}

	/**
	 * @param libraryDate the libraryDate to set
	 */
	public void setLibraryDate(Date libraryDate) {
		this.libraryDate = libraryDate;
	}

	/**
	 * @return the libraryType
	 */
	public String getLibraryType() {
		return libraryType;
	}

	/**
	 * @param libraryType the libraryType to set
	 */
	public void setLibraryType(String libraryType) {
		this.libraryType = libraryType;
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
	 * @return the fittingLibraryDetailList
	 */
	public List<FittingLibraryDetail> getFittingLibraryDetailList() {
		return fittingLibraryDetailList;
	}

	/**
	 * @param fittingLibraryDetailList the fittingLibraryDetailList to set
	 */
	public void setFittingLibraryDetailList(
			List<FittingLibraryDetail> fittingLibraryDetailList) {
		this.fittingLibraryDetailList = fittingLibraryDetailList;
	}

	/**
	 * @return the fittingLibraryDetail
	 */
	public FittingLibraryDetail getFittingLibraryDetail() {
		return fittingLibraryDetail;
	}

	/**
	 * @param fittingLibraryDetail the fittingLibraryDetail to set
	 */
	public void setFittingLibraryDetail(FittingLibraryDetail fittingLibraryDetail) {
		this.fittingLibraryDetail = fittingLibraryDetail;
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
	 * @return the fittingRepertoryList
	 */
	public List<FittingRepertory> getFittingRepertoryList() {
		return fittingRepertoryList;
	}

	/**
	 * @param fittingRepertoryList the fittingRepertoryList to set
	 */
	public void setFittingRepertoryList(List<FittingRepertory> fittingRepertoryList) {
		this.fittingRepertoryList = fittingRepertoryList;
	}

	/**
	 * @return the queryFittingRepertoryList
	 */
	public List<FittingRepertory> getQueryFittingRepertoryList() {
		return queryFittingRepertoryList;
	}

	/**
	 * @param queryFittingRepertoryList the queryFittingRepertoryList to set
	 */
	public void setQueryFittingRepertoryList(
			List<FittingRepertory> queryFittingRepertoryList) {
		this.queryFittingRepertoryList = queryFittingRepertoryList;
	}

	/**
	 * @return the transferFittingLibrary
	 */
	public FittingLibrary getTransferFittingLibrary() {
		return transferFittingLibrary;
	}

	/**
	 * @param transferFittingLibrary the transferFittingLibrary to set
	 */
	public void setTransferFittingLibrary(FittingLibrary transferFittingLibrary) {
		this.transferFittingLibrary = transferFittingLibrary;
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
