package com.qylm.dto.fitting;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.qylm.bean.returner.Returner;
import com.qylm.entity.FittingInfo;
import com.qylm.entity.FittingPurchase;
import com.qylm.entity.FittingPurchaseDetail;
import com.qylm.entity.User;

/**
 * 保存配件采购管理画面需要的值
 * @author smj
 */
public class FittingPurchaseCreateDto implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6431499339303519410L;

	/**
	 * 采购编号 自动生成
	 */
	private String serialNumber;

	/**
	 * 申请日期
	 */
	private Date applyDate;
	
	/**
	 * 申请预算金额
	 */
	private BigDecimal applyPrice;
	
	/**
	 * 配件总金额
	 */
	private BigDecimal sumFittingPrice;
	
	/**
	 * 预算金额
	 */
	private BigDecimal budgetPrice;
	
	/**
	 * 采购日期
	 */
	private Date purchaseDate;
	
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
	 * 采购总金额
	 */
	private BigDecimal purchasePrice;
	
	/**
	 * 采购单状态
	 * <ul>
	 * <li>0：草稿状态</li>
	 * <li>1：已提交，待审核</li>
	 * <li>2：审核未通过</li>
	 * <li>3：审核通过</li>
	 * <li>4：采购中</li>
	 * <li>5：采购归来，待入库</li>
	 * <li>6：已入库（入库人员提出申请时为此状态）</li>
	 * <li>7：采购完结</li>
	 * </ul>
	 */
	private String state;
	
	/**
	 * 运费
	 */
	private BigDecimal freight;
	
	/**
	 * 采购人员
	 */
	private User purchaseUser;
	
	/**
	 * 入库人员
	 */
	private User storageUser;
	
	/**
	 * 配件采购详细
	 */
	private List<FittingPurchaseDetail> fittingPurchaseDetailList;
	
	/**
	 * 修改传值
	 */
	private FittingPurchaseDetail fittingPurchaseDetail;
	
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
	private FittingPurchase transferFittingPurchase;
	
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
	 * 是否是草稿状态， true 是 false 不是
	 * @return
	 */
	public boolean isDraft() {
		return FittingPurchase.STATE_1.equals(state);
	}

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
	 * @return the sumFittingPrice
	 */
	public BigDecimal getSumFittingPrice() {
		return sumFittingPrice;
	}

	/**
	 * @param sumFittingPrice the sumFittingPrice to set
	 */
	public void setSumFittingPrice(BigDecimal sumFittingPrice) {
		this.sumFittingPrice = sumFittingPrice;
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
	 * @return the purchaseDate
	 */
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	/**
	 * @param purchaseDate the purchaseDate to set
	 */
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
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
	 * @return the purchasePrice
	 */
	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	/**
	 * @param purchasePrice the purchasePrice to set
	 */
	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
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
	 * @return the freight
	 */
	public BigDecimal getFreight() {
		return freight;
	}

	/**
	 * @param freight the freight to set
	 */
	public void setFreight(BigDecimal freight) {
		this.freight = freight;
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

	/**
	 * @return the fittingPurchaseDetail
	 */
	public FittingPurchaseDetail getFittingPurchaseDetail() {
		return fittingPurchaseDetail;
	}

	/**
	 * @param fittingPurchaseDetail the fittingPurchaseDetail to set
	 */
	public void setFittingPurchaseDetail(FittingPurchaseDetail fittingPurchaseDetail) {
		this.fittingPurchaseDetail = fittingPurchaseDetail;
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
	 * @return the transferFittingPurchase
	 */
	public FittingPurchase getTransferFittingPurchase() {
		return transferFittingPurchase;
	}

	/**
	 * @param transferFittingPurchase the transferFittingPurchase to set
	 */
	public void setTransferFittingPurchase(FittingPurchase transferFittingPurchase) {
		this.transferFittingPurchase = transferFittingPurchase;
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
