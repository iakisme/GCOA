package com.qylm.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 配件退货详细久化类
 * @author smj
 */
@Entity
@Table(uniqueConstraints = {}, name = "fittingrejected_detail")
public class FittingRejectedDetail extends BaseFittingInfo {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4299985016654336027L;

	/**
	 * 退货单
	 */
	public static final String FITTING_REJECTED = "fittingRejected";
	
	/**
	 * 退货单
	 */
	@ManyToOne(targetEntity = FittingRejected.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "fittingRejectedId")
	private FittingRejected fittingRejected;
	
	/**
	 * 数量
	 */
	private BigDecimal quantity;
	
	/**
	 * @return the fittingRejected
	 */
	public FittingRejected getFittingRejected() {
		return fittingRejected;
	}

	/**
	 * @param fittingRejected the fittingRejected to set
	 */
	public void setFittingRejected(FittingRejected fittingRejected) {
		this.fittingRejected = fittingRejected;
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
