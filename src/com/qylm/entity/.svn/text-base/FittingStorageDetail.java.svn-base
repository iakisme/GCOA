package com.qylm.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 入库单详细久化类
 * @author smj
 */
@Entity
@Table(uniqueConstraints = {}, name = "fittingstorage_detail")
public class FittingStorageDetail extends BaseFittingInfo {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7085251513900575788L;

	/**
	 * 入库单
	 */
	public static final String FITTING_STORAGE = "fittingStorage";
	
	/**
	 * 入库单
	 */
	@ManyToOne(targetEntity = FittingStorage.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "fittingStorageId")
	private FittingStorage fittingStorage;
	
	/**
	 * 数量
	 */
	private BigDecimal quantity;
	
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
