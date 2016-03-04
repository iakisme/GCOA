package com.qylm.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 配件采购详细久化类
 * @author smj
 */
@Entity
@Table(uniqueConstraints = {}, name = "fittingpurchase_detail")
public class FittingPurchaseDetail extends BaseFittingInfo {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3184712421495491116L;
	
	/**
	 * 采购单
	 */
	public static final String FITTING_PURCHASE = "fittingPurchase";
	
	/**
	 * 采购单
	 */
	@ManyToOne(targetEntity = FittingPurchase.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "fittingPurchaseId")
	private FittingPurchase fittingPurchase;
	
	/**
	 * 数量
	 */
	private BigDecimal quantity;
	
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
	 * @return the quantity
	 */
	public BigDecimal getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

}
