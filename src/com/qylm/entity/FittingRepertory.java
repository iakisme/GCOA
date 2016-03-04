package com.qylm.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 配件库存久化类
 * @author smj
 */
@Entity
@Table(uniqueConstraints = {}, name = "fittingrepertory")
public class FittingRepertory extends BaseEntity {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -368802084191710143L;
	
	/**
	 * 配件信息（搜索条件）
	 */
	public static final String FITTING_INFO = "fittingInfo";
	
	/**
	 * 配件信息.id（搜索条件）
	 */
	public static final String FITTING_INFO_ID = "fittingInfo.id";

	/**
	 * 车辆名称（搜索条件）
	 */
	public static final String CAR_NAME = "fittingInfo.carName";
	
	/**
	 * 配件品牌（搜索条件）
	 */
	public static final String FITTING_BRAND = "fittingInfo.fittingBrand";
	
	/**
	 * 配件名称（搜索条件）
	 */
	public static final String FITTING_NAME = "fittingInfo.fittingName";
	
	/**
	 * 型号（搜索条件）
	 */
	public static final String MODEL = "fittingInfo.model";
	
	/**
	 * 配件信息
	 */
	@ManyToOne(targetEntity = FittingInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "fittingInfoId")
	private FittingInfo fittingInfo;

	/**
	 * 库存现数量
	 */
	private BigDecimal repertorySum;
	
	/**
	 * @return the fittingInfo
	 */
	public FittingInfo getFittingInfo() {
		return fittingInfo;
	}

	/**
	 * @param fittingInfo the fittingInfo to set
	 */
	public void setFittingInfo(FittingInfo fittingInfo) {
		this.fittingInfo = fittingInfo;
	}

	/**
	 * @return the repertorySum
	 */
	public BigDecimal getRepertorySum() {
		return repertorySum;
	}

	/**
	 * @param repertorySum the repertorySum to set
	 */
	public void setRepertorySum(BigDecimal repertorySum) {
		this.repertorySum = repertorySum;
	}

}
