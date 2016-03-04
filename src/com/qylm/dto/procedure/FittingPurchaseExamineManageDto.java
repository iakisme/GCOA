package com.qylm.dto.procedure;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.qylm.entity.FittingPurchaseDetail;
import com.qylm.entity.FittingPurchaseExamine;
import com.qylm.entity.User;

/**
 * 存放采购单审核管理画面需要保存的值
 * @author smj
 */
public class FittingPurchaseExamineManageDto implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7268195806689261038L;

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
	 * 修改传值
	 */
	private FittingPurchaseExamine fittingPurchaseExamine;
	
	/**
	 * 审核操作显示状态，true可操作，反之查看
	 */
	private boolean showExamine;
	
	/**
	 * 审核状态
	 * <ul>
	 * <li>1：待审核</li>
	 * <li>2：审核通过</li>
	 * <li>3：审核未通过</li>
	 * </ul>
	 */
	private String state;
	
	/**
	 * 采购编号 自动生成
	 */
	private String serialNumbers;

	/**
	 * 申请日期
	 */
	private Date applyDate;
	
	/**
	 * 申请预算金额
	 */
	private BigDecimal applyPrice;
	
	/**
	 * 审批金额
	 */
	private BigDecimal budgetPrice;
	
	/**
	 * 采购内容
	 */
	private String title;
	
	/**
	 * 采购源
	 */
	private String purchaseSource;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 采购人员
	 */
	private User purchaseUser;
	
	/**
	 * 入库人员
	 */
	private User storageUser;
	
	/**
	 * 申请人
	 */
	private User creater;
	
	/**
	 * 审核备注
	 */
	private String examineRemark;
	
	/**
	 * 采购单详细
	 */
	private List<FittingPurchaseDetail> fittingPurchaseDetailList;

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
	 * @return the fittingPurchaseExamine
	 */
	public FittingPurchaseExamine getFittingPurchaseExamine() {
		return fittingPurchaseExamine;
	}

	/**
	 * @param fittingPurchaseExamine the fittingPurchaseExamine to set
	 */
	public void setFittingPurchaseExamine(
			FittingPurchaseExamine fittingPurchaseExamine) {
		this.fittingPurchaseExamine = fittingPurchaseExamine;
	}

	/**
	 * @return the showExamine
	 */
	public boolean isShowExamine() {
		return showExamine;
	}

	/**
	 * @param showExamine the showExamine to set
	 */
	public void setShowExamine(boolean showExamine) {
		this.showExamine = showExamine;
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

	/**
	 * @return the serialNumbers
	 */
	public String getSerialNumbers() {
		return serialNumbers;
	}

	/**
	 * @param serialNumbers the serialNumbers to set
	 */
	public void setSerialNumbers(String serialNumbers) {
		this.serialNumbers = serialNumbers;
	}

	/**
	 * @return the applyDate
	 */
	public Date getApplyDate() {
		return applyDate;
	}

	/**
	 * @param applyDate the applyDate to set
	 */
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	/**
	 * @return the applyPrice
	 */
	public BigDecimal getApplyPrice() {
		return applyPrice;
	}

	/**
	 * @param applyPrice the applyPrice to set
	 */
	public void setApplyPrice(BigDecimal applyPrice) {
		this.applyPrice = applyPrice;
	}

	/**
	 * @return the budgetPrice
	 */
	public BigDecimal getBudgetPrice() {
		return budgetPrice;
	}

	/**
	 * @param budgetPrice the budgetPrice to set
	 */
	public void setBudgetPrice(BigDecimal budgetPrice) {
		this.budgetPrice = budgetPrice;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the purchaseSource
	 */
	public String getPurchaseSource() {
		return purchaseSource;
	}

	/**
	 * @param purchaseSource the purchaseSource to set
	 */
	public void setPurchaseSource(String purchaseSource) {
		this.purchaseSource = purchaseSource;
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
	 * @return the purchaseUser
	 */
	public User getPurchaseUser() {
		return purchaseUser;
	}

	/**
	 * @param purchaseUser the purchaseUser to set
	 */
	public void setPurchaseUser(User purchaseUser) {
		this.purchaseUser = purchaseUser;
	}

	/**
	 * @return the storageUser
	 */
	public User getStorageUser() {
		return storageUser;
	}

	/**
	 * @param storageUser the storageUser to set
	 */
	public void setStorageUser(User storageUser) {
		this.storageUser = storageUser;
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
	 * @return the examineRemark
	 */
	public String getExamineRemark() {
		return examineRemark;
	}

	/**
	 * @param examineRemark the examineRemark to set
	 */
	public void setExamineRemark(String examineRemark) {
		this.examineRemark = examineRemark;
	}

	/**
	 * @return the fittingPurchaseDetailList
	 */
	public List<FittingPurchaseDetail> getFittingPurchaseDetailList() {
		return fittingPurchaseDetailList;
	}

	/**
	 * @param fittingPurchaseDetailList the fittingPurchaseDetailList to set
	 */
	public void setFittingPurchaseDetailList(
			List<FittingPurchaseDetail> fittingPurchaseDetailList) {
		this.fittingPurchaseDetailList = fittingPurchaseDetailList;
	}
	
}
