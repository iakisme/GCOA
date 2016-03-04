package com.qylm.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 配件采购久化类
 * @author smj
 */
@Entity
@Table(uniqueConstraints = {}, name = "fittingpurchase")
public class FittingPurchase extends BaseEntity {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4228076790797793518L;
	
	/**
	 * 采购编号
	 */
	public static final String SERIAL_NUMBER = "serialNumber";
	
	/**
	 * 申请日期
	 */
	public static final String APPLY_DATE = "applyDate";
	
	/**
	 * 采购日期
	 */
	public static final String PURCHASE_DATE = "purchaseDate";
	
	/**
	 * 检索条件：采购单状态
	 */
	public static final String STATE = "state";
	
	/**
	 * 存放最新的采购单编号
	 */
	public static String PURCHASE_NUMBER_VALUE;
	
	/**
	 * 检索条件：采购单状态 草稿状态
	 */
	public static final String STATE_1 = "1";
	
	/**
	 * 检索条件：采购单状态 已提交，待审核
	 */
	public static final String STATE_2 = "2";
	
	/**
	 * 检索条件：采购单状态 审核未通过
	 */
	public static final String STATE_3 = "3";
	
	/**
	 * 检索条件：采购单状态 审核通过
	 */
	public static final String STATE_4 = "4";
	
	/**
	 * 检索条件：采购单状态 采购中
	 */
	public static final String STATE_5 = "5";
	
	/**
	 * 检索条件：采购单状态 采购归来，待入库
	 */
	public static final String STATE_6 = "6";
	
	/**
	 * 检索条件：采购单状态 已入库
	 */
	public static final String STATE_7 = "7";
	
	/**
	 * 检索条件：采购单状态 已完结
	 */
	public static final String STATE_8 = "8";
	
	/**
	 * 检索条件：采购单状态 采购失败
	 */
	public static final String STATE_9 = "9";
	
	/**
	 * 采购人员
	 */
	public static final String PURCHASE_USER = "purchaseUser";
	
	/**
	 * 入库人员
	 */
	public static final String STORAGE_USER = "storageUser";
	
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
	 * 审批金额
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
	 * <li>1：草稿状态</li>
	 * <li>2：已提交，待审核</li>
	 * <li>3：审核未通过</li>
	 * <li>4：审核通过</li>
	 * <li>5：采购中</li>
	 * <li>6：采购归来，待入库</li>
	 * <li>7：已入库（入库人员提出申请时为此状态）</li>
	 * <li>8：采购完结</li>
	 * <li>9：采购失败</li>
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
	@ManyToOne(targetEntity = User.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "purchaseUserId")
	private User purchaseUser;
	
	/**
	 * 入库人员
	 */
	@ManyToOne(targetEntity = User.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "storageUserId")
	private User storageUser;
	
	/**
	 * 存放当前采购单下的采购详细
	 */
	@Transient
	private List<FittingPurchaseDetail> fittingPurchaseDetailList;
	
	/**
	 * 是否是草稿状态， true 是 false 不是
	 * @return
	 */
	@Transient
	public boolean isDraft() {
		return FittingPurchase.STATE_1.equals(state);
	}
	
	@Transient
	public boolean isExamineState() {
		return FittingPurchase.STATE_3.equals(state) || FittingPurchase.STATE_4.equals(state);
	}
	
	/**
	 * 此方法用于页面显示，获取项目状态的颜色样子
	 * @return
	 */
	public String getTypeColor() {
		int i = Integer.valueOf(state);
		String color;
		switch (i) {
		case 1:
			color = "#000000";// 黑色
			break;
		case 2:
			color = "#FF4040";// 暗红色
			break;
		case 3:
			color = "#FF0000";// 红色
			break;
		case 4:
			color = "#00FF00";// 绿色
			break;
		case 5:
			color = "#FFD700";// 黄色
		break;
		case 6:
			color = "#0000FF";// 蓝色
		break;
		case 7:
			color = "#8B4513";// 棕色
		break;
		case 8:
			color = "#FF0000";// 红色
		break;
		default:
			color = "#000000";// 黑色
			break;
		}
		return color;
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
	
}
