package com.qylm.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.qylm.common.utils.StringUtil;

/**
 * 采购审核设定管理
 * @author ShengMinJun
 */
@Entity
@Table(uniqueConstraints = {}, name = "fittingpurchaseexamine")
public class FittingPurchaseExamine extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3005531402496391801L;

	/**
	 * 检索条件：fittingPurchase
	 */
	public static final String FITTING_PURCHASE = "fittingPurchase";
	
	/**
	 * 采购编号
	 */
	public static final String FITTING_PURCHASE_SERIAL_NUMBER = "fittingPurchase.serialNumber";
	
	/**
	 * 申请日期
	 */
	public static final String FITTING_PURCHASE_APPLY_DATE = "fittingPurchase.applyDate";
	
	/**
	 * 检索条件：fittingPurchase.state
	 */
	public static final String FITTING_PURCHASE_STATE = "fittingPurchase.state";
	
	/**
	 * 检索条件：fittingPurchase.采购人员
	 */
	public static final String FITTING_PURCHASE_PURCHASE_USER = "fittingPurchase.purchaseUser";
	
	/**
	 * 检索条件：fittingPurchase.入库人员
	 */
	public static final String FITTING_PURCHASE_STORAGE_USER = "fittingPurchase.storageUser";
	
	/**
	 * 检索条件：fittingPurchase.创建人员
	 */
	public static final String FITTING_PURCHASE_CREATER = "fittingPurchase.creater";
	
	/**
	 * 检索条件：auditor
	 */
	public static final String AUDITOR = "auditor";
	
	/**
	 * 检索条件：state
	 */
	public static final String STATE = "state";
	
	/**
	 * 检索条件：state 1待审核
	 */
	public static final String STATE_1 = "1";
	
	/**
	 * 检索条件：state 2审核通过
	 */
	public static final String STATE_2 = "2";
	
	/**
	 * 检索条件：state 3审核未通过
	 */
	public static final String STATE_3 = "3";
	
	/**
	 * 检索条件：sequence
	 */
	public static final String SEQUENCE = "sequence";

	/**
	 * 采购单
	 */
	@ManyToOne(targetEntity = FittingPurchase.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "fittingPurchaseId")
	private FittingPurchase fittingPurchase;
	
	/**
	 * 审核员
	 */
	@ManyToOne(targetEntity = User.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "auditorId")
	private User auditor;
	
	/**
	 * 等级顺序
	 */
	private Integer sequence;
	
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
	 * 备注
	 */
	private String remark;
	
	/**
	 * 采购单详细
	 */
	@Transient
	private List<FittingPurchaseDetail> fittingPurchaseDetailList;
	
	/**
	 * 获取审核状态名称
	 * @return
	 */
	@Transient
	public String getStateName() {
		if (StringUtil.isBlank(state)) {
			return "未审核";
		} else if (STATE_1.equals(state)) {
			return "待审核";
		} else if (STATE_2.equals(state)) {
			return "审核通过";
		} else {
			return "审核未通过";
		}
	}
	
	/**
	 * 审核画面的审核通过按钮样式
	 * @return
	 */
	@Transient
	public boolean isThroughBtnState() {
		if (STATE_1.equals(state)) {
			return false;
		}
		return true;
	}
	
	/**
	 * 审核画面的审核未通过按钮样式
	 * @return
	 */
	@Transient
	public boolean isNotThroughBtnState() {
		if (STATE_1.equals(state)) {
			return false;
		}
		return true;
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
			color = "#FFD700";// 黄色
			break;
		case 2:
			color = "#00FF00";// 绿色
			break;
		case 3:
			color = "#FF0000";// 红色
			break;
		default:
			color = "#000000";// 黑色
			break;
		}
		return color;
	}
	
	/**
	 * get fittingPurchase
	 * @return the fittingPurchase
	 */
	public FittingPurchase getFittingPurchase() {
		return fittingPurchase;
	}

	/**
	 * set fittingPurchase
	 * @param fittingPurchase the fittingPurchase to set
	 */
	public void setFittingPurchase(FittingPurchase fittingPurchase) {
		this.fittingPurchase = fittingPurchase;
	}

	/**
	 * get auditor
	 * @return the auditor
	 */
	public User getAuditor() {
		return auditor;
	}

	/**
	 * set auditor
	 * @param auditor the auditor to set
	 */
	public void setAuditor(User auditor) {
		this.auditor = auditor;
	}

	/**
	 * get sequence
	 * @return the sequence
	 */
	public Integer getSequence() {
		return sequence;
	}

	/**
	 * set sequence
	 * @param sequence the sequence to set
	 */
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	/**
	 * get state
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * set state
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * get remark
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * set remark
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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
